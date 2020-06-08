
PACKAGES += " stx-extensions"

RDEPENDS_stx-extensions  += " systemd"
DESCRIPTION_stx-extensions  = "TIS Extensions to thirdparty pkgs"
SUMMARY_stx-extensions  = "TIS Extensions to thirdparty pkgs"

inherit setuptools distutils

do_configure_append() {
	:
}
do_compile_append() {
	:
}

do_install_append() {
	cd ${S}/utilities/stx-extensions/files


	install -p -d -m0755 ${D}/${sysconfdir}/sysctl.d
	install -m0755 coredump-sysctl.conf ${D}/${sysconfdir}/sysctl.d/50-coredump.conf

	install -p -d -m0755 ${D}/${sysconfdir}/systemd/coredump.conf.d
	install -m0755 coredump.conf ${D}/${sysconfdir}/systemd/coredump.conf.d/coredump.conf

	install -p -d -m0755 ${D}/${sysconfdir}/modules-load.d
	install -m0644 modules-load-vfio.conf ${D}/${sysconfdir}/modules-load.d/vfio.conf

}

FILES_stx-extensions = "  \
	${sysconfdir}/sysctl.d/50-coredump.conf \
	${sysconfdir}/systemd/coredump.conf.d/coredump.conf \
	${sysconfdir}/modules-load.d/vfio.conf \
	"
