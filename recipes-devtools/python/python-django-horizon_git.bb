DESCRIPTION = "Simple database sharding (horizontal partitioning) library for Django applications."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=50eaaae97522b223bb0b67befd823e69"

#SRC_URI[md5sum] = ""
#SRC_URI[sha256sum] = ""

SRCREV = "9b413570111a5d57f1ea1d125ca8a1768565eb88"
PROTOCOL = "https"
BRANCH = "master"
S = "${WORKDIR}/git"

SRC_URI = "git://github.com/uncovertruth/django-horizon;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"


DEPENDS += " python"
inherit setuptools distutils pkgconfig
