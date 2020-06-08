inherit setuptools

require fault-common.inc

S = "${S_DIR}/fm-rest-api/fm"

do_install_append() {
	install -d -m 755 ${D}/${systemd_system_unitdir}
	install -p -D -m 644 scripts/fm-api.service ${D}/${systemd_system_unitdir}
	install -p -D -m 755 scripts/fm-api ${D}/${sysconfdir}/init.d/fm-api
	install -p -D -m 644 fm-api-pmond.conf ${D}/${sysconfdir}/pmon.d/fm-api.conf
	
	# fix the path for init scripts
	sed -i -e 's|rc.d/||' ${D}/${systemd_system_unitdir}/*.service
}

inherit systemd
SYSTEMD_PACKAGES += "fm-rest-api"
SYSTEMD_SERVICE_${PN} = "fm-api.service"
SYSTEMD_AUTO_ENABLE_${PN} = "disable"
