DESCRIPTION  = "TIS Extensions to thirdparty pkgs"
SUMMARY  = "TIS Extensions to thirdparty pkgs"

require utilities-common.inc

SUBPATH0 = "utilities/stx-extensions/files"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

RDEPENDS_${PN}  += " systemd"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {

	install -p -d -m0755 ${D}/${sysconfdir}/sysctl.d
	install -m0755 coredump-sysctl.conf ${D}/${sysconfdir}/sysctl.d/50-coredump.conf

	install -p -d -m0755 ${D}/${sysconfdir}/systemd/coredump.conf.d
	install -m0755 coredump.conf ${D}/${sysconfdir}/systemd/coredump.conf.d/coredump.conf

	install -p -d -m0755 ${D}/${sysconfdir}/modules-load.d
	install -m0644 modules-load-vfio.conf ${D}/${sysconfdir}/modules-load.d/vfio.conf

}
