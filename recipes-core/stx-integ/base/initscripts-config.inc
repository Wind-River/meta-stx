PACKAGES += " initscripts-config"

RDEPENDS_initscripts-config += " bash"

do_configure_prepend () {
	:
} 

do_compile_prepend () {
	:
}

do_install_prepend () {
	cd ${S}/base/initscripts-config
	install -d -m 0755 ${D}/${datadir}/starlingx/
	install -d -m 0755 ${D}/${sysconfdir}/sysconfig
	install -d -m 0755 ${D}/${sysconfdir}/init.d
	install -d -m 0755 ${D}/${systemd_system_unitdir}
	install -d -m 0755 ${D}/${sysconfdir}/sysconfig/network

	install -p -m 0644 files/sysctl.conf ${D}/${datadir}/starlingx/stx.sysctl.conf
	install -p -m 0644 files/sysconfig-network.conf ${D}/${sysconfdir}/sysconfig/network/
	install -p -m 0755 files/mountnfs.sh ${D}/${sysconfdir}/init.d/mountnfs
	install -p -m 0644 files/mountnfs.service ${D}/${systemd_system_unitdir}/mountnfs.service
}

FILES_initscripts-config = " \
	${datadir}/starlingx/stx.sysctl.conf \
	${sysconfdir}/sysconfig/network/sysconfig-network.conf \
	${sysconfdir}/init.d/mountnfs \
	${systemd_system_unitdir}/mountnfs.service \
	"
		
