DESCRIPTION = "stx-metal"

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "d599a0f4cec538b60f7d953cd89e0603cc1bdb5a"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://opendev.org/starlingx/metal.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH} \
		file://0001-mtce-compute-dont-install-empty-directory-unless-nee.patch \
		file://0001-mtce-control-dont-install-empty-directory-unless-nee.patch \
		file://0001-mtce-storage-dont-install-empty-directory-unless-nee.patch \
		file://0001-Use-snprintf-to-avoid-overflowing-amon.tx_buf.patch \
		file://0001-Use-LDFLAGS-when-linking-and-pass-flags-down-to-subm.patch \
		"

inherit setuptools


DEPENDS = " \
	python \
	python-pbr-native \
	stx-fault \
	openssl \
	libevent \
	json-c \
	"
RDEPENDS_${PN}_append = " bash"

# mtce depends on mtce-common
# TODO: Define tasks and handle dependencies accordingly

require mtce.inc
require inventory.inc
require mtce-common.inc
require mtce-compute.inc
require mtce-control.inc
require mtce-storage.inc
require python-inventoryclient.inc

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
# install default config files
}

FILES_${PN} = " "
FILES_${PN}-dbg_append += " "
FILES_${PN}-staticdev_append = " "
FILES_${PN}-dev_append = " "
