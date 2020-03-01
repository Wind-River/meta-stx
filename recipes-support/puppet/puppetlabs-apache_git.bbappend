FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " \
	file://${PN}/poky-apache-updates.patch \
	"
#SRC_URI += " \
#	file://${PN}/0001-maint-Fix-conditional-in-vhost-ssl-template.patch \
#	file://${PN}/0002-maint-Fix-the-vhost-ssl-template-correctly-this-time.patch \
#	"
