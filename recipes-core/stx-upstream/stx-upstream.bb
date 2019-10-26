DESCRIPTION = "stx-upstream"

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "45843738786449a77cd00b9f796031300eb18636"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://opendev.org/starlingx/upstream.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"


require stx-ocf-scripts.inc
require rabbitmq-server-config.inc

do_configure () {
	:
} 

do_compile() {
	:
}

do_install () {
	:
}


FILES_${PN} = " "
