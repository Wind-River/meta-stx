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
inherit setuptools

require fault-common.inc

S = "${S_DIR}/fm-rest-api/fm"

do_install_append() {
	install -d -m 755 ${D}/${systemd_system_unitdir}
	install -p -D -m 644 scripts/fm-api.service ${D}/${systemd_system_unitdir}
	install -p -D -m 755 scripts/fm-api ${D}/${sysconfdir}/init.d/fm-api
	install -p -D -m 644 fm-api-pmond.conf ${D}/${sysconfdir}/pmon.d/fm-api.conf
	
	# fix the path for init scripts
	sed -i -e 's|rc.d/||' ${D}/${systemd_system_unitdir}/*.service
}

inherit systemd
SYSTEMD_PACKAGES += "fm-rest-api"
SYSTEMD_SERVICE_${PN} = "fm-api.service"
SYSTEMD_AUTO_ENABLE_${PN} = "disable"
