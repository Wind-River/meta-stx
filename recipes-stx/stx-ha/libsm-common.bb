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

PACKAGES += " sm-common-libs"
PACKAGES += " sm-eru"

S = "${S_DIR}/service-mgmt/sm-common"

require ha-common.inc

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

DEPENDS_append = " \
	glib-2.0 \
	"

inherit pkgconfig

RDEPENDS_sm-common-libs += " \
	bash \
	sqlite \
	util-linux \
	systemd \
	"
RDEPENDS_sm-eru = " sm-common-libs"
RDEPENDS_libsm-common = " sm-common-libs"

inherit systemd
SYSTEMD_PACKAGES += "sm-eru"
SYSTEMD_SERVICE_sm-eru = "sm-eru.service sm-watchdog.service"
SYSTEMD_AUTO_ENABLE_sm-eru = "enable"

EXTRA_OEMAKE = ' -e VER=0 VER_MJR=1 \
		INCLUDES="-I. $(pkg-config --cflags glib-2.0)" \
		CCFLAGS="${CXXFLAGS} -fPIC" LDFLAGS="${LDFLAGS} -shared -rdynamic" \
		EXTRACCFLAGS="${LDFLAGS}" \
		'

do_install_append () {
	oe_runmake -e BUILDSUBDIR=${B} DEST_DIR=${D} BIN_DIR=${bindir} \
		UNIT_DIR=${systemd_system_unitdir} LIB_DIR=${libdir} \
		INC_DIR=${includedir} ETC_DIR=${sysconfdir} VER=0 VER_MJR=1 install
	rm -f ${D}/var/lib/sm/watchdog/modules/libsm_watchdog_nfs.so
	if [ -d ${D}/etc/pmon.d ] ; then
		chmod 0755 ${D}/etc/pmon.d
	fi
}

FILES_${PN}_append = " \
	${systemd_system_unitdir}/sm-eru.service \
	${systemd_system_unitdir}/sm-watchdog.service \
	"

FILES_${PN} = " \
	${libdir}/libsm_common.so.0 \
	${libdir}/libsm_common.so.1 \
	${libdir}/libsm_common.so \
	"

FILES_sm-common-libs = " \
	var/lib/sm/watchdog/modules/libsm_watchdog_nfs.so.0 \
	var/lib/sm/watchdog/modules/libsm_watchdog_nfs.so.1 \
	"

FILES_sm-eru = " \
	${bindir}/sm-eru \
	${bindir}/sm-eru-dump \
	${bindir}/sm-watchdog \
	${systemd_system_unitdir}/sm-eru.service \
	${systemd_system_unitdir}/sm-watchdog.service \
	${sysconfdir}/init.d/sm-eru \
	${sysconfdir}/pmon.d/sm-eru.conf \
	${sysconfdir}/init.d/sm-watchdog \
	${sysconfdir}/pmon.d/sm-watchdog.conf \
	"
