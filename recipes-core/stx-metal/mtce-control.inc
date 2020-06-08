
PACKAGES += " mtce-control"

RDEPENDS_mtce-control += " \
	bash \
	systemd \
	lighttpd \
	qemu \
	"

do_configure_prepend () {
	:
} 

do_compile_prepend () {
	:
}

do_install_prepend () {
	cd ${S}/mtce-control/src/
	oe_runmake buildroot=${D} \
		_sysconfdir=${sysconfdir} _unitdir=${systemd_system_unitdir} _datarootdir=${datadir} \
		install
	sed -i -e 's|/usr/local/bin/|${bindir}/|' ${D}/${sysconfdir}/init.d/hbsAgent
}

FILES_mtce-control = " \
	${datadir}/licenses/mtce-control-1.0/LICENSE \
	${systemd_system_unitdir}/hbsAgent.service \
	${sysconfdir}/pmon.d/hbsAgent.conf \
	${sysconfdir}/init.d/hbsAgent \
	${sysconfdir}/init.d/goenabledControl \
	"

SYSTEMD_PACKAGES += "mtce-control"
SYSTEMD_SERVICE_mtce-control = "hbsAgent.service"
SYSTEMD_AUTO_ENABLE_mtce-control = "enable"
