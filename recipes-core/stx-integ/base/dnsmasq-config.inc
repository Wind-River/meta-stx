PACKAGES += " dnsmasq-config"

RDEPENDS_dnsmasq-config += " bash"

do_configure_prepend () {
	:
} 

do_compile_prepend () {
	:
}

do_install_prepend () {
	cd ${S}/base/dnsmasq-config
	install -d -m 0755 ${D}/${sysconfdir}/init.d
	install -p -m 0755 files/init ${D}/${sysconfdir}/init.d/stx-dnsmasq
}

FILES_dnsmasq-config = " \
	${sysconfdir}/init.d/stx-dnsmasq \
	"
