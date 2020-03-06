FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " \
	file://${PN}/poky-puppet-updates.patch \
	file://${PN}/puppet-poky-yum.patch \
	"
