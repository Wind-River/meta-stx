SUMMARY = "Manage Linux kernel modules with Puppet"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0e5ccf641e613489e66aa98271dbe798"

PV = "2.1.0"
SRC_REV = "0d69a96e8d0d3a08da0d5f476c733134df4fb9ee"
PROTOCOL = "https"
BRANCH = "master"
S = "${WORKDIR}/git"

SRC_URI = "git://github.com/camptocamp/puppet-kmod;protocol=${PROTOCOL};rev=${SRC_REV};branch=${BRANCH} \
	file://${PN}/Add-gemspec.patch \
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

RUBY_BUILD_GEMS = "camptocamp-kmod.gemspec"
RUBY_INSTALL_GEMS = "camptocamp-kmod-${PV}.gem"

do_install_append() {
	install -d -m 0755 ${D}/${datadir}/puppet/modules/kmod
	cp -R ${S}/* ${D}/${datadir}/puppet/modules/kmod
}

FILES_${PN} += " ${datadir}"
