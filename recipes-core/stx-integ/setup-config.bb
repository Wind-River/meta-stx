DESCRIPTION = "setup-config"

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
	cd ${S}/base/setup-config
	install -d ${D}/${datadir}/starlingx
	install -d ${D}/${sysconfdir}/profile.d

	install -m640 files/motd ${D}/${datadir}/starlingx/stx.motd
	install -m640 files/prompt.sh ${D}/${sysconfdir}/profile.d/prompt.sh
	install -m640 files/custom.sh ${D}/${sysconfdir}/profile.d/custom.sh

}

FILES_${PN}_append += " \
	${datadir} \
	"
		
