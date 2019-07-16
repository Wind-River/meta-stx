PACKAGES += " setup-config"

RDEPENDS_setup-config += " bash"

do_configure_prepend () {
	:
} 

do_compile_prepend () {
	:
}

do_install_prepend () {
	cd ${S}/base/setup-config
	install -d ${D}/${datadir}/starlingx
	install -d ${D}/${sysconfdir}/profile.d

	install -m640 files/motd ${D}/${datadir}/starlingx/stx.motd
	install -m640 files/prompt.sh ${D}/${sysconfdir}/profile.d/prompt.sh
	install -m640 files/custom.sh ${D}/${sysconfdir}/profile.d/custom.sh

}

FILES_setup-config = " \
	${datadir}/starlingx/stx.motd \
	${sysconfdir}/profile.d/prompt.sh \
	${sysconfdir}/profile.d/custom.sh \
	"
		
