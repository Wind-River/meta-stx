DESCRIPTION = "syslog-ng-config"

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "70609a3d55e5b7d2be82667fc35792505f9013c4"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://opendev.org/starlingx/config.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

require stx-integ-config-files.inc

RDEPENDS_${PN} += " bash"

do_configure () {
	:
} 

do_compile() {
	:
}

do_install () {

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

FILES_${PN}_append += " \
		${systemd_system_unitdir} \
		${datadir} \
		"
