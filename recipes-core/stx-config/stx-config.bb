
DESCRIPTION = "stx-config"

PROTOCOL = "https"
BRANCH = "r/stx.3.0"
SRCREV = "b51e4ef738e0020f11f164fd3f86399872caf3c6"
S = "${WORKDIR}/git"
PV = "1.0.0"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = " \
	git://opendev.org/starlingx/config.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH} \
	file://0001-stx-config-remove-argparse-requirement-from-sysinv.patch \
	file://0002-cgts-client-handle-exceptions-other-than-CalledProcessErr.patch \
	file://sriovpf-bring-up.patch \
	"

DEPENDS = "\
	puppet \
	python \
	python-pbr-native \
	"

inherit setuptools distutils

# Other packages depend on tsconfig build it first
require tsconfig.inc
require config-gate.inc
require controllerconfig.inc
require storageconfig.inc
require cgts-client.inc
require sysinv.inc
require sysinv-agent.inc
require workerconfig.inc

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

DISTRO_FEATURES_BACKFILL_CONSIDERED_remove = "sysvinit"
