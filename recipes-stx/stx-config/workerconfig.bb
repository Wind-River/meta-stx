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

PACKAGES += " \
	workerconfig-standalone \
	workerconfig-subfunction \
	"

require config-common.inc

S = "${S_DIR}/workerconfig/workerconfig"


LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

RDEPENDS_${PN}_append = "bash"
RDEPENDS_workerconfig-standalone += "workerconfig"
RDEPENDS_workerconfig-subfunction += "workerconfig"

systemddir = "${sysconfdir}/systemd/system"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
	oe_runmake -e GOENABLEDDIR=${D}/${sysconfdir}/goenabled.d  INITDDIR=${D}/${sysconfdir}/init.d \
		SYSTEMDDIR=${D}/${systemddir} install

	sed -i -e 's|/usr/local/bin|${bindir}|' \
		${D}${sysconfdir}/init.d/worker_config
}

FILES_${PN} = " \
	${sysconfdir}/init.d/worker_config \
	"

FILES_workerconfig-standalone = " \
	${systemddir}/config/workerconfig-standalone.service \
	${sysconfdir}/goenabled.d/config_goenabled_check.sh \
	"

FILES_workerconfig-subfunction = "  \
	${sysconfdir}/systemd/system/config/workerconfig-combined.service \
	"

pkg_postinst_workerconfig-standalone () {
	if [ ! -e $D${systemddir}/workerconfig.service ]; then
		cp $D${systemddir}/config/workerconfig-standalone.service $D${systemddir}/workerconfig.service
	else
		rm -f $D${systemddir}/workerconfig.service
		cp $D${systemddir}/config/workerconfig-standalone.service $D${systemddir}/workerconfig.service
	fi

	# enable workerconfig service by default
	OPTS=""
	if [ -n "$D" ]; then
		OPTS="--root=$D"
	fi
	if [ -z "$D" ]; then
		systemctl daemon-reload
	fi

	systemctl $OPTS enable workerconfig.service

	if [ -z "$D" ]; then
		systemctl --no-block restart workerconfig.service
	fi
}


pkg_postinst_workerconfig-subfunction () {
	if [ ! -e $D${systemddir}/workerconfig.service ]; then
		cp $D${systemddir}/config/workerconfig-combined.service $D${systemddir}/workerconfig.service
	else
		rm -f $D${systemddir}/workerconfig.service
		cp $D${systemddir}/config/workerconfig-combined.service $D${systemddir}/workerconfig.service
	fi

	# enable workerconfig service by default
	OPTS=""
	if [ -n "$D" ]; then
		OPTS="--root=$D"
	fi
	if [ -z "$D" ]; then
		systemctl daemon-reload
	fi

	systemctl $OPTS enable workerconfig.service

	if [ -z "$D" ]; then
		systemctl --no-block restart workerconfig.service
	fi
}
