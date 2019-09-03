PACKAGES += " puppet-dcmanager"

do_install_prepend () {

	install -d -m 0755 ${D}/${datadir}/puppet/modules/dcmanager
	cp -R ${S}/puppet-modules-wrs/puppet-dcmanager/src/dcmanager ${D}/${datadir}/puppet/modules
}

FILES_puppet-dcmanager += " \
	${datadir}/puppet/modules/dcmanager \
	"
