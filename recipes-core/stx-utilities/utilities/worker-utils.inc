PACKAGES += " worker-utils"

RDEPENDS_worker-utils  += " perl systemd python bash"
DESCRIPTION_worker-utils  = "Initial worker node resource reservation and misc. utilities"
SUMMARY_worker-utils  = "dynamic MOTD generation"

inherit setuptools distutils python-dir

do_configure_append() {
	:
}
do_compile_append() {
	cd ${S}/utilities/worker-utils/worker-utils
	oe_runmake all
}

do_install_append() {
	cd ${S}/utilities/worker-utils/worker-utils

	oe_runmake install \
		BINDIR=${D}/${bindir} \
		INITDDIR=${D}/${sysconfdir}/initd.d \
		GOENABLEDDIR=${D}/${sysconfdir}/goenabled.d \
		PLATFORMCONFDIR=${D}/${sysconfdir}/platform \
		SYSTEMDDIR=${D}/${systemd_system_unitdir}
}

FILES_worker-utils = "  \
	${sysconfdir}/initd.d/affine-platform.sh \
	${sysconfdir}/initd.d/affine-tasks.sh \
	${sysconfdir}/initd.d/cpumap_functions.sh \
	${sysconfdir}/initd.d/task_affinity_functions.sh \
	${bindir}/ps-sched.sh \
	${bindir}/topology.py \
	${bindir}/topology.pyc \
	${bindir}/affine-interrupts.sh \
	${bindir}/set-cpu-wakeup-latency.sh \
	${bindir}/topology \
	${sysconfdir}/platform/worker_reserved.conf \
	${sysconfdir}/goenabled.d/worker-goenabled.sh \
	${systemd_system_unitdir}/affine-platform.sh.service \
	${systemd_system_unitdir}/affine-tasks.service \
	"
