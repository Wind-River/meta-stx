inherit systemd
require fault-common.inc

SRC_URI += "file://0001-fm-mgr-Fix-install-target.patch"
S = "${S_DIR}/fm-mgr/sources"

DEPENDS += "fm-common"

EXTRA_OEMAKE = 'LDFLAGS="${LDFLAGS} -L${S}/fm-common/sources" \
		CCFLAGS="${CXXFLAGS}" \
		INCLUDES="-I. -I${S}/fm-common/sources" \
                BINDIR="${bindir}" \
		LIBDIR="${libdir}" \
                UNITDIR="${systemd_system_unitdir}" \
                DESTDIR="${D}" \
               '
do_install () {
	oe_runmake install
	# fix the path for init scripts
	sed -i -e 's|rc.d/||' ${D}/${systemd_system_unitdir}/*.service

	# fix the path for binaries
	sed -i -e 's|/usr/local/bin/|${bindir}/|' ${D}${sysconfdir}/init.d/fminit
}

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "fminit.service"
SYSTEMD_AUTO_ENABLE_${PN} = "disable"
DISTRO_FEATURES_BACKFILL_CONSIDERED_remove = "sysvinit"

inherit useradd

USERADD_PACKAGES = "fm-mgr"
USERADD_PARAM_fm-mgr = "-r -g fm -u 195 -d /var/lib/fm -s /sbin/nologin -c 'fm-mgr' fm"
GROUPADD_PARAM_fm-mgr = "-r -g 195 fm"

RDEPENDS_${PN}_append = " net-snmp-server-snmpd"
