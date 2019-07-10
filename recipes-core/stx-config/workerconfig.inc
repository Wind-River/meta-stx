PACKAGES += " workerconfig"
RDEPENDS_workerconfig += " bash"

do_install_append () {
	cd ${S}/workerconfig/workerconfig/
	oe_runmake GOENABLEDDIR=${D}/${sysconfdir}/goenabled.d  INITDDIR=${D}/${sysconfdir}/init.d \
		SYSTEMDDIR=${D}/${systemd_system_unitdir} install
}

FILES_workerconfig += "  \
		${systemd_system_unitdir}/config/workerconfig-standalone.service \
		${systemd_system_unitdir}/config/workerconfig-combined.service \
		${sysconfdir}/goenabled.d/config_goenabled_check.sh \
		${sysconfdir}/init.d/worker_config \
		${sysconfdir}/init.d/worker_services \
		"
