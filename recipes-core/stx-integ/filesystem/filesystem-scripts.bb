DESCRIPTION = "filesystem-scripts"

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

	cd ${S}/filesystem/filesystem-scripts/filesystem-scripts-1.0
	install -d -m755 ${D}/${sysconfdir}/init.d
	install -d -m755 ${D}/${libdir}/ocf/resource.d/platform
	install -d -m755 ${D}/${bindir}
	install -d -m755 ${D}/${systemd_system_unitdir}

	install -D -m644 uexportfs  ${D}/${sysconfdir}/init.d
	install -D -m644 nfsserver-mgmt ${D}/${libdir}/ocf/resource.d/platform
	install -D -m644 nfs-mount ${D}/${bindir}
	install -D -m644 uexportfs.service ${D}/${systemd_system_unitdir}

}

FILES_${PN}_append += " \
		${systemd_system_unitdir} \
		${libdir} \
		"
