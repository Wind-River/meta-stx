PACKAGES += " controllerconfig"
RDEPENDS_controllerconfig += " bash"

do_configure_prepend() {
	cd ${S}/controllerconfig/controllerconfig
	distutils_do_configure
} 

do_compile_prepend() {
	cd ${S}/controllerconfig/controllerconfig
	distutils_do_compile
}

do_install_prepend () {
	cd ${S}/controllerconfig/controllerconfig
	distutils_do_install

        install -p -D -m 700 scripts/keyringstaging ${D}/${bindir}
        install -p -D -m 700 scripts/openstack_update_admin_password ${D}/${bindir}
        install -p -D -m 700 scripts/install_clone.py ${D}/${bindir}
        install -p -D -m 700 scripts/finish_install_clone.sh ${D}/${bindir}

        install -p -D -m 700 scripts/config_goenabled_check.sh ${D}/${sysconfdir}/goenabled.d
        install -p -D -m 755 scripts/controller_config ${D}/${sysconfdir}/init.d/controller_config

        ## Install Upgrade scripts
	install -d -m 755 ${D}/${sysconfdir}/upgrade.d
        install -p -m 755 upgrade-scripts/* ${D}/${sysconfdir}/upgrade.d

        install -p -D -m 664 scripts/controllerconfig.service ${D}/${systemd_system_unitdir}/controllerconfig.service
}


FILES_controllerconfig = " \
		${sysconfdir}/goenabled.d \
		${sysconfdir}/goenabled.d/config_goenabled_check.sh  \
		${sysconfdir}/upgrade.d/* \
		${sysconfdir}/init.d/controller_config \
		${bindir}/keysringstaging \
        	${bindir}/openstack_update_admin_password \
        	${bindir}/install_clone.py \
        	${bindir}/finish_install_clone.sh \
        	${bindir}/finish_install_clone.sh \
		${bindir}/upgrade_controller \
		${bindir}/config_region \
		${bindir}/config_subcloud \
		${bindir}/config_management \
		${bindir}/keyringstaging \
		${bindir}/tidy_storage_post_restore \
		${bindir}/config_controller \
		${bindir}/upgrade_controller_simplex \
		${systemd_system_unitdir}/controllerconfig.service \
		${libdir}/python2.7/site-packages/controllerconfig*.egg-info/ \
		${libdir}/python2.7/site-packages/controllerconfig/ \
		"
