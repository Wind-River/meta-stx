FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " \
	file://${PN}/0001-Stx-uses-nanliu-staging-module.patch \
	file://${PN}/puppet-mysql-poky.patch \
	file://${PN}/puppet-mysqltuner-adjust-path.patch \
	"
