DESCRIPTION = "CherryPy is a pythonic, object-oriented HTTP framework."

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=a8cbc5da4e6892b15a972a0b18622b2b"

SRCREV = "9eac6246e6c3e410ee150288e377f88f579cbbbd"
PROTOCOL = "https"
BRANCH = "master"
S = "${WORKDIR}/git"

SRC_URI = "git://github.com/cherrypy/cherrypy;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"


DEPENDS += " python python-setuptools-scm-native"
inherit setuptools distutils pkgconfig
