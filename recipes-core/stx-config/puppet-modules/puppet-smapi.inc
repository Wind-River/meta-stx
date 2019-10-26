PACKAGES += " puppet-smapi"

do_install_prepend () {

	install -d -m 0755 ${D}/${datadir}/puppet/modules/smapi
	cp -R ${S}/puppet-modules-wrs/puppet-smapi/src/smapi ${D}/${datadir}/puppet/modules
}

FILES_puppet-smapi += " \
	${datadir}/puppet/modules/smapi \
	"
