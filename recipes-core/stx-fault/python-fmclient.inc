PACKAGES += " python-fmclient"

do_configure_append () {
	cd ${S}/python-fmclient/fmclient
	distutils_do_configure
} 

do_compile_append() {
	cd ${S}/python-fmclient/fmclient
	distutils_do_compile
}

do_install_append() {
	cd ${S}/python-fmclient/fmclient
	distutils_do_install
	
	install -d -m 0755 ${D}/${sysconfdir}/bash_completion.d/
	install -p -D -m 664 tools/fm.bash_completion ${D}/${sysconfdir}/bash_completion.d/fm.bash_completion
}

FILES_python-fmclient = " \
	${bindir}/fm \
	${libdir}/python2.7/site-packages/fmclient-*.egg-info/ \
	${libdir}/python2.7/site-packages/fmclient/ \
	${sysconfdir}/bash_completion.d/fm.bash_completion \
	"
