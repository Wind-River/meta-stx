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

FILES_sm-tools = " "
