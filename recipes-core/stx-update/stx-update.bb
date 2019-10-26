DESCRIPTION = "stx-update"

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "25dee38f0ba4abb9a4c27330bfa4032cdc136965"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://opendev.org/starlingx/update.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

DEPENDS = " \
	python \
	python-pbr-native \
	"

RDEPENDS_${PN} += " requests-toolbelt"

require cgcs-patch.inc
require enable-dev-patch.inc
require patch-alarm.inc
require tsconfig.inc

do_configure () {
	:
} 

do_compile() {
	:
}

do_install () {
	:
}

pkg_postinst_ontarget_${PN} () { 
}

FILES_${PN} = " "
