PACKAGES += " tsconfig"

inherit setuptools

RDEPENDS_tsconfig_append = " bash"

do_configure_prepend () {
	cd ${S}/tsconfig/tsconfig
	distutils_do_configure
} 

do_compile_prepend () {
	cd ${S}/tsconfig/tsconfig
	distutils_do_compile
}

do_install_prepend () {
	cd ${S}/tsconfig/tsconfig
	distutils_do_install


	install -m 755 -d ${D}/${bindir}
	install -m 500 scripts/tsconfig ${D}/${bindir}/

}

FILES_tsconfig = " \
	${libdir}/python2.7/site-packages/tsconfig \
	${libdir}/python2.7/site-packages/tsconfig*.egg-info \
	${bindir}/tsconfig \
	"
