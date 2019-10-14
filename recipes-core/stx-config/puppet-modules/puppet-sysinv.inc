PACKAGES += " puppet-sysinv"

do_install_prepend () {

	install -d -m 0755 ${D}/${datadir}/puppet/modules/sysinv
	cp -R ${S}/puppet-modules-wrs/puppet-sysinv/src/sysinv ${D}/${datadir}/puppet/modules


}

FILES_puppet-sysinv += " \
	${datadir}/puppet/modules/sysinv \
	"
