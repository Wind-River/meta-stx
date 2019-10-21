PACKAGES += " namespace-utils"

RDEPENDS_namespace-utils_append = " bash"

do_configure_append() {
	:
}

do_compile_append() {
	cd ${S}/utilities/namespace-utils/namespace-utils
	${CC} ${TARGET_CFLAGS} -c -o bashns.o bashns.c
	${CC} ${TARGET_LDFLAGS} -o bashns bashns.o
}

do_install_append() {
	cd ${S}/utilities/namespace-utils/namespace-utils

	install -d ${D}/${sbindir}
	install -m 500 bashns  ${D}/${sbindir}
	install -m 500 umount-in-namespace ${D}/${sbindir}
}

FILES_namespace-utils_append = "\
	${sbindir}/bashns \
	${sbindir}/umount-in-namespace \
	"
