DESCRIPTION = "d2to1 (the ‘d’ is for ‘distutils’) allows using distutils2-like setup.cfg files for a package’s metadata with a distribute/setuptools setup.py script. It works by providing a distutils2-formatted setup.cfg file containing all of a package’s metadata, and a very minimal setup.py which will slurp its arguments from the setup.cfg."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=046523829184aac3703a4c60c0ae2104"

SRCREV = "dc83fc6576b6463dcc77a0c101475a2a71ed655a"
PROTOCOL = "https"
BRANCH = "master"
S = "${WORKDIR}/git"

SRC_URI = "git://github.com/influxdata/influxdb-python.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"


DEPENDS += " python"
inherit setuptools distutils pkgconfig
