
SYSROOT_NATIVE_DIRS = " "

SYSROOT_PREPROCESS_FUNCS += "openssl10_avoid_conflict"

openssl10_avoid_conflict () {
	# For libaries
	mkdir -p ${SYSROOT_DESTDIR}${libdir}/openssl10
	mv ${SYSROOT_DESTDIR}${libdir}/lib* ${SYSROOT_DESTDIR}${libdir}/engines \
	    ${SYSROOT_DESTDIR}${libdir}/ssl ${SYSROOT_DESTDIR}${libdir}/pkgconfig \
	    ${SYSROOT_DESTDIR}${libdir}/openssl10

	# For headers
	mv ${SYSROOT_DESTDIR}${includedir}/openssl ${SYSROOT_DESTDIR}${includedir}/openssl10

	rm -rf ${SYSROOT_DESTDIR}${bindir} ${SYSROOT_DESTDIR}${sysconfdir}
}
