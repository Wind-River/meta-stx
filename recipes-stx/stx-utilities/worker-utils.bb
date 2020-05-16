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

DESCRIPTION  = "Initial worker node resource reservation and misc. utilities"
SUMMARY  = "dynamic MOTD generation"

require utilities-common.inc

S = "${S_DIR}/utilities/worker-utils/worker-utils"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

RDEPENDS_${PN}_append  = " perl systemd python bash"
DEPENDS += " python"

inherit python-dir systemd
DISTRO_FEATURES_BACKFILL_CONSIDERED_remove = "sysvinit"
SYSTEMD_PACKAGES += "${PN}"
SYSTEMD_SERVICE_${PN} = " affine-tasks.service affine-platform.sh.service"

do_configure[noexec] = "1"

do_install() {

	oe_runmake install \
		BINDIR=${D}/${bindir} \
		INITDDIR=${D}/${sysconfdir}/init.d \
		GOENABLEDDIR=${D}/${sysconfdir}/goenabled.d \
		PLATFORMCONFDIR=${D}/${sysconfdir}/platform \
		SYSTEMDDIR=${D}/${systemd_system_unitdir}
}
