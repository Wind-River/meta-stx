PACKAGES += " mtce-compute"

RDEPENDS_mtce-compute_append = " bash"

do_configure_prepend () {
	:
} 

do_compile_prepend () {
	:
}

do_install_prepend () {
	cd ${S}/mtce-compute/src/
	oe_runmake buildroot=${D} \
		_sysconfdir=${sysconfdir} _unitdir=${systemd_system_unitdir} _datarootdir=${datadir} \
		install
}

pkg_postinst_ontarget_mtce-compute_prepend () { 
	/bin/systemctl enable goenabled-worker.service
	/bin/systemctl enable qemu_clean.service

}

FILES_mtce-compute = " \
	${datadir}/licenses/mtce-compute-1.0/LICENSE \
	${systemd_system_unitdir}/goenabled-worker.service \
	${sysconfdir}/goenabled.d/virt-support-goenabled.sh \
	${sysconfdir}/init.d/goenabledWorker \
	"
