DESCRIPTION = "io-scheduler"

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "70609a3d55e5b7d2be82667fc35792505f9013c4"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://opendev.org/starlingx/config.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

require stx-integ-config-files.inc

RDEPENDS_${PN} += " bash"

do_configure () {
	:
} 

do_compile() {
	:
}

do_install () {
	install -d -m 0755 ${D}/${sysconfdir}/udev/rules.d
	install -m644 ${S}/config-files/io-scheduler/centos/files/60-io-scheduler.rules \
		${D}/${sysconfdir}/udev/rules.d/60-io-scheduler.rules 
}

#FILES_${PN}_append += " ${systemd_system_unitdir} "
