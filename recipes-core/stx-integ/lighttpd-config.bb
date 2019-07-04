DESCRIPTION = "lighttpd-config"

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "70609a3d55e5b7d2be82667fc35792505f9013c4"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://opendev.org/starlingx/config.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

require stx-integ.inc

RDEPENDS_${PN} += " bash"

do_configure () {
	:
} 

do_compile() {
	:
}

do_install () {
	cd ${S}/base/lighttpd-config
	install -d -m 1777 ${D}/www/tmp
	install -d ${D}/${sysconfdir}/lighttpd/ssl
	install -d ${D}/www/pages/dav
	install -d ${D}/${datadir}/starlingx

	install -m640 files/lighttpd.conf ${D}/${datadir}/starlingx/lighttpd.conf
	install -m755 files/lighttpd.init ${D}/${datadir}/starlingx/lighttpd.init
	install -m644 files/lighttpd-inc.conf ${D}/${sysconfdir}/lighttpd/lighttpd-inc.conf
	install -m644 files/index.html.lighttpd ${D}/www/pages/index.html
	
	install -d ${D}/${sysconfdir}/logrotate.d
	install -m644 files/lighttpd.logrotate ${D}/${datadir}/starlingx/lighttpd.logrotate

}

FILES_${PN}_append += " \
	${systemd_system_unitdir} \
	${datadir} \
	www \
	"
		
