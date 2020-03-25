PACKAGES += " workerconfig-standalone"
PACKAGES += " workerconfig-subfunction"

RDEPENDS_workerconfig-standalone += " bash"

do_install_append () {
	cd ${S}/workerconfig/workerconfig/
	oe_runmake GOENABLEDDIR=${D}/${sysconfdir}/goenabled.d  INITDDIR=${D}/${sysconfdir}/init.d \
		SYSTEMDDIR=${D}/${sysconfdir}/systemd/system install

	sed -i -e 's|/usr/local/bin|${bindir}|' \
		${D}${sysconfdir}/init.d/worker_config
}

FILES_workerconfig-standalone += "  \
		${sysconfdir}/systemd/system/config/workerconfig-standalone.service \
		${sysconfdir}/goenabled.d/config_goenabled_check.sh \
		${sysconfdir}/init.d/worker_config \
		${sysconfdir}/init.d/worker_services \
		"

FILES_workerconfig-subfunction += "  \
		${sysconfdir}/systemd/system/config/workerconfig-combined.service \
		"
