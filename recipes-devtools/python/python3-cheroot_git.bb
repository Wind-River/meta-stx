SUMMARY = " Lang - Python Language Constraints"
DESCRIPTION = "\
	Lang is a Python module that allows enforcing programming language constraints. Lang was \
	built using a Java like mindset, so many of the constraints that are supported are mirrors \
	of constraints in the Java programming language. \
	"


LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=beeffd9dfcc746ed5a91921f1acc2746"

SRCREV = "c7ca7ff0bcebb53e1bed783280a3bb5db35f900f"
PROTOCOL = "https"
BRANCH = "master"
S = "${WORKDIR}/git"

SRC_URI = "git://github.com/cherrypy/cheroot.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"


DEPENDS += " \
	python3 \
	python3-setuptools-scm-native \
	python3-setuptools-scm-git-archive-native \
	"

RDEPENDS_${PN} += " \
	python3-email \
	python3-fcntl \
	python3-io \
	python3-logging \
	python3-unixadmin \
	python3-pyopenssl \
	python3-six \
	"

inherit setuptools3 distutils3 pkgconfig
