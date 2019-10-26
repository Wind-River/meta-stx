PACKAGES += " puppet-dcdbsync"

do_install_prepend () {

	install -d -m 0755 ${D}/${datadir}/puppet/modules/dcdbsync
	cp -R ${S}/puppet-modules-wrs/puppet-dcdbsync/src/dcdbsync ${D}/${datadir}/puppet/modules
}

FILES_puppet-dcdbsync += " \
	${datadir}/puppet/modules/dcdbsync \
	"
