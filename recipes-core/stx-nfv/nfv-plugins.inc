PACKAGES += " nfv-plugins"

inherit setuptools


do_configure_append () {
	cd ${S}/nfv/nfv-plugins
	distutils_do_configure
} 

do_compile_append () {
	cd ${S}/nfv/nfv-plugins
	distutils_do_compile
}

do_install_append () {
	cd ${S}/nfv/nfv-plugins
	distutils_do_install

	install -d -m 755 ${D}/${sysconfdir}/nfv/
	install -d -m 755 ${D}/${sysconfdir}/nfv/nfv_plugins/
	install -d -m 755 ${D}/${sysconfdir}/nfv/nfv_plugins/alarm_handlers/

	install -p -D -m 600 nfv_plugins/alarm_handlers/config.ini \
			${D}/${sysconfdir}/nfv/nfv_plugins/alarm_handlers/config.ini

	install -d -m 755 ${D}/${sysconfdir}/nfv/nfv_plugins/event_log_handlers/

	install -p -D -m 600 nfv_plugins/event_log_handlers/config.ini \
			${D}/${sysconfdir}/nfv/nfv_plugins/event_log_handlers/config.ini
			\
	install -d -m 755 ${D}/${sysconfdir}/nfv/nfv_plugins/nfvi_plugins/

	install -p -D -m 600 nfv_plugins/nfvi_plugins/config.ini \
			${D}/${sysconfdir}/nfv/nfv_plugins/nfvi_plugins/config.ini
					
	install -d -m 755 ${D}/
	install -p -D -m 644 scripts/nfvi-plugins.logrotate \
			${D}/${sysconfdir}/logrotate.d/nfvi-plugins.logrotate
	
}

FILES_nfv-plugins += " \
	${bindir}/nfv-forensic \
	${bindir}/nfv-notify \
	${libdir}/python2.7/site-packages/windriver_nfv_plugins*egg-info \
	${libdir}/python2.7/site-packages/nfv_plugins \
	${sysconfdir}/nfv/nfv_plugins/ \
	${sysconfdir}/logrotate.d/nfvi-plugins.logrotate \
	"

