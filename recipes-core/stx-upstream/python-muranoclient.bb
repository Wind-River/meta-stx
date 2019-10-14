DESCRIPTION = "python-muranoclient"
STABLE = "master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "70b4392c7f8524ac25dbf3ab0feb3ac4127c1ecf"
S = "${WORKDIR}/git"
PV = "1.1.1"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=1dece7821bf3fd70fe1309eaa37d52a2"

SRC_URI = "git://github.com/openstack/python-muranoclient.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

DEPENDS += " \
	python \
	python-pbr-native \
	"

inherit setuptools

RDEPENDS_${PN}_append = " bash"
