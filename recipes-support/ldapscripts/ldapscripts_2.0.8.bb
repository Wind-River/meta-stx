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

SUMMARY = "Shell scripts to manage POSIX accounts in LDAP"

SECTION = "base"
LICENSE = "LGPL2"

LIC_FILES_CHKSUM = "file://COPYING;md5=393a5ca445f6965873eca0259a17f833"

SRC_URI = "https://downloads.sourceforge.net/project/ldapscripts/ldapscripts/ldapscripts-2.0.8/ldapscripts-2.0.8.tgz"
SRC_URI[md5sum] = "99a7222215eaea2c8bc790d0437f22ea"
SRC_URI[sha256sum] = "7db3848501f257a10417c9bcfc0b70b76d0a8093eb993f2354925e156c3419ff"

SRC_URI += " file://sudo-support.patch \
             file://sudo-delete-support.patch \
             file://log_timestamp.patch \
             file://ldap-user-setup-support.patch \
             file://allow-anonymous-bind-for-ldap-search.patch \
             file://ldapscripts.conf.cgcs \
	file://ldapadduser.template.cgcs \
	file://ldapaddgroup.template.cgcs \
	file://ldapmoduser.template.cgcs \
	file://ldapaddsudo.template.cgcs \
	file://ldapmodsudo.template.cgcs \
	file://ldapscripts.passwd \
"

SOURCE1 = "${WORKDIR}/ldapscripts.conf.cgcs"
SOURCE2 = "${WORKDIR}/ldapadduser.template.cgcs"
SOURCE3 = "${WORKDIR}/ldapaddgroup.template.cgcs"
SOURCE4 = "${WORKDIR}/ldapmoduser.template.cgcs"
SOURCE5 = "${WORKDIR}/ldapaddsudo.template.cgcs"
SOURCE6 = "${WORKDIR}/ldapmodsudo.template.cgcs"
SOURCE7 = "${WORKDIR}/ldapscripts.passwd"

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
		MANDIR=${mandir} ETCDIR=${sysconfdir}/ldapscripts \
		LIBDIR=${libdir} install

	rm -Rf ${D}${mandir}/*
	rm -f ${D}${sbindir}/*machine*
	rm -f ${D}${sysconfdir}//ldapscripts/ldapaddmachine.template.sample
	install -m 644 ${SOURCE1} ${D}${sysconfdir}/ldapscripts/ldapscripts.conf
	install -m 644 ${SOURCE2} ${D}${sysconfdir}/ldapscripts/ldapadduser.template.cgcs
	install -m 644 ${SOURCE3} ${D}${sysconfdir}/ldapscripts/ldapaddgroup.template.cgcs
	install -m 644 ${SOURCE4} ${D}${sysconfdir}/ldapscripts/ldapmoduser.template.cgcs
	install -m 644 ${SOURCE5} ${D}${sysconfdir}/ldapscripts/ldapaddsudo.template.cgcs
	install -m 644 ${SOURCE6} ${D}${sysconfdir}/ldapscripts/ldapmodsudo.template.cgcs
	install -m 600 ${SOURCE7} ${D}${sysconfdir}/ldapscripts/ldapscripts.passwd
}

FILES_${PN}_append = " ${libdir}/runtime \
                       ${sysconfdir} \
"
