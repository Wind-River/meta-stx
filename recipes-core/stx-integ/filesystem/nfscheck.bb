DESCRIPTION = "nfscheck"

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

	cd ${S}/filesystem/nfscheck/
	install -d -m755 ${D}/${bindir}
	install -d -m755 ${D}/${systemd_system_unitdir}

	install -D -m644 files/nfscheck.sh ${D}/${bindir}
	install -D -m644 files/nfscheck.service ${D}/${systemd_system_unitdir}


}

pkg_postinst_ontarget_${PN} () {
	/usr/bin/systemctl enable nfscheck.service> 
}	

FILES_${PN}_append += " \
		${systemd_system_unitdir} \
		"
