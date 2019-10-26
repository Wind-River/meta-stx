PACKAGES += " python-inventoryclient"

RDEPENDS_python-inventoryclient_append = " bash"

do_configure_prepend () {
	cd ${S}/python-inventoryclient/inventoryclient
	distutils_do_configure
} 

do_compile_prepend () {
	cd ${S}/python-inventoryclient/inventoryclient
	distutils_do_compile
}

do_install_prepend () {
	cd ${S}/python-inventoryclient/inventoryclient
	distutils_do_install
	
	install -d -m 755 ${D}/${sysconfdir}/bash_completion.d
	install -p -D -m 664 tools/inventory.bash_completion ${D}/${sysconfdir}/bash_completion.d

}

# pkg_postinst_ontarget_python-inventoryclient () { } 

FILES_python-inventoryclient = " \
	${bindir}/inventory \
	${sysconfdir}/bash_completion.d/inventory.bash_completion \
	${libdir}/python2.7/site-packages/inventoryclient-*.egg-info/ \
	${libdir}/python2.7/site-packages/inventoryclient/ \
	"
