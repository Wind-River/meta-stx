SUMMARY = "The mysql module installs, configures, and manages the MySQL service."

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRCREV = "817844c08573177f73be71049b521ec67c134951"
PROTOCOL = "https"
BRANCH = "master"
S = "${WORKDIR}/git"
PV = "10.2.0"

SRC_URI = "git://github.com/puppetlabs/puppetlabs-mysql.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH} \
    file://puppetlabs-mysql/Add-gemspec.patch \
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

RUBY_INSTALL_GEMS = "puppetlabs-mysql-${PV}.gem"
