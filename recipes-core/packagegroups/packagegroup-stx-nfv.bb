SUMMARY = "StarlingX/cgsc-root/stx/stx-nfv"
PR = "r0"

#
# packages which content depend on MACHINE_FEATURES need to be MACHINE_ARCH
#
PACKAGE_ARCH = "${MACHINE_ARCH}"


inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = " \
	packagegroup-stx-nfv \
	"

RDEPENDS_packagegroup-stx-nfv = " \
	nfv-common \
	nfv-plugins \
	nfv-tools \
	nfv-vim \
	nfv-client \
	mtce-guest \
	nova-api-proxy \
	"
