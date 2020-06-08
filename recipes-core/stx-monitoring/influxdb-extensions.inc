
PACKAGES += " influxdb-extensions"

RDEPENDS_influxdb-extensions += " \
	systemd \
	python-influxdb \
	"

do_configure_append () {
	:
} 

do_compile_append() {
	:
}

local_unit_dir = "${sysconfdir}/systemd/system"

do_install_append() {

	cd ${S}/influxdb-extensions/src
	install -m 755 -d ${D}/${sysconfdir}
	install -m 755 -d ${D}/${local_unit_dir}
	install -m 755 -d ${D}/${sysconfdir}/influxdb

	# support files ; service and pmon conf
	install -m 644 influxdb.service  ${D}/${local_unit_dir}
	install -m 600 influxdb.conf.pmon  ${D}/${sysconfdir}/influxdb
}

FILES_influxdb-extensions = " \
	${local_unit_dir}/influxdb.service \
	${sysconfdir}/influxdb \
	"
