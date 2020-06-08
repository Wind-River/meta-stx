
require metal-common.inc

S = "${S_DIR}/mtce-compute/src/"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

RDEPENDS_${PN}_append += " \
	bash \
	systemd \
	qemu \
	"

inherit systemd
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "goenabled-worker.service"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
	cd ${S}/
	oe_runmake -e buildroot=${D} \
		_sysconfdir=${sysconfdir} _unitdir=${systemd_system_unitdir} _datarootdir=${datadir} \
		install
}

FILES_${PN}_append = " ${datadir}/licenses/mtce-compute-1.0/LICENSE"
