FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

#####################################################################################
# Port is NOT complete yet:
# See files/centos_patches_notported_yet for patches that have not been ported yet. 
# See files/sources for scripts and conf files that need to be set in the recipe. 
#####################################################################################


SRC_URI += " \
	file://rootdn-should-not-bypass-ppolicy.patch \
	file://0001-Various-manual-pages-changes.patch \
	file://0002-Correct-log-levels-in-ppolicy-overlay.patch \
	file://0003-Removes-unnecessary-linking-of-SQL-Libs-into-slad.patch \
	file://0004-openlap-reentrant-gethostby.patch \
	file://0005-openldap-smbk5pwd-overlay.patch \
	file://0006-openldap-ldaprc-currentdir.patch \
	file://0007-openldap-userconfig-setgid.patch \
	file://0008-openldap-allop-overlay.patch \
	file://0009-openldap-syncrepl-unset-tls-options.patch \
	file://0010-openldap-ai-addrconfig.patch \
	file://0011-openldap-switch-to-t_dlopenadvise-to-get-RTLD_GLOBAL.patch \
	file://0012-openldap-ldapi-sasl.patch \
	file://0013-openldap-missing-unlock-in-accesslog-overlay.patch \
	file://0014-openldap-module-passwd-sha2.patch \
	file://0015-openldap-man-tls-reqcert.patch \
	file://0016-openldap-man-ldap-conf.patch \
	file://0017-openldap-bdb_idl_fetch_key-correct-key-pointer.patch \
	file://0018-openldap-tlsmc.patch \
	file://0019-openldap-fedora-systemd.patch \
	"

inherit pkgconfig

PACKAGECONFIG_CONFARGS_remove = "--with-tls=gnutls "
DEPENDS += " \
	openssl \
	glibc \
	mariadb \
	mariadb-native \
	libtirpc \
	"


# Defaults:
#	--enable-bdb=no
#	--enable-hdb=no
#	--enable-bdb=no
# 	--enable-monitor=mod 
######
# Stx :
#	--enable-wrappers=yes
#	--enable-moznss-compatibility=yes

#################
# TODO:
#	mysql_config: native command missing

EXTRA_OECONF += " \
		--enable-syslog \
		--enable-crypt \
		--enable-proctitle \
		--enable-ipv6 \
		--enable-local \
		--enable-slapd \
		--enable-dynacl \
		--enable-aci \
		--enable-cleartext \
		--enable-modules \
		--enable-lmpasswd \
		--enable-rewrite \
		--enable-rlookups \
		--disable-slp \
		--enable-wrappers=no \
		--enable-backends=mod \
		--enable-bdb=yes \
		--enable-hdb=yes \
		--enable-mdb=yes \
		--enable-monitor=yes \
		--disable-ndb \
		--enable-overlays=mod \
		--disable-static \
		--enable-shared \
		--enable-moznss-compatibility=no \
		--with-cyrus-sasl \
		--without-fetch \
		--with-tls=openssl \
		"

do_configure_append () {
   cd ${S}
   ln -f -s ${S}/contrib/slapd-modules/smbk5pwd/smbk5pwd.c servers/slapd/overlays
   ln -f -s ${S}/contrib/slapd-modules/allop/allop.c servers/slapd/overlays
   ln -f -s ${S}/contrib/slapd-modules/passwd/sha2/{sha2.{c,h},slapd-sha2.c} servers/slapd/overlays
}

# If liblmdb is needed, then patch the Makefile
#do_compile_append () {
#   cd ${S}/libraries/liblmdb
#   oe_runmake
#}

FILES_${PN}_append = " ${libexecdir}/openldap/*"


