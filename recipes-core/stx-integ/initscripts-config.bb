DESCRIPTION = "initscripts-config"

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "70609a3d55e5b7d2be82667fc35792505f9013c4"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://opendev.org/starlingx/config.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

require stx-integ.inc

RDEPENDS_${PN} += " bash"

do_configure () {
	:
} 

do_compile() {
	:
}

do_install () {
	cd ${S}/base/initscripts-config
	install -d -m 0755 ${D}/${datadir}/starlingx/
	install -d -m 0755 ${D}/${sysconfdir}/sysconfig
	install -d -m 0755 ${D}/${sysconfdir}/init.d
	install -d -m 0755 ${D}/${systemd_system_unitdir}

	install -p -m 0644 files/sysctl.conf ${D}/${datadir}/starlingx/stx.sysctl.conf
	install -p -m 0644 files/sysconfig-network.conf ${D}/${sysconfdir}/sysconfig/network
	install -p -m 0755 files/mountnfs.sh ${D}/${sysconfdir}/init.d/mountnfs
	install -p -m 0644 files/mountnfs.service ${D}/${systemd_system_unitdir}/mountnfs.service
}

FILES_${PN}_append += " \
	${systemd_system_unitdir} \
	${datadir} \
	"\
		
