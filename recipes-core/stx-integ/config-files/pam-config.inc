PACKAGES += " pam-config"

RDEPENDS_pam-config += " bash"

do_configure_prepend () {
	:
} 

do_compile_prepend () {
	:
}

do_install_prepend () {
	cd ${S}/config-files/pam-config/
	install -d -m0755 ${D}/${datadir}/starlingx
	install -d -m0755 ${D}/${sysconfdir}/pam.d

	install  -m 644 files/common-account  ${D}/${sysconfdir}/pam.d/common-account
	install  -m 644 files/common-auth     ${D}/${sysconfdir}/pam.d/common-auth
	install  -m 644 files/common-password ${D}/${sysconfdir}/pam.d/common-password
	install  -m 644 files/common-session  ${D}/${sysconfdir}/pam.d/common-session
	install  -m 644 files/common-session-noninteractive ${D}${sysconfdir}/pam.d/common-session-noninteractive
	install  -m 644 files/system-auth.pamd ${D}/${datadir}/starlingx/stx.system-auth
	install  -m 644 files/system-auth.pamd ${D}/${sysconfdir}/pam.d/system-auth
}

FILES_pam-config = " \
	${sysconfdir}/pam.d/common-account \
	${sysconfdir}/pam.d/common-auth \
	${sysconfdir}/pam.d/common-password \
	${sysconfdir}/pam.d/common-session \
	${sysconfdir}/pam.d/common-session-noninteractive \
	${datadir}/starlingx/stx.system-auth \
	${sysconfdir}/pam.d/system-auth \
	"
