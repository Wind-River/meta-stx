SUMMARY = "StarlingX/cgsc-root/stx/stx-metal"
PR = "r0"

#
# packages which content depend on MACHINE_FEATURES need to be MACHINE_ARCH
#
PACKAGE_ARCH = "${MACHINE_ARCH}"


inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = " \
	packagegroup-stx-metal \
	"

# The mtce-common contains dev components only and since
# the dev components endup in stx-metal-dev package, not 
# adding mtce-common to packagegroup.

RDEPENDS_packagegroup-stx-metal = " \
	inventory \
	mtce \
	mtce-compute \
	mtce-control \
	mtce-storage \
	python-inventoryclient \
	"

