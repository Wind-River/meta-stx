DESCRIPTION = "stx-integ"



STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "8decb63402ae185f7110845bcca845e0b3bd7447"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

#AUTOTOOLS_SCRIPT_PATH = ""


SRC_URI = "git://opendev.org/starlingx/integ.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"
#DEPENDS = ""


#inherit autotools
#inherit pkgconfig
#inherit python-dir
#EXTRA_OECONF = ""
#do_configure_append () { }
#do_install_append() { }
#pkg_postinst_ontarget_${PN} () { }
