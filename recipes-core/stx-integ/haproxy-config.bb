DESCRIPTION = "haproxy-config"

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
	cd ${S}/base/haproxy-config
	install -d -m 0755 ${D}/${systemd_system_unitdir}
	install -d -m 0755 ${D}/${sysconfdir}/haproxy/errors
	install -d -m 0755 ${D}/${sysconfdir}/init.d
	install -p -m 0755 files/503.http ${D}/${sysconfdir}/haproxy/errors
	install -p -m 0644 files/haproxy.service ${D}/${systemd_system_unitdir}
	install -p -m 0755 files/haproxy.sh ${D}/${sysconfdir}/init.d
}

FILES_${PN}_append += " ${systemd_system_unitdir} "
