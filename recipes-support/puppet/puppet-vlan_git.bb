SUMMARY = "Puppet module for OpenStack Barbican"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f05b73a1f91c0e30dece85ed11819aca"

PV = "0.1.0"
SRC_REV = "c937de75c28e63fba8d8738ad6a5f2ede517e53d"
PROTOCOL = "https"
BRANCH = "master"
S = "${WORKDIR}/git"

SRC_URI = "git://github.com/derekhiggins/puppet-vlan.git;protocol=${PROTOCOL};rev=${SRC_REV};branch=${BRANCH} \
	file://${PN}/Add-gemspec.patch \
	file://${PN}/metadata.json.patch \
	"

inherit ruby

DEPENDS += " \
	ruby \
	facter \
	"

RDEPENDS_${PN} += " \
	ruby \
	facter \
	puppet \
	"

RUBY_INSTALL_GEMS = "${PN}-${PV}.gem"

do_install_append() {
	install -d -m 0755 ${D}/${datadir}/puppet/modules/vlan
	cp -R ${S}/* ${D}/${datadir}/puppet/modules/vlan
}

FILES_${PN} += " ${datadir}"
