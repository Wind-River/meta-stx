PACKAGES += " nfscheck"

RDEPENDS_nfscheck_append += " bash"

do_configure_append () {
	:
} 

do_compile_append () {
	:
}

do_install_append () {

	cd ${S}/filesystem/nfscheck/
	install -d -m755 ${D}/${bindir}
	install -d -m755 ${D}/${systemd_system_unitdir}

	install -D -m644 files/nfscheck.sh ${D}/${bindir}
	install -D -m644 files/nfscheck.service ${D}/${systemd_system_unitdir}


}

pkg_postinst_ontarget_nfscheck () {
	/bin/systemctl enable nfscheck.service
}	

FILES_nfscheck_append += " \
		${systemd_system_unitdir}/nfscheck.service \
		${bindir}/nfscheck.sh \
		"
