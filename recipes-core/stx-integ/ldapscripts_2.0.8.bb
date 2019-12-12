DESCRIPTION = " \
The ldapscripts are originally designed to be used within Samba 3.x's \
smb.conf file. They allow to manipulate POSIX entries for users, groups \
and machines in an LDAP directory. They are written in shell and need ldap \
client commands to work correctly (ldapadd, ldapdelete, ldapmodify, \
ldapsearch). Other scripts also are provided as simple tools to (manually) \
query your LDAP directory : ldapfinger, ldapid, lsldap (...). \
 \
They are designed to be used under GNU/Linux or FreeBSD (any other \
recent UNIX-like should also work) and require several binaries that should \
come with your OS (uuencode, getent/pw, date, grep, sed, cut...). \
 \
Latest version available on http://contribs.martymac.org \
"


LICENSE = "LGPL2"

LIC_FILES_CHKSUM = "file://COPYING;md5=393a5ca445f6965873eca0259a17f833"

SRC_URI = "file://ldap/ldapscripts-2.0.8.tgz"
SRC_URI[md5sum] = "99a7222215eaea2c8bc790d0437f22ea"
SRC_URI[sha256sum] = "7db3848501f257a10417c9bcfc0b70b76d0a8093eb993f2354925e156c3419ff"

SRC_URI += " file://ldap/sudo-support.patch \
             file://ldap/sudo-delete-support.patch \
             file://ldap/log_timestamp.patch \
             file://ldap/ldap-user-setup-support.patch \
             file://ldap/allow-anonymous-bind-for-ldap-search.patch \
             file://ldap/ldapscripts.conf.cgcs \
	file://ldap/ldapadduser.template.cgcs \
	file://ldap/ldapaddgroup.template.cgcs \
	file://ldap/ldapmoduser.template.cgcs \
	file://ldap/ldapaddsudo.template.cgcs \
	file://ldap/ldapmodsudo.template.cgcs \
	file://ldap/ldapscripts.passwd \
"

SOURCE1 = "${WORKDIR}/ldap/ldapscripts.conf.cgcs"
SOURCE2 = "${WORKDIR}/ldap/ldapadduser.template.cgcs"
SOURCE3 = "${WORKDIR}/ldap/ldapaddgroup.template.cgcs"
SOURCE4 = "${WORKDIR}/ldap/ldapmoduser.template.cgcs"
SOURCE5 = "${WORKDIR}/ldap/ldapaddsudo.template.cgcs"
SOURCE6 = "${WORKDIR}/ldap/ldapmodsudo.template.cgcs"
SOURCE7 = "${WORKDIR}/ldap/ldapscripts.passwd"

sbindir = "/usr/local/sbin"
mandir = "/usr/local/share/man"
sysconfdir = "/usr/local/etc/ldapscripts"
libdir = "/usr/local/lib/ldapscripts"

do_configure () {
	cd ${S}
	oe_runmake -e configure
}

do_compile () {
	:
}

do_install () {
	cd ${S}
	oe_runmake -e DESTDIR=${D} SBINDIR=${sbindir} \
		MANDIR=${mandir} ETCDIR=${sysconfdir} \
		LIBDIR=${libdir} install

	rm -Rf ${D}${mandir}/*
	rm -f ${D}${sbindir}/*machine*
	rm -f ${D}${sysconfdir}/ldapaddmachine.template.sample
	install -m 644 ${SOURCE1} ${D}${sysconfdir}/ldapscripts.conf
	install -m 644 ${SOURCE2} ${D}${sysconfdir}/ldapadduser.template.cgcs
	install -m 644 ${SOURCE3} ${D}${sysconfdir}/ldapaddgroup.template.cgcs
	install -m 644 ${SOURCE4} ${D}${sysconfdir}/ldapmoduser.template.cgcs
	install -m 644 ${SOURCE5} ${D}${sysconfdir}/ldapaddsudo.template.cgcs
	install -m 644 ${SOURCE6} ${D}${sysconfdir}/ldapmodsudo.template.cgcs
	install -m 600 ${SOURCE7} ${D}${sysconfdir}/ldapscripts.passwd
}

FILES_${PN}_append = " ${libdir}/runtime \
                       ${sysconfdir} \
"
