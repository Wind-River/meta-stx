PACKAGES += " rsync-config"

RDEPENDS_rsync-config += " bash"

do_configure_prepend () {
	:
} 

do_compile_prepend () {
	:
}

do_install_prepend () {
	cd ${S}/config-files/rsync-config/
	install -d -m0755 ${D}/${datadir}/starlingx
	install -d -m0755 ${D}/${sysconfdir}/

	install  -m 644 files/rsyncd.conf ${D}/${datadir}/starlingx/stx.rsyncd.conf
}

FILES_rsync-config = " \
	${datadir}/starlingx/stx.rsyncd.conf \
	"
