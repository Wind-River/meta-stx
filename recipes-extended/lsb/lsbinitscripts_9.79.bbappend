do_install_append() {
	install -m 0755 -d ${D}/${sysconfdir}/profile.d/
	install -m 0644 ${S}/lang.sh  ${D}${sysconfdir}/profile.d/lang.sh
}

FILES_${PN}_append = "${sysconfdir}/profile.d/lang.sh"
