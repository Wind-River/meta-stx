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
	distutils_do_install
	install -d -m 755 ${D}/${systemd_system_unitdir}
	install -p -D -m 644 scripts/fm-api.service ${D}/${systemd_system_unitdir}
	install -p -D -m 755 scripts/fm-api ${D}/${sysconfdir}/init.d/fm-api
	install -p -D -m 644 fm-api-pmond.conf ${D}/${sysconfdir}/pmon.d/fm-api.conf

	# fix the path for init scripts
	sed -i -e 's|rc.d/||' ${D}/${systemd_system_unitdir}/*.service
}

FILES_fm-rest-api = " \
	${systemd_system_unitdir}/fm-api.service \
	${sysconfdir}/init.d/fm-api \
	${sysconfdir}/pmon.d/fm-api.conf \
	${libdir}/python2.7/site-packages/fm \
	${libdir}/python2.7/site-packages/fm-1.0.0-py2.7.egg-info \
	${bindir}/fm-dbsync \
	${bindir}/fm-api \
	"

#pkg_postinst_ontarget_fm-rest-api() {
#	/usr/bin/systemctl enable fm-api.service
#}