FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# erlang < 20.0 is not compatibel with OpenSSL 1.1.x
inherit openssl10

SRC_URI += " \
	file://fix-install-ownership.patch \
	"

do_configure_prepend () {
    export erl_xcomp_sysroot=${STAGING_DIR_HOST}

    if [ -d ${STAGING_INCDIR}/openssl10 ]; then
        rm -rf ${STAGING_INCDIR}/openssl
        ln -sf ${STAGING_INCDIR}/openssl10 ${STAGING_INCDIR}/openssl
    fi
    if [ -d ${STAGING_LIBDIR}/openssl10 ]; then
        cp -rf ${STAGING_LIBDIR}/openssl10/* ${STAGING_LIBDIR}
    fi
    sed -i -e 's/opensslconf.h/opensslconf-64.h/' \
        ${STAGING_INCDIR}/openssl/rc4.h \
        ${STAGING_INCDIR}/openssl/rc2.h
}

do_install_append () {
    # Fix the do_package_qa issue
    chown -R root:root ${D}
}
