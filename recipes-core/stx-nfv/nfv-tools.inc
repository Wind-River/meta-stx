PACKAGES += " nfv-tools"
inherit setuptools


do_configure_append () {
	cd ${S}/nfv/nfv-tools
	distutils_do_configure
} 

do_compile_append () {
	cd ${S}/nfv/nfv-tools
	distutils_do_compile
}

do_install_append () {
	cd ${S}/nfv/nfv-tools
	distutils_do_install

}

FILES_nfv-tools = " \
	${bindir}/nfv-forensic \
	${bindir}/nfv-notify \
	${libdir}/python2.7/site-packages/nfv_tools*egg-info \
	${libdir}/python2.7/site-packages/nfv_tools \
	"
