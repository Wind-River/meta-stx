FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " \
	file://${BPN}/puppet-barbican-fix-the-pkg-and-service-names-for-poky-stx.patch \
	"
