SUMMARY = "NSS database Puppet Module"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0409d65ae3bec182108fd45c64bd0ef2"

PV = "1.0.1"
SRC_REV = "2e163a21fb80d828afede2d4be6214f1171c4887"
PROTOCOL = "https"
BRANCH = "master"
S = "${WORKDIR}/git"

SRC_URI = "git://github.com/rcritten/puppet-nssdb.git;protocol=${PROTOCOL};rev=${SRC_REV};branch=${BRANCH} \
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

RUBY_INSTALL_GEMS = "puppet-nssdb-${PV}.gem"

do_install_append() {
	install -d -m 0755 ${D}/${datadir}/puppet/modules/nssdb
	cp -R ${S}/* ${D}/${datadir}/puppet/modules/nssdb
}

FILES_${PN} += " ${datadir}"
