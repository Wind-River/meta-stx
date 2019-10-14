PACKAGES += " memcached-custom"

RDEPENDS_memcached-custom = " bash"

do_configure_prepend () {
	:
} 

do_compile_prepend () {
	:
}

do_install_prepend () {
	cd ${S}/config-files/memcached-custom
	install -d -m0755 ${D}/${systemd_system_unitdir}

	install -m644 files/memcached.service ${D}/${systemd_system_unitdir}/
}

FILES_memcached-custom = " \
	${systemd_system_unitdir}/memcached.service \
	"
