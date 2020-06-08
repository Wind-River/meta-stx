
PACKAGES += " mtce-compute"

RDEPENDS_mtce-compute += " \
	bash \
	systemd \
	qemu \
	"

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

FILES_mtce-compute = " \
	${datadir}/licenses/mtce-compute-1.0/LICENSE \
	${systemd_system_unitdir}/goenabled-worker.service \
	${sysconfdir}/goenabled.d/virt-support-goenabled.sh \
	${sysconfdir}/init.d/goenabledWorker \
	"

SYSTEMD_PACKAGES += "mtce-compute"
SYSTEMD_SERVICE_mtce-compute = "goenabled-worker.service"
SYSTEMD_AUTO_ENABLE_mtce-compute = "enable"
