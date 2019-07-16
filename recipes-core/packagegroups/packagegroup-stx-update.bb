SUMMARY = "StarlingX/cgsc-root/stx/stx-update"
PR = "r0"

#
# packages which content depend on MACHINE_FEATURES need to be MACHINE_ARCH
#
PACKAGE_ARCH = "${MACHINE_ARCH}"


inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = " \
	packagegroup-stx-update \
	"

RDEPENDS_packagegroup-stx-update = " \
	cgcs-patch \
	enable-dev-patch \
	patch-alarm \
	tsconfig \
	requests-toolbelt \
	"
