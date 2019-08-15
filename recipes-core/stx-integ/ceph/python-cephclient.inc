
DESCRIPTION = "A client library in python for the Ceph REST API."
HOMEPAGE = "https://github.com/dmsimard/python-cephclient"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=41687b590435621fc0676ac02c51154f"

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "52807307cece6ff46c2713118d7d8b392a4126fb"
S = "${WORKDIR}/git/ceph/python-cephclient/python-cephclient"
PV = "13.2.2.0"

SRC_URI = "git://opendev.org/starlingx/integ.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

inherit setuptools 

DEPENDS += " \
        python-pip \
        "

RDEPENDS_${PN} += " \
        python-lxml \
        python-requests \
        python-cython \
        "
