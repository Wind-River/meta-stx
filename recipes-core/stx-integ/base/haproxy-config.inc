PACKAGES += " haproxy-config"

RDEPENDS_haproxy-config += " bash"

do_configure_prepend () {
	:
} 

do_compile_prepend () {
	:
}

do_install_prepend () {
	cd ${S}/base/haproxy-config
	install -d -m 0755 ${D}/${systemd_system_unitdir}
	install -d -m 0755 ${D}/${sysconfdir}/haproxy/errors
	install -d -m 0755 ${D}/${sysconfdir}/init.d
	install -p -m 0755 files/503.http ${D}/${sysconfdir}/haproxy/errors
	install -p -m 0644 files/haproxy.service ${D}/${systemd_system_unitdir}
	install -p -m 0755 files/haproxy.sh ${D}/${sysconfdir}/init.d
}

FILES_haproxy-config = " \
	${sysconfdir}/haproxy/errors/503.http \
	${systemd_system_unitdir}/haproxy.service \
	${sysconfdir}/init.d/haproxy.sh \
	"
