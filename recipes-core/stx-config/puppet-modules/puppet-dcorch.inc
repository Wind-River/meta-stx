PACKAGES += " puppet-dcorch"

do_install_prepend () {

	install -d -m 0755 ${D}/${datadir}/puppet/modules/dcorch
	cp -R ${S}/puppet-modules-wrs/puppet-dcorch/src/dcorch/ ${D}/${datadir}/puppet/modules/
}

FILES_puppet-dcorch += " \
	${datadir}/puppet/modules/dcorch \
	"
