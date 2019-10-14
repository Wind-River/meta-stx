PACKAGES += " logrotate-config"

RDEPENDS_logrotate-config_append = " bash"

do_configure_append () {
	:
} 

do_compile_append () {
	:
}

do_install_append () {
	cd ${S}/logging/logrotate-config/files/
	install -d ${D}/${datadir}/starlingx
	mkdir -p ${D}/${sysconfdir}/cron.d
	install -m 644 logrotate-cron.d ${D}/${sysconfdir}/cron.d/logrotate
	install -m 644 logrotate.conf ${D}/${datadir}/starlingx/logrotate.conf
}

FILES_logrotate-config_append = " \
	${sysconfdir}/cron.d/logrotate \
	${datadir}/starlingx/logrotate.conf \
	"
