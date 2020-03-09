FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " \
	file://puppet-horizon/0001-Update-memcached-dependency.patch \
	file://puppet-horizon/puppet-horizon-poky.patch \
	"
