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

DESCRIPTION  = "dynamic MOTD generation"
SUMMARY  = "dynamic MOTD generation"

require utilities-common.inc

S = "${S_DIR}/utilities/update-motd/files"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

RDEPENDS_update-motd  += " cronie bash"


do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {

	install -d ${D}/${sbindir}
	install -m 700 motd-update ${D}/${sbindir}/motd-update

	install -m0755 -d ${D}/${sysconfdir}
	install -m0755 -d ${D}/${sysconfdir}/motd.d

	install -m 755 motd-header ${D}/${sysconfdir}/motd.d/00-header
	install -m 755 motd-footer ${D}/${sysconfdir}/motd.d/99-footer
	install -m 644 motd.head ${D}/${sysconfdir}/motd.d/motd.head

	install -m0755  -d ${D}/${sysconfdir}/cron.d
	install -m 600 motd-update.cron ${D}/${sysconfdir}/cron.d/motd-update
	install -m 700 customize-banner ${D}/${sbindir}/customize-banner
	install -m 700 apply_banner_customization ${D}/${sbindir}/apply_banner_customization
	install -m 700 install_banner_customization ${D}/${sbindir}/install_banner_customization

}
