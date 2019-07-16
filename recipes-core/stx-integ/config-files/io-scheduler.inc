PACKAGES += " io-scheduler"

RDEPENDS_io-scheduler += " bash"

do_configure_prepend () {
	:
} 

do_compile_prepend () {
	:
}

do_install_prepend () {
	install -d -m 0755 ${D}/${sysconfdir}/udev/rules.d
	install -m644 ${S}/config-files/io-scheduler/centos/files/60-io-scheduler.rules \
		${D}/${sysconfdir}/udev/rules.d/60-io-scheduler.rules 
}

FILES_io-scheduler = " \
	${sysconfdir}/udev/rules.d/60-io-scheduler.rules \
	"
