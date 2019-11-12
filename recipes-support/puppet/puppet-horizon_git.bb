SUMMARY = "Intended managing the entirety of horizon."
DESCRIPTION = " \
	The horizon module is a thorough attempt to make Puppet capable of \
	managing the entirety of horizon. Horizon is a fairly classic django \
	application, which results in a fairly simply Puppet module.\
	"

# HOMEPAGE = "https://github.com/openstack/puppet-horizon"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=12a15a9ebddda7d856c783f745e5ee47"

PV = "11.5.0"
SRC_REV = "d75706e38fdf63f9c3174a526a7d07799390dfeb"
PROTOCOL = "https"
BRANCH = "stable/pike"
S = "${WORKDIR}/git"

SRC_URI = "git://github.com/openstack/puppet-horizon.git;protocol=${PROTOCOL};rev=${SRC_REV};branch=${BRANCH} \
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

RUBY_INSTALL_GEMS = "puppet-horizon-${PV}.gem"

do_install_append() { 
	install -d -m 0755 ${D}/${datadir}/puppet/modules/horizon
	cp -R ${S}/* ${D}/${datadir}/puppet/modules/horizon
}

FILES_${PN} += " ${datadir}"
