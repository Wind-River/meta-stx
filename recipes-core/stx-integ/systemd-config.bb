DESCRIPTION = "systemd-config"

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
	cd ${S}/base/systemd-config
	install -d ${D}/${datadir}/starlingx

	install -m644 files/60-persistent-storage.rules ${D}/${datadir}/starlingx/60-persistent-storage.rules
	install -m644 files/journald.conf ${D}/${datadir}/starlingx/journald.conf
	install -m644 files/systemd.conf.tmpfiles.d ${D}/${datadir}/starlingx/
	install -m644 files/tmp.conf.tmpfiles.d ${D}/${datadir}/starlingx/
	install -m644 files/tmp.mount ${D}/${datadir}/starlingx/

}

FILES_${PN}_append += " \
	${datadir} \
	"
