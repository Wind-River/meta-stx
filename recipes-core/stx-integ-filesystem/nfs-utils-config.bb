DESCRIPTION = "nfs-utils-config"

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "70609a3d55e5b7d2be82667fc35792505f9013c4"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://opendev.org/starlingx/config.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

require stx-integ-filesystem.inc

RDEPENDS_${PN} += " bash"

do_configure () {
	:
} 

do_compile() {
	:
}

do_install () {

	cd ${S}/filesystem/nfs-utils-config/
	install -d -m755 ${D}/${sysconfdir}/init.d
	install -d -m755 ${D}/${datadir}/starlingx
	install -d -m755 ${D}/${systemd_system_unitdir}

	install -D -m644 files/nfscommon ${D}/${sysconfdir}/init.d/
	install -D -m644 files/nfscommon.service ${D}/${systemd_system_unitdir}/
	install -D -m644 files/nfsserver ${D}/${sysconfdir}/init.d/
	install -D -m644 files/nfsserver.service ${D}/${systemd_system_unitdir}/
	install -D -m644 files/nfsmount.conf ${D}/${datadir}/starlingx/stx.nfsmount.conf
	install -D -m644 files/nfsmount.conf ${D}/${sysconfdir}/

}

pkg_postinst_ontarget_${PN} () {
	systemctl disable rpc-statd.service
	systemctl disable rpc-statd-notify.service
	systemctl disable nfs-lock.service
	systemctl disable nfslock.service

	systemctl enable nfscommon.service  >/dev/null 2>&1 || :
	systemctl enable nfsserver.service  >/dev/null 2>&1 || :
}	

FILES_${PN}_append += " \
		${systemd_system_unitdir} \
		"
