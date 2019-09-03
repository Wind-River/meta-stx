DESCRIPTION = "A Puppet module to manage client and server configuration for OpenLdap."

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://metadata.json;md5=4244fe391bee02e9ee7259aa7f8dda8b"

SRCREV = "480f13af6d17d1d3fcf0dc7b4bd04b49fa4099e1"
PROTOCOL = "https"
BRANCH = "master"
S = "${WORKDIR}/git"

SRC_URI = "git://github.com/torian/puppet-ldap;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

do_configure() {
	:
}

do_compile() {
	:
}

do_install() {
	cd ${S}
	install -d -m 0755 ${D}/${datadir}/puppet/modules/ldap
	cp -R ${S}/* ${D}/${datadir}/puppet/modules/ldap
}

FILES_${PN} = " ${datadir}"
