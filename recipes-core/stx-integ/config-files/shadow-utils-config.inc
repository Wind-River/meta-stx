PACKAGES += " shadow-utils-config"

RDEPENDS_shadow-utils-config += " bash"

do_configure_prepend () {
	:
} 

do_compile_prepend () {
	:
}

do_install_prepend () {
	cd ${S}/config-files/shadow-utils-config 
	install -d -m0755 ${D}/${datadir}/starlingx
	install -d -m0755 ${D}/${sysconfdir}/init.d
	install -d -m0755 ${D}/${systemd_system_unitdir}

	install -m644 files/login.defs ${D}/${datadir}/starlingx/login.defs
	install -m644 files/clear_shadow_locks.service ${D}/${systemd_system_unitdir}/
	install -m644 files/login.defs ${D}/${sysconfdir}/login.defs
}

FILES_shadow-utils-config = " \
	${datadir}/starlingx/login.defs \
	${systemd_system_unitdir}/clear_shadow_locks.service \
	${sysconfdir}/login.defs \
	"
