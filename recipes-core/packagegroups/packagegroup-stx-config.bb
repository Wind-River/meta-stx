SUMMARY = "StarlingX/cgsc-root/stx/stx-config"
PR = "r0"

#
# packages which content depend on MACHINE_FEATURES need to be MACHINE_ARCH
#
PACKAGE_ARCH = "${MACHINE_ARCH}"


inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = " \
	packagegroup-stx-config \
	"

RDEPENDS_packagegroup-stx-config = " \
	config-gate \
	controllerconfig \
	puppet-manifests \
	puppet-modules-wrs \
	storageconfig \
	worker-utils \
	workerconfig \
	pm-qos-mgr \
	sysinv \
	"
