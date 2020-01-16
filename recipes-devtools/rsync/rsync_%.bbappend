inherit systemd
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "rsync.service"
SYSTEMD_AUTO_ENABLE_${PN} = "enable"

do_install_append_class-target() {
        install -p -D -m 644 ${S}/packaging/systemd/rsync.service ${D}/${systemd_system_unitdir}/rsync.service
}

FILES_${PN}_append = " ${systemd_system_unitdir}"

	
