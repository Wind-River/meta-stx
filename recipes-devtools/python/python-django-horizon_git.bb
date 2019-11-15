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

DESCRIPTION = "Simple database sharding (horizontal partitioning) library for Django applications."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=50eaaae97522b223bb0b67befd823e69"

#SRC_URI[md5sum] = ""
#SRC_URI[sha256sum] = ""

SRCREV = "9b413570111a5d57f1ea1d125ca8a1768565eb88"
PROTOCOL = "https"
BRANCH = "master"
S = "${WORKDIR}/git"

SRC_URI = "git://github.com/uncovertruth/django-horizon;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"


DEPENDS += " python"
inherit setuptools distutils pkgconfig
