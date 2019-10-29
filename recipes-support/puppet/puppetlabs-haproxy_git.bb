SUMMARY = "Configures HAProxy servers and manages the configuration of backend member servers."

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

PV = "1.5.0"
SRCREV = "3ac513c0ceb3bcfe35dd2936875189ccfc991a34"
PROTOCOL = "https"
BRANCH = "master"
S = "${WORKDIR}/git"

SRC_URI = "git://github.com/puppetlabs/puppetlabs-haproxy;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH} \
	file://puppetlabs-haproxy/Add-gemspec.patch \
	file://puppetlabs-haproxy/0001-Roll-up-TIS-patches.patch \
	file://puppetlabs-haproxy/0002-disable-config-validation-prechecks.patch \
	file://puppetlabs-haproxy/0003-Fix-global_options-log-default-value.patch \
	file://puppetlabs-haproxy/0004-Stop-invalid-warning-message \
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

RUBY_INSTALL_GEMS = "puppetlabs-haproxy-${PV}.gem"

