DESCRIPTION = "controllerconfig"

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "70609a3d55e5b7d2be82667fc35792505f9013c4"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://opendev.org/starlingx/config.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

inherit setuptools externalsrc
EXTERNALSRC_pn-${PN}="${EXTERNALREPO}/stx-config"

RDEPENDS_${PN} += " bash"

do_configure () {
	cd ${S}/controllerconfig/controllerconfig
	distutils_do_configure
} 

do_compile() {
	cd ${S}/controllerconfig/controllerconfig
	distutils_do_compile
}

do_install () {
	cd ${S}/controllerconfig/controllerconfig
	distutils_do_install

	# TBD
        # install -d -m 755 ${D}/wheels
        # install -m 644 dist/*.whl $RPM_BUILD_ROOT/wheels/


        install -p -D -m 700 scripts/keyringstaging ${D}/${bindir}
        install -p -D -m 700 scripts/openstack_update_admin_password ${D}/${bindir}
        install -p -D -m 700 scripts/install_clone.py ${D}/${bindir}
        install -p -D -m 700 scripts/finish_install_clone.sh ${D}/${bindir}

        install -p -D -m 700 scripts/config_goenabled_check.sh ${D}/${sysconfdir}/goenabled.d
        install -p -D -m 755 scripts/controller_config ${D}/${sysconfdir}/init.d

        ## Install Upgrade scripts
	install -d -m 755 ${D}/${sysconfdir}/upgrade.d
        install -p -D -m 755 upgrade-scripts/* ${D}/${sysconfdir}/upgrade.d

        install -p -D -m 664 scripts/controllerconfig.service ${D}/${systemd_system_unitdir}
}

FILES_${PN}_append += " ${baselib} \
		${sysconfdir} \
		${bindir} \
		${sbindir} \
		"


