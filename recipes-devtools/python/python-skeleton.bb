DESCRIPTION = "__PKG_DESCRIPTION__"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

#SRC_URI[md5sum] = ""
#SRC_URI[sha256sum] = ""

SRCREV = "__SRC_REV"
PROTOCOL = "https"
BRANCH = "master"
S = "${WORKDIR}/git"

SRC_URI = "git://SRC_URL;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"


DEPENDS += " python"
inherit setuptools distutils pkgconfig
