PACKAGES += " systemd-config"

RDEPENDS_systemd-config += " bash"

do_configure_prepend () {
	:
} 

do_compile_prepend () {
	:
}

do_install_prepend () {
	cd ${S}/base/systemd-config
	install -d ${D}/${datadir}/starlingx

	install -m644 files/60-persistent-storage.rules ${D}/${datadir}/starlingx/60-persistent-storage.rules
	install -m644 files/journald.conf ${D}/${datadir}/starlingx/journald.conf
	install -m644 files/systemd.conf.tmpfiles.d ${D}/${datadir}/starlingx/
	install -m644 files/tmp.conf.tmpfiles.d ${D}/${datadir}/starlingx/
	install -m644 files/tmp.mount ${D}/${datadir}/starlingx/

}

FILES_systemd-config = " \
	${datadir}/starlingx/60-persistent-storage.rules \
	${datadir}/starlingx/journald.conf \
	${datadir}/starlingx/systemd.conf.tmpfiles.d \
	${datadir}/starlingx/tmp.conf.tmpfiles.d \
	${datadir}/starlingx/tmp.mount \
	"
