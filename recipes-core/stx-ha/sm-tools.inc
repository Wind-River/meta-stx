PACKAGES += " sm-tools"

# DEPENDS = " \
# 	python \
# 	python-pbr-native \
# 	"

do_configure_prepend () {
	cd ${S}/service-mgmt-tools/sm-tools
	distutils_do_configure
} 

do_compile_prepend() {
	cd ${S}/service-mgmt-tools/sm-tools
	distutils_do_compile
}

do_install_prepend () {
	cd ${S}/service-mgmt-tools/sm-tools
	distutils_do_install
}

FILES_sm-tools = " \
	${bindir}/sm-query \
	${bindir}/sm-provision \
	${bindir}/sm-restart \
	${bindir}/sm-unmanage \
	${bindir}/sm-dump \
	${bindir}/sm-iface-state \
	${bindir}/sm-configure \
	${bindir}/sm-manage \
	${bindir}/sm-deprovision \
	${bindir}/sm-restart-safe \
	${libdir}/python2.7/site-packages/sm_tools-*.egg-info/ \
	${libdir}/python2.7/site-packages/sm_tools/ \
	"
