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
	file://0019-openldap-openssl-ITS7596-Add-EC-support.patch \
	file://0020-openldap-openssl-ITS7596-Add-EC-support-patch-2.patch \
	file://0021-openldap-and-stx-source-and-config-files.patch \
	file://0022-ltb-project-openldap-ppolicy-check-password-1.1.patch \
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

RDEPENDS_${PN}_append = " bash"


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
#   cd ${S}/ltb-project-openldap-ppolicy-check-password-1.1
#   oe_runmake
#}

do_install_append () {
	
	# For this we need to build ltb-project-openldap
	#install -m 755 check_password.so.%{check_password_version} %{buildroot}%{_libdir}/openldap/

	cd ${S}/stx-sources
	install -m 0755 -d ${D}/var/run/openldap
	install -m 0755 -d ${D}/${sysconfdir}/tmpfiles.d
	install -m 0755 ${S}/stx-sources/slapd.tmpfiles ${D}/${sysconfdir}/tmpfiles.d/slapd.conf 
	install -m 0755 ${S}/stx-sources/openldap.tmpfiles ${D}/${sysconfdir}/tmpfiles.d/openldap.conf 
	install -m 0755 ${S}/stx-sources/ldap.conf ${D}/${sysconfdir}/tmpfiles.d/ldap.conf 

	install -m 0644 libexec-functions ${D}/${libexecdir}/openldap/functions
	install -m 0755 libexec-convert-config.sh ${D}/${libexecdir}/openldap/convert-config.sh
	install -m 0755 libexec-check-config.sh ${D}/${libexecdir}/openldap/check-config.sh
	install -m 0755 libexec-upgrade-db.sh ${D}/${libexecdir}/openldap/upgrade-db.sh

	install -m 0755 libexec-create-certdb.sh ${D}/${libexecdir}/openldap/create-certdb.sh
	install -m 0755 libexec-generate-server-cert.sh ${D}/${libexecdir}/openldap/generate-server-cert.sh
	install -m 0755 libexec-update-ppolicy-schema.sh ${D}/${libexecdir}/openldap/update-ppolicy-schema.sh

	install -m 0644  slapd.service ${D}/${systemd_unitdir}/stx-slapd.service
	install -m 0755 -d ${D}/${sysconfdir}/sysconfig
	install -m 0644 slapd.sysconfig ${D}/${sysconfdir}/sysconfig/slapd.sysconfig
	install -m 0755 -d ${D}/${datadir}/openldap-servers
	install -m 0644 slapd.ldif ${D}/${datadir}/openldap-servers/slapd.ldif
	install -m 0750 -d ${D}/${sysconfdir}/openldap/slapd.d
}

FILES_${PN}_append = " \
		${datadir}/openldap-servers/ \
		${libexecdir}/openldap/ \
		/run/openldap \
		${sysconfdir}/sysconfig \
		${sysconfdir}/tmpfiles.d \
		${systemd_unitdir}/stx-slapd.service \
		"
