PACKAGES += " nfv-client"

inherit setuptools


do_configure_prepend () {
	cd ${S}/nfv/nfv-client
	distutils_do_configure
} 

do_compileprepend () {
	cd ${S}/nfv/nfv-client
	distutils_do_compile
}

do_install_prepend () {
	cd ${S}/nfv/nfv-client
	distutils_do_install
	
	install -d -m 755 ${D}/${sysconfdir}/bash_completion.d
	install -m 444 scripts/sw-manager.completion ${D}/${sysconfdir}/bash_completion.d/sw-manager

}

FILES_nfv-client = " \
	${bindir}/sw-manager \
	${sysconfdir}/bash_completion.d/sw-manager \
	${libdir}/python2.7/site-packages/nfv_client*egg-info \
	${libdir}/python2.7/site-packages/nfv_client \
	"
