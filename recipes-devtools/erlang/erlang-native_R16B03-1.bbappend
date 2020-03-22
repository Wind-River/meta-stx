FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# erlang < 20.0 is not compatibel with OpenSSL 1.1.x
DEPENDS += "openssl10-native"
DEPENDS_remove += "openssl-native"

SRC_URI += "file://erts-configure.in-avoid-RPATH-warning.patch"

EXTRA_OECONF = '--with-ssl'

do_configure_prepend () {
    if [ -d ${STAGING_INCDIR}/openssl10 ]; then
        rm -rf ${STAGING_INCDIR}/openssl
        ln -sf ${STAGING_INCDIR}/openssl10 ${STAGING_INCDIR}/openssl
    fi
    if [ -d ${STAGING_LIBDIR}/openssl10 ]; then
        cp -rf ${STAGING_LIBDIR}/openssl10/* ${STAGING_LIBDIR}
    fi
}
