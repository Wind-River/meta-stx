DESCRIPTION = "python-adhclient"
STABLE = "master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "6d03e61986418a3a95635405ae65ae9f28a5c61e"
S = "${WORKDIR}/git"
PV = "1.2.0"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=1dece7821bf3fd70fe1309eaa37d52a2"

SRC_URI = "git://github.com/openstack/python-aodhclient.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

DEPENDS += " \
	python \
	python-pbr-native \
	"

inherit setuptools
