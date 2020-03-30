python __anonymous() {
   if bb.utils.contains('DEPENDS', 'openssl', True, False, d) or \
      bb.utils.contains('DEPENDS', 'openssl-native', True, False, d):
          d.setVar('DEPENDS', d.getVar('DEPENDS').replace('openssl', 'openssl10'))
          d.setVar('DEPENDS', d.getVar('DEPENDS').replace('openssl-native', 'openssl10-native'))
}

do_configure_prepend () {
    if [ -d ${STAGING_INCDIR}/openssl10 ]; then
        rm -rf ${STAGING_INCDIR}/openssl
        ln -sf ${STAGING_INCDIR}/openssl10 ${STAGING_INCDIR}/openssl
    fi
    if [ -d ${STAGING_LIBDIR}/openssl10 ]; then
        cp -rf ${STAGING_LIBDIR}/openssl10/* ${STAGING_LIBDIR}
    fi
}
