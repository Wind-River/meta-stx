PACKAGES += " cgcs-patch"

inherit setuptools

RDEPENDS_cgcs-patch_append = " bash"

do_configure_append () {
	cd ${S}/cgcs-patch/cgcs-patch
	distutils_do_configure
} 

do_compile_append () {
	cd ${S}/cgcs-patch/cgcs-patch
	distutils_do_compile
}

do_install_append () {
	cd ${S}/cgcs-patch/cgcs-patch
	distutils_do_install

	cd ${S}/cgcs-patch/bin

	install -m 755 -d ${D}/${sbindir}
	install -m 755 -d ${D}/${sysconfdir}/bash_completion.d
	install -m 755 -d ${D}/${sysconfdir}/goenabled.d
	install -m 755 -d ${D}/${sysconfdir}/init.d
	install -m 755 -d ${D}/${sysconfdir}/logrotate.d
	install -m 755 -d ${D}/${sysconfdir}/patching
	install -m 700 -d ${D}/${sysconfdir}/patching/patch-scripts
	install -m 755 -d ${D}/${sysconfdir}/pmon.d
	install -m 755 -d ${D}/${systemd_system_unitdir}

	install -m 500 sw-patch-agent  ${D}/${sbindir}/sw-patch-agent
	install -m 500 sw-patch-controller-daemon ${D}/${sbindir}/sw-patch-controller-daemon
	install -m 555 sw-patch ${D}/${sbindir}/sw-patch
	install -m 555 rpm-audit ${D}/${sbindir}/rpm-audit
	
	install -m 500 sw-patch-controller-daemon-init.sh ${D}/${sysconfdir}/init.d/sw-patch-controller-daemon
	install -m 500 sw-patch-agent-init.sh ${D}/${sysconfdir}/init.d/sw-patch-agent
	
	install -m 600 patching.conf ${D}/${sysconfdir}/patching/patching.conf
	
	install -m 644 policy.json ${D}/${sysconfdir}/patching/policy.json 
	
	install -m 444 pmon-sw-patch-controller-daemon.conf ${D}/${sysconfdir}/pmon.d/sw-patch-controller-daemon.conf
	install -m 444 pmon-sw-patch-agent.conf ${D}/${sysconfdir}/pmon.d/sw-patch-agent.conf 
	install -m 444 *.service ${D}/${systemd_system_unitdir} 
	install -m 444 sw-patch.completion ${D}/${sysconfdir}/bash_completion.d/sw-patch 
	install -m 400 patch-functions ${D}/${sysconfdir}/patching/patch-functions 

	install -D -m 444 patch-tmpdirs.conf ${D}/${sysconfdir}/tempfiles.d/patch-tmpdirs.conf

	install -m 500 run-patch-scripts ${D}/${sbindir}/run-patch-scripts 
	install -m 500 sw-patch-controller-daemon-restart ${D}/${sbindir}/sw-patch-controller-daemon-restart
	install -m 500 sw-patch-agent-restart ${D}/${sbindir}/sw-patch-agent-restart


	install -m 500 run-patch-scripts ${D}/${sbindir}/run-patch-scripts
	install -m 500 sw-patch-controller-daemon-restart ${D}/${sbindir}/sw-patch-controller-daemon-restart
	install -m 500 sw-patch-agent-restart ${D}/${sbindir}/sw-patch-agent-restart 
	install -m 500 sw-patch-init.sh ${D}/${sysconfdir}/init.d/sw-patch
	install -m 500 sw-patch-controller-init.sh ${D}/${sysconfdir}/init.d/sw-patch-controller 
	install -m 555 patch_check_goenabled.sh ${D}/${sysconfdir}/goenabled.d/patch_check_goenabled.sh 
	install -m 444 patching.logrotate ${D}/${sysconfdir}/logrotate.d/patching 
	
	install -m 500 upgrade-start-pkg-extract ${D}/${sbindir}/upgrade-start-pkg-extract

}

pkg_postinst_ontarget_cgcs-patch () { 
	/usr/bin/systemctl enable sw-patch-controller.service
	/usr/bin/systemctl enable sw-patch-controller-daemon.service
}

FILES_cgcs-patch = " \
	${sbindir}/upgrade-start-pkg-extract \
	${sbindir}/rpm-audit \
	${sbindir}/sw-patch \
	${sbindir}/sw-patch-agent-restart \
	${sbindir}/sw-patch-agent \
	${sbindir}/run-patch-scripts \
	${sbindir}/sw-patch-controller-daemon-restart \
	${sbindir}/sw-patch-controller-daemon \
	${libdir}/python2.7/site-packages/cgcs_make_patch \
	${libdir}/python2.7/site-packages/cgcs_patch-1.0-py2.7.egg-info \
	${libdir}/python2.7/site-packages/cgcs_patch \
	${libdir}/python2.7/site-packages/cgcs_patch-1.0-py2.7.egg-info/top_level.txt \
	${systemd_system_unitdir}/sw-patch.service \
	${systemd_system_unitdir}/sw-patch-agent.service \
	${systemd_system_unitdir}/sw-patch-controller.service \
	${systemd_system_unitdir}/sw-patch-controller-daemon.service \
	${sysconfdir}/goenabled.d/patch_check_goenabled.sh \
	${sysconfdir}/pmon.d/sw-patch-controller-daemon.conf \
	${sysconfdir}/pmon.d/sw-patch-agent.conf \
	${sysconfdir}/tempfiles.d/patch-tmpdirs.conf \
	${sysconfdir}/patching/patch-functions \
	${sysconfdir}/patching/patching.conf \
	${sysconfdir}/patching/policy.json \
	${sysconfdir}/patching/patch-scripts \
	${sysconfdir}/init.d/sw-patch-controller \
	${sysconfdir}/init.d/sw-patch \
	${sysconfdir}/init.d/sw-patch-agent \
	${sysconfdir}/init.d/sw-patch-controller-daemon \
	${sysconfdir}/bash_completion.d/sw-patch \
	${sysconfdir}/logrotate.d/patching \
	"
