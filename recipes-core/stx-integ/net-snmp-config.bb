DESCRIPTION = "net-snmp-config"

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
	cd ${S}/base/net-snmp-config
	install -d ${D}/${datadir}/starlingx
	install -d ${D}/${datadir}/snmp
	install -d ${D}/${sysconfdir}/rc.d/init.d
	install -d ${D}/${systemd_system_unitdir}

	install -m640 files/stx.snmpd.conf ${D}/${datadir}/starlingx/stx.snmpd.conf
	install -m755 files/stx.snmpd ${D}/${sysconfdir}/rc.d/init.d/snmpd
	install -m640 files/stx.snmp.conf ${D}/${datadir}/snmp/snmp.conf
	install -m644 files/snmpd.service ${D}/${systemd_system_unitdir}/snpd.service

}

FILES_${PN}_append += " \
	${systemd_system_unitdir} \
	${datadir} \
	"
		
