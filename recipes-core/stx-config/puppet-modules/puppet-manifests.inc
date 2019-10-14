PACKAGES += " puppet-manifests"
RDEPENDS_puppet-manifests += " bash"

do_install_append () {
	cd ${S}/puppet-manifests/src
	oe_runmake BINDIR=${D}/${bindir} \
		CONFIGDIR=${D}/${sysconfdir}/puppet/ \
		MODULEDIR=${D}/${datadir}/puppet/modules -f Makefile install
}

FILES_puppet-manifests = "\
	${sysconfdir}/puppet/hiera.yaml \
	${sysconfdir}/puppet/hieradata \
	${sysconfdir}/puppet/hieradata/worker.yaml \
	${sysconfdir}/puppet/hieradata/storage.yaml \
	${sysconfdir}/puppet/hieradata/global.yaml \
	${sysconfdir}/puppet/hieradata/controller.yaml \
	${sysconfdir}/puppet/manifests/worker.pp \
	${sysconfdir}/puppet/manifests/ansible_bootstrap.pp \
	${sysconfdir}/puppet/manifests/bootstrap.pp \
	${sysconfdir}/puppet/manifests/runtime.pp \
	${sysconfdir}/puppet/manifests/storage.pp \
	${sysconfdir}/puppet/manifests/upgrade.pp \
	${sysconfdir}/puppet/manifests/controller.pp \
	${datadir}/puppet/modules/openstack/manifests \
	${datadir}/puppet/modules/openstack/templates \
	${datadir}/puppet/modules/platform/manifests \
	${datadir}/puppet/modules/platform/templates \
	${datadir}/puppet/modules/platform/files \
	${datadir}/puppet/modules/platform/lib/facter \
	${datadir}/puppet/modules/platform/lib/puppet \
	${bindir}/puppet-manifest-apply.sh \
	${bindir}/apply_network_config.sh \
	"


