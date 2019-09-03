DESCRIPTION = "A Puppet module to map files to resources and back."

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b43880e14353cbc12cf3981fb93a0944"

SRCREV = "9b53310278e76827bbe12a36cc6470d77071abb2"
PROTOCOL = "https"
BRANCH = "master"
S = "${WORKDIR}/git"

SRC_URI = " \
	git://github.com/voxpupuli/puppet-filemapper;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH} \
	" 
do_configure() {
	:
}

do_compile() {
	:
}

do_install() {
	cd ${S}
	install -d -m 0755 ${D}/${datadir}/puppet/modules/filemapper
	cp -R ${S}/* ${D}/${datadir}/puppet/modules/filemapper
}

FILES_${PN} = " ${datadir}"
