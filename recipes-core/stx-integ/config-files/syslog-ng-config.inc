PACKAGES += " syslog-ng-config"

RDEPENDS_syslog-ng-config += " bash"

do_configure_prepend () {
	:
} 

do_compile_prepend () {
	:
}

do_install_prepend () {

	cd ${S}/config-files/syslog-ng-config 
	install -d -m755 ${D}/${datadir}/starlingx
	install -d -m755 ${D}/${sysconfdir}/sysconfig/syslog-ng
	install -d -m755 ${D}/${sysconfdir}/logrotate.d
	install -d -m755 ${D}/${systemd_system_unitdir}
	install -d -m755 ${D}/${sbindir}

	install -D -m644 files/syslog-ng.conf ${D}/${datadir}/starlingx/syslog-ng.conf
	install -D -m644 files/syslog-ng.logrotate ${D}/${datadir}/starlingx/syslog-ng.logrotate
	install -D -m644 files/remotelogging.conf ${D}/${sysconfdir}/syslog-ng/remotelogging.conf
	install -D -m700 files/fm_event_syslogger ${D}/${sbindir}/fm_event_syslogger
	install -D -m644 files/syslog-ng.service ${D}/${datadir}/starlingx/syslog-ng.service

	install -D -m644 files/syslog-ng.conf ${D}/${sysconfdir}/syslog-ng/syslog-ng.conf
	install -D -m644 files/syslog-ng.logrotate ${D}/${sysconfdir}/logrotate.d/syslog
	install -D -m644 files/syslog-ng.service ${D}/${systemd_system_unitdir}/syslog-ng.service

}

FILES_syslog-ng-config = " \
	${datadir}/starlingx/syslog-ng.conf \
	${datadir}/starlingx/syslog-ng.logrotate \
	${sysconfdir}/syslog-ng/remotelogging.conf \
	${sbindir}/fm_event_syslogger \
	${datadir}/starlingx/syslog-ng.service \
	${sysconfdir}/syslog-ng/syslog-ng.conf \
	${sysconfdir}/logrotate.d/syslog \
	${systemd_system_unitdir}/syslog-ng.service \
	${sysconfdir}/sysconfig/syslog-ng \
	"
