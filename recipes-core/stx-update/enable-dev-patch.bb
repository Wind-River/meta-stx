DESCRIPTION = "enable-dev-patch"

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "70609a3d55e5b7d2be82667fc35792505f9013c4"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://opendev.org/starlingx/config.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

require stx-update.inc
inherit setuptools


do_configure () {
	:
} 

do_compile() {
	:
}

do_install () {
	cd ${S}/enable-dev-patch/
	install -m 755 -d ${D}/${sysconfdir}/pki/wrs
	install -m 444 enable-dev-patch/dev_certificate_enable.bin ${D}/${sysconfdir}/pki/wrs

}

# pkg_postinst_ontarget_${PN} () { }

FILES_${PN}_append += " "
