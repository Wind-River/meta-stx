DESCRIPTION = "ldap3 is a strictly RFC 4510 conforming LDAP V3 pure Python client library. The same codebase runs in Python 2, Python 3, PyPy and PyPy3."

LICENSE = "UNKNOWNi|BSD"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=32be5282b8f7cafe30b89d2980fbc7d0"

#SRC_URI[md5sum] = ""
#SRC_URI[sha256sum] = ""

SRCREV = "08810e435392a1f1a96d5e65bb444b9cb9e96ae3"
PROTOCOL = "https"
BRANCH = "master"
S = "${WORKDIR}/git"

SRC_URI = "git://github.com/cannatag/ldap3;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"


DEPENDS += " python"
inherit setuptools distutils pkgconfig
