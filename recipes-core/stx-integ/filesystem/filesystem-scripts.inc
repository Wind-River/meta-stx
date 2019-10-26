PACKAGES += " filesystem-scripts"

RDEPENDS_filesystem-scripts += " bash"

do_configure_append () {
	:
} 

do_compile_append () {
	:
}

do_install_append () {

	cd ${S}/filesystem/filesystem-scripts/filesystem-scripts-1.0
	install -d -m755 ${D}/${sysconfdir}/init.d
	install -d -m755 ${D}/${libdir}/ocf/resource.d/platform
	install -d -m755 ${D}/${bindir}
	install -d -m755 ${D}/${systemd_system_unitdir}

	install -D -m644 uexportfs  ${D}/${sysconfdir}/init.d
	install -D -m644 nfsserver-mgmt ${D}/${libdir}/ocf/resource.d/platform
	install -D -m644 nfs-mount ${D}/${bindir}
	install -D -m644 uexportfs.service ${D}/${systemd_system_unitdir}

}

FILES_filesystem-scripts_append += " \
	${sysconfdir}/init.d/uexportfs \
	${libdir}/ocf/resource.d/platform/nfsserver-mgmt \
	${bindir}/nfs-mount \
	${systemd_system_unitdir}/uexportfs.service \
		"
