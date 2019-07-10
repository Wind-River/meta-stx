DESCRIPTION = "stx-config"

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "70609a3d55e5b7d2be82667fc35792505f9013c4"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://opendev.org/starlingx/config.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

DEPENDS = "\
	puppet \
	python \
	python-pbr-native \
	"

inherit setuptools

require config-gate.inc
require controllerconfig.inc
require pm-qos-mgr.inc
require puppet-modules-wrs.inc
require puppet-manifests.inc
require storageconfig.inc
require sysinv.inc
require workerconfig.inc
require worker-utils.inc

do_configure() {
	:
} 

do_compile() {
	:
}

do_install() {
	:
}

FILES_${PN} = " "
