PACKAGES += " sm-client"

do_configure_prepend () {
	cd ${S}/service-mgmt-client/sm-client
	distutils_do_configure
} 

do_compile_prepend() {
	cd ${S}/service-mgmt-client/sm-client
	distutils_do_compile
}

do_install_prepend () {
	cd ${S}/service-mgmt-client/sm-client
	distutils_do_install
	install -d -m 0755 ${D}/${bindir}
	install -m 755 usr/bin/smc ${D}/${bindir}
}

FILES_sm-client = " "
