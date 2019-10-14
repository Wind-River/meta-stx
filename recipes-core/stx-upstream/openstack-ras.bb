DESCRIPTION = "openstack-ras"

STABLE = "master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "66619fd22e98b68da89fed1049fe825104110f2e"
S = "${WORKDIR}/git"
# PV = "git"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://COPYING;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://github.com/madkiss/openstack-resource-agents.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

do_configure () {
	:
} 

do_compile () {
	:
}

do_install() {
	cd ${S}
	oe_runmake -e INSTALL="install" DESTDIR="${D}" install
}

FILES_${PN} = " ${libdir}"
