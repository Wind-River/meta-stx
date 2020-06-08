
PACKAGES += " sm-client"

RDEPENDS_sm-client += " \
	python \
	python-six \
	"

do_configure_append () {
	cd ${S}/service-mgmt-client/sm-client
	distutils_do_configure
} 

do_compile_append() {
	cd ${S}/service-mgmt-client/sm-client
	distutils_do_compile
}

do_install_append () {
	cd ${S}/service-mgmt-client/sm-client
	distutils_do_install
	install -d -m 0755 ${D}/${bindir}
	install -m 755 usr/bin/smc ${D}/${bindir}
}

FILES_sm-client = " \
	${bindir}/smc \
	${libdir}/python2.7/site-packages/sm_client*.egg-info/ \
	${libdir}/python2.7/site-packages/sm_client/ \
	"
