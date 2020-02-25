PACKAGES += " namespace-utils"

RDEPENDS_namespace-utils += " \
	"
DESCRIPTION_namspace-utils = "Titanium Cloud namespace utilities"
SUMMARY_namespace-utils = "namespace utils"

do_configure_append() {
	:
}
do_compile_append() {
	cd ${S}/utilities/namespace-utils/namespace-utils
	$CC -o bashns bashns.c
}

do_install_append() {
	cd ${S}/utilities/namespace-utils/namespace-utils

	install -d -m0755 ${D}/${sbindir}
	install -m0500 bashns ${D}/${sbindir}
	install -m0500 umount-in-namespace  ${D}/${bindir}
}

FILES_namespace-utils = "  \
	${sbindir}/bashns \
	${sbindir}/umount-in-namespace \
	"
