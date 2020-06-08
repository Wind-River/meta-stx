
require monitoring-common.inc

S = "${S_DIR}/influxdb-extensions/src"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

RDEPENDS_${PN} += " \
	systemd \
	python-influxdb \
	"


local_unit_dir = "${sysconfdir}/systemd/system"

do_install_append() {

	install -m 755 -d ${D}/${sysconfdir}
	install -m 755 -d ${D}/${local_unit_dir}
	install -m 755 -d ${D}/${sysconfdir}/influxdb

	# support files ; service and pmon conf
	install -m 644 influxdb.service  ${D}/${local_unit_dir}
	install -m 600 influxdb.conf.pmon  ${D}/${sysconfdir}/influxdb
}

#SYSTEMD_PACKAGES += "${PN}"
#SYSTEMD_SERVICE_${PN} = "influxdb.service"
