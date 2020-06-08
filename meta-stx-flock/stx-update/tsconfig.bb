
require update-common.inc

S = "${S_DIR}/tsconfig/tsconfig"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

RDEPENDS_${PN}_append = " bash"

inherit setuptools
do_install_append () {
	install -m 755 -d ${D}/${bindir}
	install -m 500 scripts/tsconfig ${D}/${bindir}/

}
