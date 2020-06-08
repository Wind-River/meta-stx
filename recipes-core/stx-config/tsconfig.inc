
PACKAGES += " tsconfig"

inherit distutils

RDEPENDS_tsconfig_append = " bash"

do_configure_append () {
	cd ${S}/tsconfig/tsconfig
	distutils_do_configure
} 

do_compile_append () {
	cd ${S}/tsconfig/tsconfig
	distutils_do_compile
}

do_install_append () {
	cd ${S}/tsconfig/tsconfig
	distutils_do_install


	install -m 755 -d ${D}/${bindir}
	install -m 500 scripts/tsconfig ${D}/${bindir}/

}

FILES_tsconfig = " \
	${PYTHON_SITEPACKAGES_DIR}/tsconfig \
	${PYTHON_SITEPACKAGES_DIR}/tsconfig*.egg-info \
	${bindir}/tsconfig \
	"
