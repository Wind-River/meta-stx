do_install_append() {
	rm -f ${D}/etc/network/interfaces
	touch ${D}/etc/network/interfaces
}
