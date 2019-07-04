DESCRIPTION = "patch-alarm"

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "70609a3d55e5b7d2be82667fc35792505f9013c4"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://opendev.org/starlingx/config.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

require stx-update.inc
inherit setuptools


DEPENDS = " \
	python \
	python-pbr-native \
	"
RDEPENDS_${PN}_append = " bash"

do_configure () {
	cd ${S}/patch-alarm/patch-alarm
	distutils_do_configure
} 

do_compile() {
	cd ${S}/patch-alarm/patch-alarm
	distutils_do_compile
}

do_install () {
	cd ${S}/patch-alarm/patch-alarm
	distutils_do_install

	cd ${S}/patch-alarm/

	install -m 755 -d ${D}/${bindir}
	install -m 755 -d ${D}/${sysconfdir}/init.d

	install -m 700 scripts/bin/patch-alarm-manager ${D}/${bindir}/
	install -m 700 scripts/bin/patch-alarm-manager ${D}/${sysconfdir}/init.d/
	

}

#pkg_postinst_ontarget_${PN} () { }

#FILES_${PN}_append += " "
