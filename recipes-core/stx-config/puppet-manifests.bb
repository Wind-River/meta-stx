DESCRIPTION = "puppet-manifests"

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

DEPENDS = " \
	puppet \
	"
RDEPENDS_${PN} += " bash"

do_configure () {
	:
} 

do_compile() {
	:
}

do_install () {
	cd ${S}/puppet-manifests/src
	oe_runmake BINDIR=${D}/${bindir} \
		CONFIGDIR=${D}/${sysconfdir}/puppet/ \
		MODULEDIR=${D}/${datadir}/puppet/modules -f Makefile install
}

FILES_${PN}_append += " ${sysconfdir} \
		${datadir} \
		"
