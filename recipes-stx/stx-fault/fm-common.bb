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
#
#
inherit autotools
inherit setuptools

require fault-common.inc

SRC_URI += "file://0001-fm-common-add-LDFLAGS.patch"

S = "${S_DIR}/fm-common/sources"
EXTRA_OEMAKE = '-e INCLUDES="-I./ " \
               EXTRACCFLAGS=" " \
               CCFLAGS="${CXXFLAGS} ${CCSHARED}" \
               LDFLAGS="${LDFLAGS} -shared" \
               LIBDIR=${libdir} \
               INCDIR=${includedir} \
               CGCS_DOC_DEPLOY=${docdir} \
               DESTDIR=${D} \
               BINDIR=${bindir} \
              '

do_configure_prepend () {
    sed -i -e 's|/usr/local/bin|${bindir}|' ${S}/fmConstants.h
    cd ${S}
} 


# need to build fm-common library first then setup.py can run
do_compile_prepend() {
	cd ${S}
	autotools_do_compile
}

do_install_prepend() {
	cd ${S}
	autotools_do_install
}
