DESCRIPTION = "Python client for containers service"
HOMEPAGE = "https://github.com/openstack/python-magnumclient"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d2794c0df5b907fdace235a619d80314"

SRCREV = "c991639a6b24c34350945d653c2620b7d9c42d10"
SRCNAME = "python-magnumclient"
PROTOCOL = "https"
BRANCH = "master"
S = "${WORKDIR}/git"
PV = "2.0.0+git${SRCPV}"

SRC_URI = "git://github.com/openstack/${SRCNAME}.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

DEPENDS += " \
        python-pip \
        python-pbr-native \
        "

# Satisfy setup.py 'setup_requires'
DEPENDS += " \
        python-pbr-native \
        "

RDEPENDS_${PN} +=" \
        python-pbr \
        python-babel \
        python-oslo.config \
        python-oslo.i18n \
        python-oslo.serialization \
        python-oslo.utils \
        python-iso8601 \
        python-requests \
        python-keystoneclient \
        python-pyyaml \
        python-stevedore \
        python-six \
	"

inherit setuptools rmargparse
