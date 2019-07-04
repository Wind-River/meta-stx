DESCRIPTION = "nfv-plugins"



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
	cd ${S}/nfv/nfv-plugins
	distutils_do_configure
} 

do_compile() {
	cd ${S}/nfv/nfv-plugins
	distutils_do_compile
}

do_install () {
	cd ${S}/nfv/nfv-plugins
	distutils_do_install

	install -d -m 755 ${D}/${sysconfdir}/nfv/
	install -d -m 755 ${D}/${sysconfdir}/nfv/nfv_plugins/
	install -d -m 755 ${D}/${sysconfdir}/nfv/nfv_plugins/alarm_handlers/

	install -p -D -m 600 nfv_plugins/alarm_handlers/config.ini \
			${D}/${sysconfdir}/nfv/nfv_plugins/alarm_handlers/config.ini

	install -d -m 755 ${D}/${sysconfdir}/nfv/nfv_plugins/event_log_handlers/

	install -p -D -m 600 nfv_plugins/event_log_handlers/config.ini \
			${D}/${sysconfdir}/nfv/nfv_plugins/event_log_handlers/config.ini
			\
	install -d -m 755 ${D}/${sysconfdir}/nfv/nfv_plugins/nfvi_plugins/

	install -p -D -m 600 nfv_plugins/nfvi_plugins/config.ini \
			${D}/${sysconfdir}/nfv/nfv_plugins/nfvi_plugins/config.ini
					
	install -d -m 755 ${D}/
	install -p -D -m 644 scripts/nfvi-plugins.logrotate \
			${D}/${sysconfdir}/logrotate.d/nfvi-plugins.logrotate
	
}

#pkg_postinst_ontarget_${PN} () {

# FILES_${PN}_append += " ${systemd_unitdir}/* "
