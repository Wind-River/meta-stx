DESCRIPTION = "nfv-vim"



STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "cdd6c334d9d1d6c0f4de344fff8ef2af28c76e56"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"



SRC_URI = "git://opendev.org/starlingx/nfv.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

require stx-nfv.inc
inherit setuptools


do_configure () {
	cd ${S}/nfv/nfv-vim
	distutils_do_configure
} 

do_compile() {
	cd ${S}/nfv/nfv-vim
	distutils_do_compile
}

do_install () {
	cd ${S}/nfv/nfv-vim
	distutils_do_install
	install -d -m 755 ${D}/usr/lib/ocf/resource.d/nfv
	install -p -D -m 755 scripts/vim ${D}/usr/lib/ocf/resource.d/nfv/vim
	install -p -D -m 755 scripts/vim-api ${D}/usr/lib/ocf/resource.d/nfv/vim-api
	install -p -D -m 755 scripts/vim-webserver ${D}/usr/lib/ocf/resource.d/nfv/vim-webserver
	install -d -m 755 ${D}/${sysconfdir}/nfv/
	install -d -m 755 ${D}/${sysconfdir}/nfv/vim/
	install -p -D -m 600 nfv_vim/config.ini ${D}/${sysconfdir}/nfv/vim/config.ini
	install -p -D -m 600 nfv_vim/debug.ini ${D}/${sysconfdir}/nfv/vim/debug.ini

}

#pkg_postinst_ontarget_${PN} () {

# FILES_${PN}_append += " ${systemd_unitdir}/* "
