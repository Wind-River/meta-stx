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

PACKAGES += " config-gate-worker"

require config-common.inc

S = "${S_DIR}/config-gate/files"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

RDEPENDS_config-gate-worker_append = " bash"
RDEPENDS_${PN}_append = " bash"


inherit systemd
SYSTEMD_PACKAGES += "${PN}"
SYSTEMD_PACKAGES += "config-gate-worker"
SYSTEMD_SERVICE_${PN} = "config.service"
SYSTEMD_SERVICE_config-gate-worker = "worker-config-gate.service"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
	oe_runmake -e \
                 SBINDIR=${D}/${sbindir} SYSTEMDDIR=${D}/${systemd_system_unitdir} \
		 install
}
