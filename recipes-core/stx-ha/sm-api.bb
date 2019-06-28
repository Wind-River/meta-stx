DESCRIPTION = "sm-api"

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "70609a3d55e5b7d2be82667fc35792505f9013c4"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://opendev.org/starlingx/config.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

require stx-ha.inc
inherit setuptools

DEPENDS = " \
	python \
	python-pbr-native \
	"

do_configure () {
	cd ${S}/service-mgmt-api/sm-api
	distutils_do_configure
} 

do_compile() {
	cd ${S}/service-mgmt-api/sm-api
	distutils_do_compile
}

do_install () {
	cd ${S}/service-mgmt-api/sm-api
	distutils_do_install
	install -d -m 0755 ${D}/${sysconfdir}/sm
	install -d -m 0755 ${D}/${sysconfdir}/init.d
	install -d -m 0755 ${D}/${sysconfdir}/pmon.d
	install -d -m 0755 ${D}/${sysconfdir}/sm-api
	install -d -m 0755 ${D}/${systemd_system_unitdir}
	install -m 644 scripts/sm_api.ini ${D}/${sysconfdir}/sm
	install -m 755 scripts/sm-api ${D}/${sysconfdir}/init.d
	install -m 644 scripts/sm-api.service ${D}/${systemd_system_unitdir/}
	install -m 644 scripts/sm-api.conf ${D}/${sysconfdir}/pmon.d
	install -m 644 etc/sm-api/policy.json ${D}/${sysconfdir}/sm-api
}

FILES_${PN}_append = " ${systemd_unitdir}/* "
