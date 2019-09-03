PACKAGES += " cgts-client"

do_configure_prepend() {
	echo "Configure cgts-client..."
	cd ${S}/sysinv/cgts-client/cgts-client
	distutils_do_configure
} 

do_compile_prepend() {
	echo "Building cgts-client..."
	cd ${S}/sysinv/cgts-client/cgts-client
	distutils_do_compile
}

do_install_prepend() {

	echo "Installing cgts-client..."
	cd ${S}/sysinv/cgts-client/cgts-client
	distutils_do_install
}

FILES_cgts-client = " \
	${libdir}/python2.7/site-packages/sysinv/ \
	${libdir}/python2.7/site-packages/cgtsclient/ \
	${libdir}/python2.7/site-packages/cgtsclient-*.egg-info \
	${libdir}/python2.7/site-packages/sysinv-*.egg-info \
	"
