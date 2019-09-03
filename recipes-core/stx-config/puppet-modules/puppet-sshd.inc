PACKAGES += " puppet-sshd"

do_install_prepend () {

	install -d -m 0755 ${D}/${datadir}/puppet/modules/sshd
	cp -R ${S}/puppet-modules-wrs/puppet-sshd/src/sshd ${D}/${datadir}/puppet/modules
}

FILES_puppet-sshd += " \
	${datadir}/puppet/modules/sshd \
	"
