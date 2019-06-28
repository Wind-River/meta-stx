DESCRIPTION = "stx-nfv"



STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "cdd6c334d9d1d6c0f4de344fff8ef2af28c76e56"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"



SRC_URI = "git://opendev.org/starlingx/nfv.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

#AUTOTOOLS_SCRIPT_PATH = ""
#DEPENDS = ""
#inherit autotools
#inherit pkgconfig
#inherit python-dir
#EXTRA_OECONF = ""
#do_configure_append () { }
#do_install_append() { }
#pkg_postinst_ontarget_${PN} () { }
