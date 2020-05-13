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

DESCRIPTION = " \
A client library in Python for Ceph Mgr RESTful plugin providing REST API \
access to the cluster over an SSL-secured connection. Python API is compatible \
with the old Python Ceph client at \
https://github.com/dmsimard/python-cephclient that no longer works in Ceph \
mimic because Ceph REST API component was removed. \
"

require utilities-common.inc

S = "${S_DIR}/ceph/python-cephclient/python-cephclient"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=41687b590435621fc0676ac02c51154f"

RDEPENDS_${PN}_append = " \
	python \
	python-ipaddress \
	python-six \
	python-requests \
	"

inherit setuptools

do_configure_prepend() {
	rm -rf .pytest_cache
	rm -rf python_cephclient.egg-info
	rm -f requirements.txt
}
