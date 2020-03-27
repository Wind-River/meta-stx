PACKAGES += " fm-api"

do_configure_append () {
	cd ${S}/fm-api
	distutils_do_configure
} 

do_compile_append() {
	cd ${S}/fm-api
	distutils_do_compile
}

do_install_append() {
	cd ${S}/fm-api
	distutils_do_install

	# fix the path for binaries
	sed -i -e 's|/usr/local/bin|${bindir}|' ${D}${libdir}/python2.7/site-packages/fm_api/constants.py
}

FILES_fm-api_append = " \
	${libdir}/python2.7/site-packages/fm_api-1.0.0-py2.7.egg-info/ \
	${libdir}/python2.7/site-packages/fm_api \
	"
