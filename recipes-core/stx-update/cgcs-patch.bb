DESCRIPTION = "cgcs-patch"

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "70609a3d55e5b7d2be82667fc35792505f9013c4"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://opendev.org/starlingx/config.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

require stx-update.inc
inherit setuptools


DEPENDS = " \
	python \
	python-pbr-native \
	"
RDEPENDS_${PN}_append = " bash"

do_configure () {
	cd ${S}/cgcs-patch/cgcs-patch
	distutils_do_configure
} 

do_compile() {
	cd ${S}/cgcs-patch/cgcs-patch
	distutils_do_compile
}

do_install () {
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

pkg_postinst_ontarget_${PN} () { 
	/usr/bin/systemctl enable sw-patch-controller.service
	/usr/bin/systemctl enable sw-patch-controller-daemon.service
}

FILES_${PN}_append += " \
	${systemd_system_unitdir} \
	"
