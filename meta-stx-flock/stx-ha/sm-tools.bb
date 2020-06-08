
S = "${S_DIR}/service-mgmt-tools/sm-tools"

require ha-common.inc

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

RDEPENDS_${PN}_append = " python"

inherit setuptools
