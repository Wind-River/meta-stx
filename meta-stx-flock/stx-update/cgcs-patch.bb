
PACKAGES += " ${PN}-agent"
PACKAGES += " ${PN}-controller"

require update-common.inc

S = "${S_DIR}/cgcs-patch/cgcs-patch"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI += "file://0001-Remove-use-of-rpmUtils.miscutils-from-cgcs-patch.patch;striplevel=3 \
       file://0003-Cleaning-up-pylint-settings-for-cgcs-patch.patch;striplevel=3 \
       file://0004-Address-python3-pylint-errors-and-warnings.patch;striplevel=3 \
       file://0005-Clean-up-pylint-W1201-logging-not-lazy-in-cgcs-patch.patch;striplevel=3 \
       file://0006-Migrate-patch-agent-to-use-DNF-for-swmgmt.patch;striplevel=3 \
        "

RDEPENDS_${PN}_append = " \
	bash \
	"
RDEPENDS_${PN}-agent_append = " \
	bash \
	python \
	"

RDEPENDS_${PN}-controller_append = " \
	bash \
	python-requests-toolbelt \
	createrepo-c \
	"

inherit setuptools systemd
DISTRO_FEATURES_BACKFILL_CONSIDERED_remove = "sysvinit"
SYSTEMD_PACKAGES += " ${PN}-controller"
SYSTEMD_SERVICE_${PN}-controller = "sw-patch-controller.service sw-patch-controller-daemon.service "
SYSTEMD_PACKAGES += " ${PN}-agent"
SYSTEMD_SERVICE_${PN}-agent = " sw-patch-agent.service"
SYSTEMD_PACKAGES += " ${PN}"
SYSTEMD_SERVICE_${PN} = "sw-patch.service"

do_install_append () {

	cd ${S_DIR}/cgcs-patch/bin

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

	sed -i -e 's/createrepo/createrepo_c/' ${D}/${sysconfdir}/init.d/sw-patch-controller

}

FILES_${PN} = " \
	${libdir}/python2.7/site-packages/cgcs_patch \
	${libdir}/python2.7/site-packages/cgcs_patch-1.0-py2.7.egg-info \
	${libdir}/python2.7/site-packages/cgcs_make_patch \
	${libdir}/python2.7/site-packages/cgcs_patch-1.0-py2.7.egg-info/top_level.txt \
	${sbindir}/rpm-audit \
	${sysconfdir}/patching/policy.json \
	${sysconfdir}/patching/patching.conf \
	${sysconfdir}/patching/patch-scripts \
	${sysconfdir}/init.d/sw-patch \
	${systemd_system_unitdir}/sw-patch.service \
	${sysconfdir}/goenabled.d/patch_check_goenabled.sh \
	${sysconfdir}/logrotate.d/patching \
	${sysconfdir}/tempfiles.d/patch-tmpdirs.conf \
	${sysconfdir}/patching/patch-functions \
"

FILES_${PN}-agent = " \
	${sbindir}/sw-patch-agent \
	${sbindir}/sw-patch-agent-restart \
	${sysconfdir}/pmon.d/sw-patch-agent.conf \
	${sbindir}/run-patch-scripts \
	${sysconfdir}/init.d/sw-patch-agent \
	${systemd_system_unitdir}/sw-patch-agent.service \
	${sysconfdir}/bash_completion.d/sw-patch \
	"

FILES_${PN}-controller = " \
	${sbindir}/sw-patch-controller-daemon-restart \
	${sysconfdir}/init.d/sw-patch-controller-daemon \
	${sbindir}/sw-patch-controller-daemon \
	${sbindir}/upgrade-start-pkg-extract \
	${sysconfdir}/pmon.d/sw-patch-controller-daemon.conf \
	${systemd_system_unitdir}/sw-patch-controller-daemon.service \
	${sbindir}/sw-patch \
	${sysconfdir}/init.d/sw-patch-controller \
	${systemd_system_unitdir}/sw-patch-controller.service \
	"
