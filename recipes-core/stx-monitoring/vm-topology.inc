PACKAGES += " vm-topology"

RDEPENDS_vm-topology += " \
	python \
	libvirt \
	python-keyring \
	"

DEPENDS += " \
	python-keyring \
	libvirt \
	"

inherit setuptools distutils python-dir

do_configure_append () {
	cd ${S}/vm-topology/vm-topology
	distutils_do_configure
} 

do_compile_append() {
	cd ${S}/vm-topology/vm-topology
	distutils_do_compile
}

do_install_append() {
	cd ${S}/vm-topology/vm-topology
	distutils_do_install
}

FILES_vm-topology  = " \
	${bindir}/vm-topology \
	${PYTHON_SITEPACKAGES_DIR}/ \
	"
