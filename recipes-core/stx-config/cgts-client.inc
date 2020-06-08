
PACKAGES += " cgts-client"

inherit setuptools distutils python-dir
DEPENDS += " \
	python-pbr-native \
	"

RDEPENDS_cgts-client += " \
	python-prettytable \
	bash-completion \
	python-neutronclient \
	python-keystoneclient \
	python-six \
	python-httplib2 \
	"

do_configure_append() {
	cd ${S}/sysinv/cgts-client/cgts-client
	distutils_do_configure
} 

do_compile_append() {
	cd ${S}/sysinv/cgts-client/cgts-client
	distutils_do_compile
}

do_install_append() {
	cd ${S}/sysinv/cgts-client/cgts-client
	distutils_do_install

	install -d -m 755 ${D}/${sysconfdir}/bash_completion.d
	install -p -D -m 664 tools/system.bash_completion ${D}/${sysconfdir}/bash_completion.d/system.bash_completion
}

FILES_cgts-client = " \
	${bindir}/system \
	${sysconfdir}/bash_completion.d/ \
	${PYTHON_SITEPACKAGES_DIR}/sysinv/ \
	${PYTHON_SITEPACKAGES_DIR}/cgtsclient/ \
	${PYTHON_SITEPACKAGES_DIR}/cgtsclient-*.egg-info \
	${PYTHON_SITEPACKAGES_DIR}/sysinv-*.egg-info \
	"
