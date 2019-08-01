DESCRIPTION = "Smart replacement for plain tuple used o __version__"
LICENSE = "LGPLv3"
LIC_FILES_CHKSUM = "file://setup.py;md5=ab73495c876271f67ef5e34d4045262b"


SRCREV = "833890e18a92cecad3134dacbdecb0cf6cc978ed"
PROTOCOL = "https"
BRANCH = "master"
S = "${WORKDIR}/git"

SRC_URI = "git://github.com/zyga/versiontools.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

# PYPI_PACKAGE = "versiontools"
inherit setuptools distutils
