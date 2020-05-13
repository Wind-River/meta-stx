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

require fault-common.inc

S = "${S_DIR}/fm-doc/fm_doc"

do_configure () {
     :
}

do_compile () {
     :
}

do_install () {
    install -d -m 755 ${D}/${docdir}
    install -d -m 755 ${D}/${sysconfdir}/fm
    install -m 744 events.yaml ${D}/${sysconfdir}/fm/
    install -m 644 events.yaml ${D}/${docdir}
    install -m 755 checkEventYaml ${D}/${docdir}
    install -m 644 parseEventYaml.py ${D}/${docdir}
    install -m 644 check_missing_alarms.py ${D}/${docdir}
}
