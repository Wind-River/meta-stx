DESCRIPTION = "puppet-dnsmasq"

STABLE = "master"
PROTOCOL = "https"
BRANCH = "master"
# SRCREV = "cff07e90890662972c97684a2baee964f68ff3ed"
SRCREV = "89759de7d7bb45fb4527ff0aa5f75f258fd0cae9"
S = "${WORKDIR}/git"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=fa818a259cbed7ce8bc2a22d35a464fc"

SRC_URI = "git://github.com/netmanagers/puppet-dnsmasq;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

RDEPENDS_${PN}_append = " \
	puppet \
	"

do_configure () {
	:
} 

do_compile() {
	:
}

do_install () {
	cd ${S}/
	install -d -m 0755 ${D}/${datadir}/puppet/modules/dnsmasq
	cp -R * ${D}/${datadir}/puppet/modules/dnsmasq/
}

FILES_${PN}_append += " \
	${datadir}/puppet/modules/dnsmasq/ \
	"
