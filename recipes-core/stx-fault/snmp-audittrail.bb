DESCRIPTION = "snmp-audittrail"

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "70609a3d55e5b7d2be82667fc35792505f9013c4"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://opendev.org/starlingx/config.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

inherit externalsrc
EXTERNALSRC_pn-${PN}="${EXTERNALREPO}/stx-fault"

require stx-fault.inc

DEPENDS = " \
	fm-common \
	net-snmp \
	"

do_configure () {
	:
} 

do_compile() {
	cd ${S}/snmp-audittrail/sources
	oe_runmake -e \
		INCLUDES="-I. " \
		LDFLAGS="${LDFLAGS}" \
		CCFLAGS="${CXXFLAGS} -fPIC"
}

do_install () {
	cd ${S}/snmp-audittrail/sources
	oe_runmake -e DESTDIR=${D} LIB_DIR=${libdir} MIBVER=0 PATCH=0 install
}

FILES_${PN}_append = " ${systemd_system_unitdir}"

