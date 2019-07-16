PACKAGES += " ntp-config"

RDEPENDS_ntp-config += " bash"

do_configure_prepend () {
	:
} 

do_compile_prepend () {
	:
}

do_install_prepend () {
	cd ${S}/config-files/ntp-config 
	install -d -m0755 ${D}/${datadir}/starlingx

	install -m644 files/ntp.conf ${D}/${datadir}/starlingx/ntp.config
	install -m644 files/ntpd.sysconfig ${D}/${datadir}/starlingx/ntpd.sysconfig

}

FILES_ntp-config = " \
	${datadir}/starlingx/ntp.config \
	${datadir}/starlingx/ntpd.sysconfig \
	"
