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

require nfv-common.inc

S = "${S_DIR}/nfv/nfv-plugins"

inherit setuptools

do_install_append () {

	install -d -m 755 ${D}/${sysconfdir}/nfv/
	install -d -m 755 ${D}/${sysconfdir}/nfv/nfv_plugins/
	install -d -m 755 ${D}/${sysconfdir}/nfv/nfv_plugins/alarm_handlers/

	install -p -D -m 600 nfv_plugins/alarm_handlers/config.ini \
			${D}/${sysconfdir}/nfv/nfv_plugins/alarm_handlers/config.ini

	install -d -m 755 ${D}/${sysconfdir}/nfv/nfv_plugins/event_log_handlers/

	install -p -D -m 600 nfv_plugins/event_log_handlers/config.ini \
			${D}/${sysconfdir}/nfv/nfv_plugins/event_log_handlers/config.ini
			\
	install -d -m 755 ${D}/${sysconfdir}/nfv/nfv_plugins/nfvi_plugins/

	install -p -D -m 600 nfv_plugins/nfvi_plugins/config.ini \
			${D}/${sysconfdir}/nfv/nfv_plugins/nfvi_plugins/config.ini
					
	install -d -m 755 ${D}/
	install -p -D -m 644 scripts/nfvi-plugins.logrotate \
			${D}/${sysconfdir}/logrotate.d/nfvi-plugins.logrotate
	
}
