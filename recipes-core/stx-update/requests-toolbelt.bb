DESCRIPTION = "requests-toolbelt"

STABLE = "master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "a1425bf1c962a93d751ac1b9f8a7e8070d6a3c0e"
S = "${WORKDIR}/git"
PV = "0.5.1"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=71760e0f1dda8cff91b0bc9246caf571"

SRC_URI = "git://github.com/requests/toolbelt.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

DEPENDS = " \
	python \
	python-pbr-native \
	"

inherit setuptools

RDEPENDS_${PN} += " bash"
