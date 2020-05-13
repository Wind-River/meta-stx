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

DESCRIPTION = "stx-update"

PROTOCOL = "https"
BRANCH = "r/stx.3.0"
SRCREV = "2542c5539bab060830009d02cbb257cc8bf4a376"
S_DIR = "${WORKDIR}/git"
PV = "1.0.0"


SRC_URI = "git://opendev.org/starlingx/update.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

DEPENDS = " \
	python \
	python-pbr-native \
	"
