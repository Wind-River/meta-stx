
PACKAGES += " python-cephclient"

RDEPENDS_ceph-manager += " \
	python \
	python-ipaddress \
	python-six \
	python-requests \
	"

DESCRIPTION_python-cephclient = " \
A client library in Python for Ceph Mgr RESTful plugin providing REST API \
access to the cluster over an SSL-secured connection. Python API is compatible \
with the old Python Ceph client at \
https://github.com/dmsimard/python-cephclient that no longer works in Ceph \
mimic because Ceph REST API component was removed. \
"

do_configure_append() {
	cd ${S}/ceph/python-cephclient/python-cephclient
	rm -rf .pytest_cache
	rm -rf python_cephclient.egg-info
	rm -f requirements.txt
	distutils_do_configure
}
do_compile_append() {
	cd ${S}/ceph/python-cephclient/python-cephclient
	distutils_do_compile
}

do_install_append() {
	cd ${S}/ceph/python-cephclient/python-cephclient
	distutils_do_install
}

FILES_python-cephclient = "  \
	${PYTHON_SITEPACKAGES_DIR}/cephclient \
	${PYTHON_SITEPACKAGES_DIR}/python_cephclient-13.2.2.0-py2.7.egg-info \
	"
#	/usr/share/licenses/python-cephclient-13.2.2.0
#	/usr/share/licenses/python-cephclient-13.2.2.0/LICENSE

