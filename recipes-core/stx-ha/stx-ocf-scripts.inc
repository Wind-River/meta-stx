PACKAGES += " stx-ocf-scripts"

RDEPENDS_stx-ocf-scripts.inc += " bash"

do_configure_append () {
	:
}

do_compile_append() {
	:
}

do_install_append () {
	cd ${S}/stx-ocf-scripts/src/ocf

	install -d -m 755 ${D}/usr/lib/ocf/resource.d/openstack
	install -p -D -m 755 $(find . -type f) ${D}/usr/lib/ocf/resource.d/openstack/ 
}

FILES_stx-ocf-scripts = " \
	${libdir}/ocf/resource.d/openstack/ \
	"
