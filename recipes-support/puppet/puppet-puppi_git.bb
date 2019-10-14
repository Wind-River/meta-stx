DESCRIPTION = "A Puppet module to manage non volatile network and route configuration."

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a300b604c66de62cf6e923cca89c9d83"

SRCREV = "c1c47f4edfd761d1bbde32a75da0c3fa7cc93a81"
PROTOCOL = "https"
BRANCH = "master"
S = "${WORKDIR}/git"

SRC_URI = " \
	git://github.com/example42/puppi;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH} \
	" 
do_configure() {
	:
}

do_compile() {
	:
}

do_install() {
	cd ${S}
	install -d -m 0755 ${D}/${datadir}/puppet/modules/puppi
	cp -R ${S}/* ${D}/${datadir}/puppet/modules/puppi
}

FILES_${PN} = " ${datadir}"
