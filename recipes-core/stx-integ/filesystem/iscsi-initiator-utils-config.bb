DESCRIPTION = "iscsi-initiator-utils-config"

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "70609a3d55e5b7d2be82667fc35792505f9013c4"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://opendev.org/starlingx/config.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

require stx-integ-filesystem.inc

RDEPENDS_${PN} += " bash"

do_configure () {
	:
} 

do_compile() {
	:
}

do_install () {

	cd ${S}/filesystem/iscsi-initiator-utils-config/
	install -d -m755 ${D}/${libdir}/tempfiles.d
	install -d -m755 ${D}/${systemd_system_unitdir}
	install -d -m755 ${D}/${datadir}/starlingx

	install -D -m644 files/iscsi-cache.volatiles ${D}/${libdir}/tempfiles.d
	install -D -m644 files/iscsi-shutdown.service ${D}/${systemd_system_unitdir}

	install -m 0644 files/iscsid.conf ${D}/${datadir}/starlingx/stx.iscsid.conf
}

FILES_${PN}_append += " \
		${systemd_system_unitdir} \
		${datadir} \
		${libdir} \
		"
