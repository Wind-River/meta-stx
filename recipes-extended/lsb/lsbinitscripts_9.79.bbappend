DEPENDS += "\
    gettext-native \
    glib-2.0 \
    popt \
"

unset do_configure[noexec]
unset do_compile[noexec]

do_install_append() {
	install -m 0755 -d ${D}/${sysconfdir}/profile.d/
	install -m 0644 ${S}/lang.sh  ${D}${sysconfdir}/profile.d/lang.sh
	install -m 0755 -d ${D}/${base_sbindir}
	install -m 0755 ${S}/src/consoletype ${D}/${base_sbindir}
}

FILES_${PN}_append = "${sysconfdir}/profile.d/lang.sh"
