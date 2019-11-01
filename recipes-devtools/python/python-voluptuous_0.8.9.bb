DESCRIPTION = "Voluptuous is a Python data validation library"
HOMEPAGE = "https://pypi.python.org/pypi/voluptuous/"
SECTION = "devel/python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=9855ba150f2edb00d8e7a41554896ffb"

SRC_URI[md5sum] = "dc02da0fc6c2b87b2092400da9598e39"
SRC_URI[sha256sum] = "6e1562d51b7ff77692509479d9e2d8ea4c00294bcc1f0236605a3c86923e04b4"

inherit setuptools pypi

SRC_URI += " file://voluptuous/0001-add-NotIn-validation.patch"
RDEPENDS_${PN} = " \
        "
