FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " \
	file://${PN}/poky-nslcd-updates.patch;striplevel=5 \
	"
