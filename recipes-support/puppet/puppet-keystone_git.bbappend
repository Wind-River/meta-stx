FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " \
	file://${PN}/0001-pike-rebase-squash-titanium-patches.patch \
	file://${PN}/0002-remove-the-Keystone-admin-app.patch \
	file://${PN}/0003-remove-eventlet_bindhost-from-Keystoneconf.patch \
	file://${PN}/0004-escape-special-characters-in-bootstrap.patch \
	file://${PN}/0005-Add-support-for-fernet-receipts.patch \
	file://${PN}/0006-workaround-Adjust-keystone-naming-to-poky.patch \
	"
