DESCRIPTION = "ceph-manager"

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "70609a3d55e5b7d2be82667fc35792505f9013c4"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://opendev.org/starlingx/config.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

require stx-ineg-ceph.inc
inherit setuptools


DEPENDS = " \
	python \
	python-pbr-native \
	"
RDEPENDS_${PN}_append = " bash"

do_configure () {
	cd ${S}/ceph/ceph-manager/ceph-manager/
	distutils_do_configure
} 

do_compile() {
	cd ${S}/ceph/ceph-manager/ceph-manager/
	distutils_do_compile
}

do_install () {
	cd ${S}/ceph/ceph-manager/ceph-manager/
	distutils_do_install

	cd ${S}/ceph/ceph-manager/

	install -d -m 755 ${D}/${sysconfdir}/init.d
	install -p -D -m 700 scripts/init.d/ceph-manager ${D}/${sysconfdir}/init.d/ceph-manager

	install -d -m 755 ${D}/${bindir}
	install -p -D -m 700 scripts/bin/ceph-manager ${D}//${bindir}/ceph-manager

	install -d -m 755 ${D}${sysconfdir}/logrotate.d
	install -p -D -m 644 files/ceph-manager.logrotate ${D}/${sysconfdir}/logrotate.d/ceph-manager.logrotate

	install -d -m 755 ${D}/${systemd_system_unitdir}
	install -m 644 -p -D files/${PN}.service ${D}/${systemd_system_unitdir}/${PN}.service

}

# pkg_postinst_ontarget_${PN} () { }

FILES_${PN}_append += " \
	${systemd_system_unitdir} \
	"
