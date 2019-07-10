PACKAGES += " config-gate"
RDEPENDS_config-gate += " bash"

do_install_prepend() {
	cd ${S}/config-gate/files
	oe_runmake -e \
                 SBINDIR=${D}/${sbindir} SYSTEMDDIR=${D}/${systemd_system_unitdir}
}

FILES_config-gate = " \
	${sbindir}/wait_for_worker_config_init.sh \
	${sbindir}/wait_for_config_init.sh \
	${systemd_system_unitdir}/worker-config-gate.service \
	${systemd_system_unitdir}/config.service \
	"
