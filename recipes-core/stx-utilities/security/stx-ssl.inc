PACKAGES += " stx-ssl"

DESCRIPTION_stx-ssl = " Wind River Security"

do_install_append() {

	openssl req -new -x509 -sha256 \
		-keyout ${S}/security/stx-ssl/self-signed-server-cert.pem \
		-out ${S}/security/stx-ssl/self-signed-server-cert.pem \
		-days 365 -nodes \
		-config ${S}/security/stx-ssl/server-csr.conf

	install -p -d -m0700 ${D}/${sysconfdir}/ssl/private/
	install -m0400 ${S}/security/stx-ssl/self-signed-server-cert.pem \
		${D}/${sysconfdir}/ssl/private/self-signed-server-cert.pem 

#	install -p -d -m0755 ${D}/${sbindir}
#	install -m0700 ${S}/security/stx-ssl/files/tpmdevice-setup ${D}/${sbindir}/tpmdevice-setup

	#install -d -m 0755 ${D}/${datadir}/stx-ssl-${PV}/
	#install -m644 ${S}/security/stx-ssl/LICENSE ${D}/${datadir}/stx-ssl-${PV}/
}

FILES_stx-ssl = " \
	${sysconfdir}/ssl/private/self-signed-server-cert.pem  \
	"
#	${sbindir}/tpmdevice-setup 
