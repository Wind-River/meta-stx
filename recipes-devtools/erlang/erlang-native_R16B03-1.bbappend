FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# erlang < 20.0 is not compatibel with OpenSSL 1.1.x
#DEPENDS += "openssl10-native"
#DEPENDS_remove += "openssl-native"

inherit openssl10
DEPENDS_append = " openssl-native"

SRC_URI += "file://erts-configure.in-avoid-RPATH-warning.patch"

EXTRA_OECONF = '--with-ssl --without-krb5 --without-zlib'

inherit autotools gettext
do_configure_prepend() {
	cd erts; autoreconf; cd -
	export erl_xcomp_sysroot=${STAGING_DIR_HOST}/usr
	export erl_xcomp_isysroot=${STAGING_DIR_HOST}
}
