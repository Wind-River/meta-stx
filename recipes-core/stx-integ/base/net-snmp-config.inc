PACKAGES += " net-snmp-config"

RDEPENDS_net-snmp-config += " bash"

do_configure_prepend () {
	:
} 

do_compile_prepend () {
	:
}

do_install_prepend () {
	cd ${S}/base/net-snmp-config
	install -d ${D}/${datadir}/starlingx
	install -d ${D}/${datadir}/snmp
	install -d ${D}/${sysconfdir}/rc.d/init.d
	install -d ${D}/${systemd_system_unitdir}

	install -m640 files/stx.snmpd.conf ${D}/${datadir}/starlingx/stx.snmpd.conf
	install -m755 files/stx.snmpd ${D}/${sysconfdir}/rc.d/init.d/stx-snmpd
	install -m640 files/stx.snmp.conf ${D}/${datadir}/snmp/snmp.conf
	install -m644 files/snmpd.service ${D}/${systemd_system_unitdir}/stx-snmpd.service

}

FILES_net-snmp-config = " \
	${datadir}/starlingx/stx.snmpd.conf \
	${sysconfdir}/rc.d/init.d/stx-snmpd \
	${datadir}/snmp/snmp.conf \
	${systemd_system_unitdir}/stx-snmpd.service \
	"
