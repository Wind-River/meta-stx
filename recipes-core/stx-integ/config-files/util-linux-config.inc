PACKAGES += " util-linux-config"

RDEPENDS_util-linux-config += " bash"

do_configure_prepend () {
	:
} 

do_compile_prepend () {
	:
}

do_install_prepend () {
	cd ${S}/config-files/util-linux-config 
	install -d -m0755 ${D}/${datadir}/starlingx
	install -d -m0755 ${D}/${sysconfdir}/pam.d

	install -m644 files/stx.su ${D}/${datadir}/starlingx/stx.su
	install -m644 files/stx.login ${D}/${datadir}/starlingx/stx.login

}

FILES_util-linux-config = " \
	${datadir}/starlingx/stx.su \
	${datadir}/starlingx/stx.login \
	"

