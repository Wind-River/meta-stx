DESCRIPTION = "fm-doc"

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
EXTERNALSRC_pn-${PN}="${EXTERNALREPO}/stx-fault"

require stx-fault.inc

DEPENDS = " fm-common"
# RDEPENDS_${PN} += " bash"

do_configure () {
	:
} 

do_compile() {
	cd ${S}/fm-mgr/sources/
	oe_runmake -e
}

do_install () {
	cd ${S}/fm-mgr/sources/
	install -d -m0755 ${D}/${systemd_system_unitdir} 
	oe_runmake -e DESTDIR=${D} BINDIR=${bindir} \
		LIBDIR=${libdir} UNITDIR=${systemd_system_unitdir} \
			SYSCONFDIR=${sysconfdir} \
			install
}

FILES_${PN}_append += " ${libdir} \
			${systemd_system_unitdir} \
			"

