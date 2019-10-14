PACKAGES += " workerconfig-standalone"
PACKAGES += " workerconfig-subfunction"

RDEPENDS_workerconfig-standalone += " bash"

do_install_append () {
	cd ${S}/workerconfig/workerconfig/
	oe_runmake GOENABLEDDIR=${D}/${sysconfdir}/goenabled.d  INITDDIR=${D}/${sysconfdir}/init.d \
		SYSTEMDDIR=${D}/${systemd_system_unitdir} install
}

FILES_workerconfig-standalone += "  \
		${systemd_system_unitdir}/config/workerconfig-standalone.service \
		${sysconfdir}/goenabled.d/config_goenabled_check.sh \
		${sysconfdir}/init.d/worker_config \
		${sysconfdir}/init.d/worker_services \
		"

FILES_workerconfig-subfunction += "  \
		${systemd_system_unitdir}/config/workerconfig-combined.service \
		"
