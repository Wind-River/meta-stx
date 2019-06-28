SUMMARY = "StarlingX/cgsc-root/stx/stx-config"
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

RDEPENDS_packagegroup-stx-metal = " \
	inventory \
	mtce \
	mtce-common \
	mtce-compute \
	mtce-control \
	mtce-storage \
	python-inventoryclient \
	"
