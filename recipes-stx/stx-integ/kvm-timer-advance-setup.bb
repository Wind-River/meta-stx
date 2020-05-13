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

DESCRIPTION = "StarlingX KVM Timer Advance Package"

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "r/stx.3.0"
SRCNAME = "integ"
SRCREV = "0bf4b546df8c7fdec8cfc6cb6f71b9609ee54306"
S = "${WORKDIR}/git"
PV = "1.0.0"

LICENSE = "Apache-2.0 & GPL-2.0"
LIC_FILES_CHKSUM = "file://virt/kvm-timer-advance/files/LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

RDEPENDS_${PN}_append = " \
	systemd \
	bash \
	"


SRC_URI = "git://opendev.org/starlingx/${SRCNAME}.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH} \
	"
inherit setuptools systemd
SYSTEMD_PACKAGES += " ${PN}"
SYSTEMD_SERVICE_${PN} = "kvm_timer_advance_setup.service"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install () {
	
# kvm-timer-advance

	install -p -D -m 0755 ${S}/virt/kvm-timer-advance/files/setup_kvm_timer_advance.sh \
			${D}/${bindir}/setup_kvm_timer_advance.sh
	install -p -D -m 444 ${S}/virt/kvm-timer-advance/files/kvm_timer_advance_setup.service \
			${D}/${systemd_system_unitdir}/kvm_timer_advance_setup.service

}
