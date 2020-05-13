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

S = "${S_DIR}/nfv/nfv-client"

inherit setuptools


do_install_append() {
	
	install -d -m 755 ${D}/${sysconfdir}/bash_completion.d
	install -m 444 scripts/sw-manager.completion ${D}/${sysconfdir}/bash_completion.d/sw-manager

}

FILES_${PN}_append = " \
	${sysconfdir}/bash_completion.d/sw-manager \
	"
