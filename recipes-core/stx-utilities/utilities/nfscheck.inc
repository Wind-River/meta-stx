PACKAGES += " nfscheck"

RDEPENDS_nfscheck  += " bash systemd"
DESCRIPTION_nfscheck = "NFS Audit"
SUMMARY_nfscheck = "NFS Audit"

do_configure_append() {
	:
}
do_compile_append() {
	:
}

do_install_append() {
	cd ${S}/utilities/nfscheck/files

	install -d -m0755 ${D}/${bindir}
	install -m0755 nfscheck.sh ${D}/${bindir}

	install -d -m0755 ${D}/${systemd_system_unitdir}
	install -m0644 nfscheck.service ${D}/${systemd_system_unitdir}
}

FILES_nfscheck = "  \
	${bindir}/nfscheck.sh \
	${systemd_system_unitdir}/nfscheck.service \
	"
