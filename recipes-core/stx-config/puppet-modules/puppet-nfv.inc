PACKAGES += " puppet-nfv"

do_install_prepend () {

	install -d -m 0755 ${D}/${datadir}/puppet/modules/nfv
	cp -R ${S}/puppet-modules-wrs/puppet-nfv/src/nfv ${D}/${datadir}/puppet/modules
}

FILES_puppet-nfv += " \
	${datadir}/puppet/modules/nfv \
	"
