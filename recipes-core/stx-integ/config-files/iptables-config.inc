PACKAGES += " iptables-config"

RDEPENDS_iptables-config += " bash"

do_configure_prepend () {
	:
} 

do_compile_prepend () {
	:
}

do_install_prepend () {
	cd ${S}/config-files/iptables-config 
	install -d -m0755 ${D}/${sysconfdir}/sysconfig
	install -d -m0755 ${D}/${datadir}/starlingx

	install -m600 files/iptables.rules ${D}/${datadir}/starlingx/iptables.rules
	install -m600 files/ip6tables.rules ${D}/${datadir}/starlingx/ip6tables.rules

	install -m600 files/iptables.rules ${D}/${sysconfdir}/sysconfig/iptables.rules
	install -m600 files/ip6tables.rules ${D}/${sysconfdir}/sysconfig/ip6tables.rules
}

FILES_iptables-config = " \
	${datadir}/starlingx/iptables.rules \
	${datadir}/starlingx/ip6tables.rules \
	${sysconfdir}/sysconfig/iptables.rules \
	${sysconfdir}/sysconfig/ip6tables.rules \
	"
