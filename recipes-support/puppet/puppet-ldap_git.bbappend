FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " \
	file://${PN}/poky-ldap-updates.patch;striplevel=5 \
	file://${PN}/puppet-ldap-poky.patch \
	"
