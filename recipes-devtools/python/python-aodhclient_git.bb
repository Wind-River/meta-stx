DESCRIPTION = "client library for Aodh built on the Aodh API"
HOMEPAGE = "https://launchpad.net/python-aodhclient"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1dece7821bf3fd70fe1309eaa37d52a2"

SRCREV = "d60577d671ce8a9f571e0bf98d19dc774700cf7b"
SRCNAME = "python-aodhclient"
BRANCH = "stable/train"
PROTOCOL = "https"
PV = "1.2.0+git${SRCPV}"
S = "${WORKDIR}/git"

SRC_URI = "git://github.com/openstack/${SRCNAME}.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"
	
inherit setuptools monitor rmargparse

DEPENDS += " \
        python-pip \
        python-pbr-native\
        "

# Satisfy setup.py 'setup_requires'
DEPENDS += " \
        python-pbr-native \
        "

RDEPENDS_${PN} += " \
        python-pbr \
        python-cliff \
        python-oslo.i18n \
        python-oslo.serialization \
        python-oslo.utils \
        python-keystoneauth1 \
        python-six \
        python-osc-lib \
	python-pyparsing \
	"


do_install_append() {
	:
}

FILES_${PN} += " \
	"
