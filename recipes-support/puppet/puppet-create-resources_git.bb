DESCRIPTION = "puppet-boolean"

STABLE = "master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "4639819a7f3a4fa9310d2ba583c63e467df7e2c3"
S = "${WORKDIR}/git"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=6089b6bd1f0d807edb8bdfd76da0b038 "

SRC_URI = "git://github.com/puppetlabs/puppetlabs-create_resources.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

do_configure () {
	:
} 

do_compile() {
	:
}

do_install () {
	cd ${S}/
	install -d -m 0755 ${D}/${datadir}/puppet/modules/create-resources
	cp -R * ${D}/${datadir}/puppet/modules/create-resources
	# chown -R root:root ${D}/
}

FILES_${PN}_append += " \
	${datadir}/puppet/modules/create-resources \
	"
