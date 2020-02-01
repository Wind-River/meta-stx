FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " \
	file://${PN}/0001-puppet-dnsmasq-Kilo-quilt-patches.patch \
	file://${PN}/0002-Fixing-mismatched-permission-on-dnsmasq-conf.patch \
	file://${PN}/0003-Support-management-of-tftp_max-option.patch \
	file://${PN}/0004-Enable-clear-DNS-cache-on-reload.patch \
	"
