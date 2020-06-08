
PACKAGES += " config-gate-worker"
PACKAGES += " config-gate"

RDEPENDS_config-gate-worker += " bash"
RDEPENDS_config-gate += " bash"

do_install_append() {
	cd ${S}/config-gate/files
	oe_runmake -e \
                 SBINDIR=${D}/${sbindir} SYSTEMDDIR=${D}/${systemd_system_unitdir} \
		 install
}

FILES_config-gate-worker = " \
	${sbindir}/wait_for_worker_config_init.sh \
	${systemd_system_unitdir}/worker-config-gate.service \
	"
FILES_config-gate = " \
	${sbindir}/wait_for_config_init.sh \
	${systemd_system_unitdir}/config.service \
	"

SYSTEMD_PACKAGES += "\
	config-gate \
	config-gate-worker \
	"
SYSTEMD_SERVICE_config-gate = "config.service"
SYSTEMD_SERVICE_config-gate-worker = "worker-config-gate.service"

SYSTEMD_AUTO_ENABLE_config-gate = "enable"
SYSTEMD_AUTO_ENABLE_config-gate-worker = "enable"
