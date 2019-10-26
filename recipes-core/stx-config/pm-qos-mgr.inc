PACKAGES += " pm-qos-mgr"

do_configure_prepend () {
	cd ${S}/pm-qos-mgr/src
	distutils_do_configure
} 

do_compile_prepend() {
	cd ${S}/pm-qos-mgr/src
	distutils_do_compile
}

do_install_prepend () {
	cd ${S}/pm-qos-mgr/src
	distutils_do_install
	install -p -D -m 664 pm-qos-mgr.service ${D}/${systemd_system_unitdir}/pm-qos-mgr.service
}

FILES_pm-qos-mgr = " \
	${bindir}/pm-qos-mgr \
	${systemd_system_unitdir}/pm-qos-mgr.service \
	${libdir}/python2.7/site-packages/pm_qos_mgr*egg-info/ \
	${libdir}/python2.7/site-packages/pm_qos_mgr/ \
	"
