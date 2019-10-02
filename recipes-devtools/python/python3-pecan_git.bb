CRIPTION = "WSGI object-dispatching web framework"

HOMEPAGE = "https://pypi.python.org/pypi/pecan/"
SECTION = "devel/python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d846877d24bbb3d7a00a985c90378e8c"

SRCREV = "da15e06d783e2cf569b39ba506e68e4e1e85568d"
PROTOCOL = "https"
BRANCH = "master"
S = "${WORKDIR}/git"

SRC_URI = "git://github.com/pecan/pecan.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"


inherit setuptools3 distutils3

RDEPENDS_${PN} = "python3-mako \
		  python3-six \
		  python3-logutils \
		  python3-webtest  \
		  "
