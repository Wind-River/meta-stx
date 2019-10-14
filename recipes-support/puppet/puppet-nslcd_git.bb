DESCRIPTION = "A Puppet module for nslcd - local LDAP name service daemon."

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://Modulefile;md5=674f57ad12dfafcf6c3943f34d459ded"

SRCREV = "b8c19b1ada89865f2e50758e054583798ad8011a"
PROTOCOL = "https"
BRANCH = "master"
S = "${WORKDIR}/git"

SRC_URI = " \
	git://github.com/jlyheden/puppet-nslcd;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH} \
	" 
do_configure() {
	:
}

do_compile() {
	:
}

do_install() {
	cd ${S}
	install -d -m 0755 ${D}/${datadir}/puppet/modules/nslcd
	cp -R ${S}/* ${D}/${datadir}/puppet/modules/nslcd
}

FILES_${PN} = " ${datadir}"
