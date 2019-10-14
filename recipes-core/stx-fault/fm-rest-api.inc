PACKAGES += " fm-rest-api"

do_configure_append () {
	cd ${S}/fm-rest-api/fm
	distutils_do_configure
} 

do_compile_append() {
	cd ${S}/fm-rest-api/fm
	distutils_do_compile
}

do_install_append() {
	cd ${S}/fm-rest-api/fm
	distutils_do_compile
	install -d -m 755 ${D}/${systemd_system_unitdir}
	install -p -D -m 644 scripts/fm-api.service ${D}/${systemd_system_unitdir}
	install -p -D -m 755 scripts/fm-api ${D}/${sysconfdir}/init.d/fm-api
	install -p -D -m 644 fm-api-pmond.conf ${D}/${sysconfdir}/pmon.d/fm-api.conf

}

FILES_fm-rest-api = " \
	${systemd_system_unitdir}/fm-api.service \
	${sysconfdir}/init.d/fm-api \
	${sysconfdir}/pmon.d/fm-api.conf \
	"

