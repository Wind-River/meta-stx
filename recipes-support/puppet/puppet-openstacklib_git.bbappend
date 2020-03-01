FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " \
	file://puppet-openstacklib/0001-Roll-up-TIS-patches.patch \
	file://puppet-openstacklib/poky-openstacklib-updates.patch \
	"
