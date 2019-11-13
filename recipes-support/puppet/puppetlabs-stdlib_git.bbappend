#
# Copyright (C) 2019 Wind River Systems, Inc.
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

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRCREV = "f2492ee916c1c8e0345514045432c4a049674029"
PROTOCOL = "https"
BRANCH = "master"
S = "${WORKDIR}/git"
PV = "4.13.1"

SRC_URI = " \
    git://github.com/puppetlabs/puppetlabs-stdlib.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH} \
        file://puppetlabs-stdlib/Add-gemspec.patch \
	"

#inherit ruby
#
#DEPENDS += " \
#	ruby \
#	facter \
#	"
#
#RDEPENDS_${PN} += " \
#	ruby \
#	facter \
#	puppet \
#	" 
#	
RUBY_INSTALL_GEMS = "puppetlabs-stdlib-${PV}.gem"

do_install_append () {
	install -d -m 0755 ${D}/${datadir}/puppet/modules/stdlib
	cp -r ${S}/* ${D}/${datadir}/puppet/modules/stdlib
}

FILES_${PN} += " ${datadir}"
