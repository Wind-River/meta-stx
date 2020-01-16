FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# This bbappend replaces stx sudo-config

DEPENDS += " \
	openldap \
	libgcrypt \
	"

SRC_URI += " \
	file://sysadmin \
	file://sudo-1.6.7p5-strip.patch \
	file://sudo-1.7.2p1-envdebug.patch \
	file://sudo-1.8.23-sudoldapconfman.patch \
	file://sudo-1.8.23-legacy-group-processing.patch \
	file://sudo-1.8.23-ldapsearchuidfix.patch \
	file://sudo-1.8.6p7-logsudouser.patch \
	file://sudo-1.8.23-nowaitopt.patch \
	file://sudo-1.8.23-Ignore-PAM_NEW_AUTHTOK_REQD-and-PAM_AUTHTOK_EXPIRED.patch \
	file://sudo-1.8.23-fix-double-quote-parsing-for-Defaults-values.patch \
	"

EXTRA_OECONF += " \
	--with-pam-login \
	--with-editor=/bin/vi \
	--with-env-editor \
	--with-ignore-dot \
	--with-tty-tickets \
	--with-ldap \
	--with-ldap-conf-file="${sysconfdir}/sudo-ldap.conf" \
	--with-passprompt="[sudo] password for %Zp: " \
	--with-linux-audit \
	--with-sssd \
	"
	

do_install_append () {
	install -m644 ${S}/../sysadmin ${D}/${sysconfdir}/sudoers.d/sysadmin
	install -m755 -d ${D}/${sysconfdir}/openldap/schema
	install -m644 ${S}/doc/schema.OpenLDAP  ${D}/${sysconfdir}/openldap/schema/sudo.schema
}

pkg_postinst_ontarget_sudo_append () {

# We do this with extrausers_config.bbclass
#SYSADMIN_P="4SuW8cnXFyxsk"
#/usr/sbin/groupadd sys_protected
#/usr/sbin/useradd -m -g sys_protected -G root \
#	 	-d /home/sysadmin -p $SYSADMIN_P \
#		-s /bin/sh sysadmin 2> /dev/null || :

/usr/bin/chage -d0 sysadmin
}

# This means sudo package only owns files
# to avoid install conflict with openldap on 
# /etc/openldap. Sure there is a better way.
DIRFILES = "1"
