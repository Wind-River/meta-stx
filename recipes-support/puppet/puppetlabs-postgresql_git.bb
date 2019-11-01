DESCRIPTION = "A Puppet module for managing PostgreSQL databases."

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

PV = "4.8.0"
SRCREV = "d022a56b28b2174456fc0f6adc51a4b54493afad"
PROTOCOL = "https"
BRANCH = "master"
S = "${WORKDIR}/git"

SRC_URI = " \
	git://github.com/puppetlabs/puppetlabs-postgresql;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH} \
	file://postgresql/Add-gemspec.patch \
	file://postgresql/0001-Roll-up-TIS-patches.patch \
	file://postgresql/0002-remove-puppetlabs-apt-as-a-requirement.patch \
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
	install -d -m 0755 ${D}/${datadir}/puppet/modules/postgresql
	cp -r ${S}/* ${D}/${datadir}/puppet/modules/postgresql
}

FILES_${PN} += " ${datadir}"
