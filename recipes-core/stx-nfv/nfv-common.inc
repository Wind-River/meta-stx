PACKAGES += " nfv-common"

inherit setuptools

do_configure_prepend () {
	cd ${S}/nfv/nfv-common
	distutils_do_configure
} 

do_compile_prepend () {
	cd ${S}/nfv/nfv-common
	distutils_do_compile
}

do_install_prepend () {
	cd ${S}/nfv/nfv-common
	distutils_do_install
	
}

pkg_postinst_ontarget_nfv-common () {

}

FILES_nfv-common_append = " \
	${libdir}/python2.7/site-packages/nfv_common \
	${libdir}/python2.7/site-packages/windriver_nfv_common_plugins-1.0.0-py2.7.egg-info \
	"
