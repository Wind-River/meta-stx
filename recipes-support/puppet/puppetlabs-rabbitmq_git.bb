SUMMARY = "Installs, configures, and manages RabbitMQ."

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

PV = "5.6.0"
SRCREV = "5ac45dedd9b409c9efac654724bc74867cb9233b"
PROTOCOL = "https"
BRANCH = "master"
S = "${WORKDIR}/git"

SRC_URI = "git://github.com/puppetlabs/puppetlabs-rabbitmq;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH} \
	file://puppetlabs-rabbitmq/Add-gemspec.patch \
	file://puppetlabs-rabbitmq/0001-Roll-up-TIS-patches.patch \
	file://puppetlabs-rabbitmq/0002-Changed-cipher-specification-to-openssl-format.patch \
	file://puppetlabs-rabbitmq/0003-Eliminate-Puppet-4-deprecation-warnings.patch \
	file://puppetlabs-rabbitmq/0004-Partially-revert-upstream-commit-f7c3a4a637d59f3065d.patch \
	file://puppetlabs-rabbitmq/0005-Remove-the-rabbitmq_nodename-fact.patch \
	file://puppetlabs-rabbitmq/0006-Set-rabbitmq-dependency-to-nanliu-staging.patch \
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

RUBY_INSTALL_GEMS = "puppetlabs-rabbitmq-${PV}.gem"

do_install_append() {
	install -d -m 0755 ${D}/${datadir}/puppet/modules/rabbitmq
	cp -r ${S}/* ${D}/${datadir}/puppet/modules/rabbitmq
}

FILES_${PN} += " ${datadir}"
