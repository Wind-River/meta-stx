PACKAGES += " dhcp-config"

# TODO: Needs to be a bbappend

RDEPENDS_dhcp-config += " bash"

do_configure_prepend () {
	:
} 

do_compile_prepend () {
	:
}

do_install_prepend () {
	cd ${S}/base/dhcp-config
	install -d -m 0755 ${D}/${sysconfdir}/dhcp
	install -p -m 0755 files/dhclient-enter-hooks ${D}/${sysconfdir}/dhcp/stx-dhclient-enter-hooks
	install -p -m 0755 files/dhclient.conf ${D}/${sysconfdir}/dhcp/stx-dhclient.conf
}

FILES_dhcp-config = " \
	${sysconfdir}/dhcp/stx-dhclient-enter-hooks \
	${sysconfdir}/dhcp/stx-dhclient.conf \
	"
