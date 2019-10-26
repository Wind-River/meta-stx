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
}

FILES_${PN}_append = " ${libdir}/runtime"
