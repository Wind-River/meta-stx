DESCRIPTION = "nfv-api-proxy"



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
	cd ${S}/nova-api-proxy/nova-api-proxy
	distutils_do_configure
} 

do_compile() {
	cd ${S}/nova-api-proxy/nova-api-proxy
	distutils_do_compile
}

do_install () {
	cd ${S}/nova-api-proxy/nova-api-proxy
	distutils_do_install

	install -d -m 755 ${D}/${systemd_system_unitdir}
	install -p -D -m 644 nova_api_proxy/scripts/api-proxy.service ${D}/${systemd_system_unitdir}/api-proxy.service
	install -d -m 755 ${D}/${sysconfdir}/rc.d/init.d
	install -p -D -m 755 nova_api_proxy/scripts/api-proxy ${D}/${sysconfdir}/rc.d/init.d/api-proxy

	install -d -m 755 ${D}/${sysconfdir}/proxy
	install -p -D -m 700 nova_api_proxy/nova-api-proxy.conf ${D}${sysconfdir}/proxy/nova-api-proxy.conf
	install -p -D -m 700 nova_api_proxy/api-proxy-paste.ini ${D}${sysconfdir}/proxy/api-proxy-paste.ini
	

}

#pkg_postinst_ontarget_${PN} () { }

FILES_${PN}_append += " ${systemd_system_unitdir}/* "
