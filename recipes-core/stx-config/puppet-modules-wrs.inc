PACKAGES += "puppet-modules-wrs"

do_install_prepend () {

	install -d -m 0755 ${D}/${datadir}/puppet/modules/dcdbsync
	install -d -m 0755 ${D}/${datadir}/puppet/modules/mtce
	install -d -m 0755 ${D}/${datadir}/puppet/modules/patching
	install -d -m 0755 ${D}/${datadir}/puppet/modules/sshd
	install -d -m 0755 ${D}/${datadir}/puppet/modules/dcmanager
	install -d -m 0755 ${D}/${datadir}/puppet/modules/fm
	install -d -m 0755 ${D}/${datadir}/puppet/modules/nfv
	install -d -m 0755 ${D}/${datadir}/puppet/modules/smapi
	install -d -m 0755 ${D}/${datadir}/puppet/modules/sysinv
	cp -R ${S}/puppet-modules-wrs/puppet-dcdbsync/src/dcdbsync ${D}/${datadir}/puppet/modules
	cp -R ${S}/puppet-modules-wrs/puppet-dcorch/src/dcorch ${D}/${datadir}/puppet/modules
	cp -R ${S}/puppet-modules-wrs/puppet-patching/src/patching ${D}/${datadir}/puppet/modules
	cp -R ${S}/puppet-modules-wrs/puppet-sshd/src/sshd ${D}/${datadir}/puppet/modules
	cp -R ${S}/puppet-modules-wrs/puppet-dcmanager/src/dcmanager ${D}/${datadir}/puppet/modules
	cp -R ${S}/puppet-modules-wrs/puppet-fm/src/fm ${D}/${datadir}/puppet/modules
	cp -R ${S}/puppet-modules-wrs/puppet-nfv/src/nfv ${D}/${datadir}/puppet/modules
	cp -R ${S}/puppet-modules-wrs/puppet-smapi/src/smapi ${D}/${datadir}/puppet/modules
	cp -R ${S}/puppet-modules-wrs/puppet-sysinv/src/sysinv ${D}/${datadir}/puppet/modules


}

FILES_puppet-modules-wrs_prepend += " \
	${datadir}/puppet/modules/dcdbsync \
	${datadir}/puppet/modules/dcmanager \
	${datadir}/puppet/modules/patching \
	${datadir}/puppet/modules/mtce \
	${datadir}/puppet/modules/dcorch \
	${datadir}/puppet/modules/sshd \
	${datadir}/puppet/modules/nfv \
	${datadir}/puppet/modules/fm \
	${datadir}/puppet/modules/smapi \
	${datadir}/puppet/modules/sysinv \
	"
