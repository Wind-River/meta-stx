FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " \
	file://${PN}/0001-puppet-dnsmasq-Kilo-quilt-patches.patch;striplevel=5 \
	file://${PN}/0002-Fixing-mismatched-permission-on-dnsmasq-conf.patch;striplevel=5 \
	file://${PN}/0003-Support-management-of-tftp_max-option.patch;striplevel=5 \
	file://${PN}/0004-Enable-clear-DNS-cache-on-reload.patch;striplevel=5 \
	file://${PN}/poky-dnsmasq-updates.patch;striplevel=5 \
	"
