PACKAGES += "storageconfig"
RDEPENDS_storageconfig += " bash"

do_install_prepend () {
	cd ${S}/storageconfig/storageconfig/
        oe_runmake GOENABLEDDIR=${D}/${sysconfdir}/goenabled.d  INITDDIR=${D}/${sysconfdir}/init.d \
	                        SYSTEMDDIR=${D}/${systemd_system_unitdir} install
	sed -i -e 's:/usr/local/bin/:/usr/bin/:g' ${D}/${sysconfdir}/init.d/storage_config
}

FILES_storageconfig = " \
		${sysconfdir}/init.d/storage_config \
		${sysconfdir}/goenabled.d/config_goenabled_check.sh \
		${systemd_system_unitdir}/storageconfig.service \
		"
