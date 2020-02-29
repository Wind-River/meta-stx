DESCRIPTION = "stx-metal"

# STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "r/stx.3.0"
SRCREV = "be3cf4eeb50eef55910cf9c73ea47c168005ad64"
S = "${WORKDIR}/git"
PV = "1.0.0"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

#TODO:
#3b83ef96387f14655fc854ddc3c6bd57  ./LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./installer/pxe-network-installer/pxe-network-installer/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./kickstart/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./mtce-common/src/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./mtce-compute/src/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./mtce-control/src/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./mtce-storage/src/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./mtce/src/LICENSE
#1dece7821bf3fd70fe1309eaa37d52a2  ./inventory/inventory/LICENSE
#1dece7821bf3fd70fe1309eaa37d52a2  ./python-inventoryclient/inventoryclient/LICENSE


SRC_URI = "git://opendev.org/starlingx/metal.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH} \
		file://0001-mtce-compute-dont-install-empty-directory-unless-nee.patch \
		file://0001-mtce-control-dont-install-empty-directory-unless-nee.patch \
		file://0001-mtce-storage-dont-install-empty-directory-unless-nee.patch \
		file://0001-Use-snprintf-to-avoid-overflowing-amon.tx_buf.patch \
		file://0001-Use-LDFLAGS-when-linking-and-pass-flags-down-to-subm.patch \
		file://0001-stx-metal-remove-argparse-requirement-from-inventory.patch \
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

require mtce.inc
require inventory.inc
require mtce-common.inc
require mtce-compute.inc
require mtce-control.inc
require mtce-storage.inc
require python-inventoryclient.inc
require pxe-network-installer.inc
# require kickstart.inc

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
