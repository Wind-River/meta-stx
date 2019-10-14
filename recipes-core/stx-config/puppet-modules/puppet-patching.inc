PACKAGES += " puppet-patching"

do_install_prepend () {

	install -d -m 0755 ${D}/${datadir}/puppet/modules/patching
	cp -R ${S}/puppet-modules-wrs/puppet-patching/src/patching ${D}/${datadir}/puppet/modules
}

FILES_puppet-patching += " \
	${datadir}/puppet/modules/patching \
	"
