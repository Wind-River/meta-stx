DESCRIPTION = "woker-utils"

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "70609a3d55e5b7d2be82667fc35792505f9013c4"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://opendev.org/starlingx/config.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

inherit externalsrc
EXTERNALSRC_pn-${PN}="${EXTERNALREPO}/stx-config"

RDEPENDS_${PN} += " bash"

do_configure () {
	:
} 

do_compile() {
	cd ${S}/worker-utils/worker-utils
	oe_runmake all
}

do_install () {
	cd ${S}/worker-utils/worker-utils
	oe_runmake BINDIR=${D}/${bindir} GOENABLEDDIR=${D}/${sysconfdir}/goenabled.d \
		INITDDIR=${D}/${sysconfdir}/init.d PLATFORMCONFDIR=${D}/${sysconfdir}/platform \
		SYSTEMDDIR=${D}/${systemd_system_unitdir} install
}

FILES_${PN}_append += " ${bindir} \
		${sysconfdir} \
		${systemd_system_unitdir} \
		"
