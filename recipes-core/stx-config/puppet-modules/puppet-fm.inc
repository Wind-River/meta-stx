PACKAGES += " puppet-fm"

do_install_prepend () {

	install -d -m 0755 ${D}/${datadir}/puppet/modules/fm
	cp -R ${S}/puppet-modules-wrs/puppet-fm/src/fm ${D}/${datadir}/puppet/modules
}

FILES_puppet-fm += " \
	${datadir}/puppet/modules/fm \
	"
