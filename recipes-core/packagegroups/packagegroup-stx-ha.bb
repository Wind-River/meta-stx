SUMMARY = "StarlingX/cgsc-root/stx/stx-ha"
PR = "r0"

#
# packages which content depend on MACHINE_FEATURES need to be MACHINE_ARCH
#
PACKAGE_ARCH = "${MACHINE_ARCH}"


inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = " \
	packagegroup-stx-ha\
	"

RDEPENDS_packagegroup-stx-ha = " \
	sm-api \
	sm \
	sm-client \
	libsm-common \
	sm-common-libs \
	sm-db \
	sm-tools \
	sm-eru \
	"
