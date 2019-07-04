DESCRIPTION = "ntp-config"

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
	cd ${S}/config-files/ntp-config 
	install -d -m0755 ${D}/${datadir}/starlingx
	install -d -m0755 ${D}/${sysconfdir}/sysconfig

	install -m644 files/ntp.conf ${D}/${datadir}/starlingx/ntp.config
	install -m644 files/ntpd.sysconfig ${D}/${datadir}/starlingx/ntpd.sysconfig

	install -m644 files/ntp.conf ${D}/${sysconfdir}/ntp.config
	install -m644 files/ntpd.sysconfig ${D}/${sysconfdir}/sysconfig/ntpd
}

FILES_${PN}_append += " ${datadir} "
