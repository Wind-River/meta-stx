DESCRIPTION = "fm-rest-api"

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

inherit setuptools

require stx-fault.inc

do_configure () {
	cd ${S}/fm-rest-api/fm
	distutils_do_configure
} 

do_compile() {
	cd ${S}/fm-rest-api/fm
	distutils_do_compile
}

do_install () {
	cd ${S}/fm-rest-api/fm
	distutils_do_compile
	install -p -D -m 644 scripts/fm-api.service ${D}/${systemd_system_unitdir}
	install -p -D -m 755 scripts/fm-api ${D}/${sysconfdir}/init.d/fm-api
	install -p -D -m 644 fm-api-pmond.conf ${D}/${sysconfdir}/pmon.d/fm-api.conf

}

FILES_${PN}_append = " ${systemd_system_unitdir}"

