FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
	file://${PN}/poky-firewall-updates.patch;striplevel=5 \
	"
