FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " \
	file://${PN}/poky-puppi-updates.patch;striplevel=5 \
	file://${PN}/puppet-puppi-poky.patch \
	"
