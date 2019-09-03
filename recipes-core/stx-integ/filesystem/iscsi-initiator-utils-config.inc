PACKAGES += " iscsi-initiator-utils-config"

RDEPENDS_iscsi-initiator-utils-config_append += " bash"

do_configure_append () {
	:
} 

do_compile_append () {
	:
}

do_install_append () {

	cd ${S}/filesystem/iscsi-initiator-utils-config/
	install -d -m755 ${D}/${libdir}/tempfiles.d
	install -d -m755 ${D}/${systemd_system_unitdir}
	install -d -m755 ${D}/${datadir}/starlingx

	install -D -m644 files/iscsi-cache.volatiles ${D}/${libdir}/tempfiles.d
	install -D -m644 files/iscsi-shutdown.service ${D}/${systemd_system_unitdir}
	install -m 0644 files/iscsid.conf ${D}/${datadir}/starlingx/stx.iscsid.conf
}

FILES_iscsi-initiator-utils-config_append += " \
	${libdir}/tempfiles.d/iscsi-cache.volatiles \
	${systemd_system_unitdir}/iscsi-shutdown.service \
	${datadir}/starlingx/stx.iscsid.conf \
		"
