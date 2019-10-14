PACKAGES += " audit-config"

RDEPENDS_audit-config += " bash"

do_configure_prepend () {
	:
} 

do_compile_prepend () {
	:
}

do_install_prepend () {
	cd ${S}/config-files/audit-config
	install -d -m 0755 ${D}/${datadir}/starlingx/
	install -p -m 0644 files/syslog.conf ${D}/${datadir}/starlingx/syslog.conf
}

FILES_audit-config = " \
	${datadir}/starlingx/syslog.conf \
	"
		
