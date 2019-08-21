DESCRIPTION = "unittest2 is a backport of the new features added to the unittest testing framework in Python 2.7 and onwards"
LICENSE = "UNKNOWN"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=1;endline=8;md5=0cc25b5af20e1a7c68796942b021f1a4"

SRC_URI += "file://fix-yocto-configure-by-remove-argparse-dependence.patch"

SRC_URI[sha256sum] = "22882a0e418c284e1f718a822b3b022944d53d2d908e1690b319a9d3eb2c0579"

DEPENDS += "python-traceback2-native \
            python-six-native \
            python-linecache2-native \
        "

PYPI_PACKAGE = "unittest2"
inherit pypi setuptools
