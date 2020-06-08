
require config-common.inc

S = "${S_DIR}/sysinv/cgts-client/cgts-client"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1dece7821bf3fd70fe1309eaa37d52a2"

DEPENDS += " \
	python-pbr-native \
	"

RDEPENDS_${PN}_append = " \
	python-prettytable \
	bash-completion \
	python-neutronclient \
	python-keystoneclient \
	python-six \
	python-httplib2 \
	"

inherit setuptools
