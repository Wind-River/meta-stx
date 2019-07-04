DESCRIPTION = "rsync-config"

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
	cd ${S}/config-files/rsync-config/
	install -d -m0755 ${D}/${datadir}/starlingx
	install -d -m0755 ${D}/${sysconfdir}/

	install  -m 644 files/rsyncd.conf ${D}/${datadir}/starlingx/stx.rsyncd.conf
	install  -m 644 files/rsyncd.conf ${D}/${sysconfdir}/rsyncd.conf
}

FILES_${PN}_append += " ${datadir} "
