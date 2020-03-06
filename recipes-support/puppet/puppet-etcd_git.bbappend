FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " \
	file://${PN}/puppet-etcd-poky.patch \
	"
