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
require fault-common.inc

DEPENDS += "fm-common snmp-audittrail"

SRC_URI += "file://0001-snmp-ext-add-LDFLAGS.patch"

S = "${S_DIR}/snmp-ext/sources"

EXTRA_OEMAKE = '-e PATCH=0 \
		INCLUDES="-I. -I${S}/fm-common/sources" \
		LDFLAGS="${LDFLAGS} -shared -L${S}/fm-common/sources" \
		CCFLAGS="${CXXFLAGS} -fPIC" \
                DEST_DIR="${D}" LIB_DIR="${libdir}" \
                MIBVER=0 \
               '

do_install() {
	cd ${S}
	oe_runmake -e DEST_DIR=${D} BIN_DIR=${bindir} UNIT_DIR=${systemd_system_unitdir} \
		LIB_DIR=${libdir} INC_DIR=${includedir} DATA_DIR=${datadir} install
}

FILES_${PN}_append = " ${datadir}/"
