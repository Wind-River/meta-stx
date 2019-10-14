PACKAGES += " mtce-control"

RDEPENDS_mtce-control += " bash"

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

}

pkg_postinst_ontarget_mtce-control_prepend () {
	/bin/systemctl enable lighttpd.service
	/bin/systemctl enable qemu_clean.service
	/bin/systemctl enable hbsAgent.service
}

FILES_mtce-control = " \
	${datadir}/licenses/mtce-control-1.0/LICENSE \
	${systemd_system_unitdir}/hbsAgent.service \
	${sysconfdir}/pmon.d/hbsAgent.conf \
	${sysconfdir}/init.d/hbsAgent \
	${sysconfdir}/init.d/goenabledControl \
	"
