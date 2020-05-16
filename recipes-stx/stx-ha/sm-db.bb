#
## Copyright (C) 2019 Wind River Systems, Inc.
#
#  Licensed under the Apache License, Version 2.0 (the "License");
#  you may not use this file except in compliance with the License.
#  You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
#  Unless required by applicable law or agreed to in writing, software
#  distributed under the License is distributed on an "AS IS" BASIS,
#  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#  See the License for the specific language governing permissions and
#  limitations under the License.

S = "${S_DIR}/service-mgmt/sm-db"

require ha-common.inc

DEPENDS_append =  " \
	sqlite3-native \
	libsm-common \
	"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI += "file://0001-sm-db-Fix-dest-libdir.patch"

EXTRA_OEMAKE = ' -e VER=0 VER_MJR=1 \
		INCLUDES="-I." \
		CCFLAGS="${CXXFLAGS} -fPIC" \
		LDFLAGS="${LDFLAGS} -shared -rdynamic -L${S}/service-mgmt/sm-common/src " \
		EXTRACCFLAGS="${LDFLAGS} -L${S}/service-mgmt/sm-common/src " \
		'

do_compile_prepend() {
	cd ${S}
	if [ ! -f database/sm.db ]; then 
		sqlite3 database/sm.db < database/create_sm_db.sql
	fi
	if [ ! -f database/sm.hb.db ]; then 
		sqlite3 database/sm.hb.db < database/create_sm_hb_db.sql
	fi
}

do_install() {
	cd ${S}
	oe_runmake -e DEST_DIR=${D} BIN_DIR=${bindir} UNIT_DIR=${systemd_system_unitdir} \
			LIB_DIR=${libdir} INC_DIR=${includedir} VER=0 VER_MJR=1 install
}
