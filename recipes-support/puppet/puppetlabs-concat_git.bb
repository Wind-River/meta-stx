DESCRIPTION = "Construct files from multiple fragments."

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRCREV = "803f95830ebe58485c03abdae46801c12ca89be3"
PROTOCOL = "https"
BRANCH = "master"
S = "${WORKDIR}/git"
PV = "2.2.0"

SRC_URI = "git://github.com/puppetlabs/puppetlabs-concat.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH} \
    file://puppetlabs-concat/Add-gemspec.patch \
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

RUBY_INSTALL_GEMS = "puppetlabs-concat-${PV}.gem"
