SYSTEMD_DISABLED_SYSV_SERVICES_remove += " networking"

pkg_postinst_ontarget_${PN}() {
	
	systemctl enable networking.service
}
