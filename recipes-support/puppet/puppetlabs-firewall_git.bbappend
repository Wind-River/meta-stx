FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
	file://${BPN}/puppet-firewall-updates-for-poky-stx.patch \
	"

inherit openssl10
