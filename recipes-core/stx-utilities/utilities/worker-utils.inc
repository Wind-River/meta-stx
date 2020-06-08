
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
		INITDDIR=${D}/${sysconfdir}/init.d \
		GOENABLEDDIR=${D}/${sysconfdir}/goenabled.d \
		PLATFORMCONFDIR=${D}/${sysconfdir}/platform \
		SYSTEMDDIR=${D}/${systemd_system_unitdir}

	sed -i -e 's|drbdadm|${base_sbindir}/drbdadm|' ${D}/${sysconfdir}/init.d/affine-tasks.sh
}

FILES_worker-utils = "  \
	${sysconfdir}/init.d/affine-platform.sh \
	${sysconfdir}/init.d/affine-tasks.sh \
	${sysconfdir}/init.d/cpumap_functions.sh \
	${sysconfdir}/init.d/task_affinity_functions.sh \
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

SYSTEMD_PACKAGES += "worker-utils"
SYSTEMD_SERVICE_worker-utils = "\
	affine-tasks.service \
	affine-platform.sh.service \
	"
SYSTEMD_AUTO_ENABLE_worker-utils = "enable"
