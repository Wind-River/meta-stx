PACKAGES += "storageconfig"
RDEPENDS_storageconfig += " bash"

do_install_prepend () {
	cd ${S}/storageconfig/storageconfig/
        oe_runmake GOENABLEDDIR=${D}/${sysconfdir}/goenabled.d  INITDDIR=${D}/${sysconfdir}/init.d \
	                        SYSTEMDDIR=${D}/${systemd_system_unitdir} install
}

FILES_storageconfig = " \
		${sysconfdir}/init.d/storage_config \
		${sysconfdir}/goenabled.d/config_goenabled_check.sh \
		${systemd_system_unitdir}/storageconfig.service \
		"
