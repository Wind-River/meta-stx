
S = "${S_DIR}/service-mgmt-client/sm-client"

require ha-common.inc

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = " \
	file://LICENSE;md5=1dece7821bf3fd70fe1309eaa37d52a2 \
	file://${S_DIR}/service-mgmt-client/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
	"

RDEPENDS_${PN}_append = " \
	python \
	python-six \
	"

inherit setuptools
