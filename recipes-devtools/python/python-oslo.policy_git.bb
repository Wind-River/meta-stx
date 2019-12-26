DESCRIPTION = "Oslo policy library"
HOMEPAGE = "https://github.com/openstack/oslo.policy"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1dece7821bf3fd70fe1309eaa37d52a2"

SRCREV = "f319967738e671e1400052e39116ff3c23b9f9a0"
SRCNAME = "oslo.policy"
PROTOCOL = "https"
BRANCH = "stable/stein"
S = "${WORKDIR}/git"
PV = "1.38.0+git${SRCPV}"

SRC_URI = "git://github.com/openstack/${SRCNAME}.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

inherit setuptools

DEPENDS += " \
        python-pip \
        python-pbr-native \
        "

RDEPENDS_${PN} += " \
        python-oslo.config \
	python-oslo.i18n \
	python-oslo.serialization \
	python-oslo.utils \
	python-six \
	python-pyyaml \
	python-requests \
	"
