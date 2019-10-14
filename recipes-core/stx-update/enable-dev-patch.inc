PACKAGES += " enable-dev-patch"

do_configure_prepend () {
	:
} 

do_compile_prepend () {
	:
}

do_install_prepend () {
	cd ${S}/enable-dev-patch/
	install -m 755 -d ${D}/${sysconfdir}/pki/wrs
	install -m 444 enable-dev-patch/dev_certificate_enable.bin ${D}/${sysconfdir}/pki/wrs

}

FILES_enable-dev-patch = " \
	${sysconfdir}/pki/wrs/dev_certificate_enable.bin \
	"
