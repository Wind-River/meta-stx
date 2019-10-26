PACKAGES += " worker-utils"
RDEPENDS_worker-utils += " bash"

do_compile_prepend () {
	cd ${S}/worker-utils/worker-utils
	oe_runmake all
}

do_install_append () {
	cd ${S}/worker-utils/worker-utils
	oe_runmake BINDIR=${D}/${bindir} GOENABLEDDIR=${D}/${sysconfdir}/goenabled.d \
		INITDDIR=${D}/${sysconfdir}/init.d PLATFORMCONFDIR=${D}/${sysconfdir}/platform \
		SYSTEMDDIR=${D}/${systemd_system_unitdir} install
}

FILES_worker-utils = " \
	${bindir}/ps-sched.sh \
	${bindir}/topology.py \
	${bindir}/set-cpu-wakeup-latency.sh \
	${bindir}/affine-interrupts.sh \
	${bindir}/topology \
	${bindir}/topology.pyc \
	${systemd_system_unitdir}/affine-tasks.service \
	${systemd_system_unitdir}/affine-platform.sh.service \
	${sysconfdir}/goenabled.d/worker-goenabled.sh \
	${sysconfdir}/platform/worker_reserved.conf \
	${sysconfdir}/init.d/affine-platform.sh \
	${sysconfdir}/init.d/task_affinity_functions.sh \
	${sysconfdir}/init.d/cpumap_functions.sh \
	${sysconfdir}/init.d/affine-tasks.sh \
	"
