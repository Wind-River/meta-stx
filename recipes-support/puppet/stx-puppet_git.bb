DESCRIPTION = "stx-puppet modules"

STABLE = "starlingx/master"
PROTOCOL = "https"
SRCNAME = "stx-puppet"
BRANCH = "r/stx.3.0"
SRCREV = "678fe78b72b70e213eae32b1932afe97cc8c16b4"
S = "${WORKDIR}/git"
PV = "1.0.0"

LICENSE = "Apache-2.0"

# TODO:
#0e5ccf641e613489e66aa98271dbe798  ./modules/puppet-dcdbsync/src/LICENSE
#0e5ccf641e613489e66aa98271dbe798  ./modules/puppet-dcmanager/src/LICENSE
#0e5ccf641e613489e66aa98271dbe798  ./modules/puppet-dcorch/src/LICENSE
#0e5ccf641e613489e66aa98271dbe798  ./modules/puppet-fm/src/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./modules/puppet-mtce/src/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./modules/puppet-nfv/src/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./modules/puppet-patching/src/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./modules/puppet-smapi/src/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./modules/puppet-sshd/src/LICENSE
#0e5ccf641e613489e66aa98271dbe798  ./modules/puppet-sysinv/src/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./puppet-manifests/src/LICENSE

LIC_FILES_CHKSUM = " \
	file://modules/puppet-mtce/src/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
	"

SRC_URI = "git://opendev.org/starlingx/${SRCNAME}.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"


RDEPENDS_${PN} += " puppet" 
RDEPENDS_puppet-manifests += " bash"
RDEPENDS_puppet-mtce += " puppet"


PACKAGES += " puppet-dcdbsync"
PACKAGES += " puppet-dcmanager"
PACKAGES += " puppet-dcorch"
PACKAGES += " puppet-fm"
PACKAGES += " puppet-mtce"
PACKAGES += " puppet-nfv"
PACKAGES += " puppet-patching"
PACKAGES += " puppet-smapi"
PACKAGES += " puppet-sshd"
PACKAGES += " puppet-sysinv"
PACKAGES += " puppet-manifests"

do_install() {

	install -d -m 0755 ${D}/${datadir}/puppet/modules/dcdbsync
	cp -R ${S}/modules/puppet-dcdbsync/src/dcdbsync ${D}/${datadir}/puppet/modules

	install -d -m 0755 ${D}/${datadir}/puppet/modules/dcmanager
	cp -R ${S}/modules/puppet-dcmanager/src/dcmanager ${D}/${datadir}/puppet/modules

	install -d -m 0755 ${D}/${datadir}/puppet/modules/dcorch
	cp -R ${S}/modules/puppet-dcorch/src/dcorch/ ${D}/${datadir}/puppet/modules/

	install -d -m 0755 ${D}/${datadir}/puppet/modules/fm
	cp -R ${S}/modules/puppet-fm/src/fm ${D}/${datadir}/puppet/modules

	install -d -m 0755 ${D}/${datadir}/puppet/modules/mtce
	cp -R ${S}/modules/puppet-mtce/src/mtce ${D}/${datadir}/puppet/modules

	install -d -m 0755 ${D}/${datadir}/puppet/modules/nfv
	cp -R ${S}/modules/puppet-nfv/src/nfv ${D}/${datadir}/puppet/modules

	install -d -m 0755 ${D}/${datadir}/puppet/modules/patching
	cp -R ${S}/modules/puppet-patching/src/patching ${D}/${datadir}/puppet/modules

	install -d -m 0755 ${D}/${datadir}/puppet/modules/smapi
	cp -R ${S}/modules/puppet-smapi/src/smapi ${D}/${datadir}/puppet/modules

	install -d -m 0755 ${D}/${datadir}/puppet/modules/sshd
	cp -R ${S}/modules/puppet-sshd/src/sshd ${D}/${datadir}/puppet/modules

	install -d -m 0755 ${D}/${datadir}/puppet/modules/sysinv
	cp -R ${S}/modules/puppet-sysinv/src/sysinv ${D}/${datadir}/puppet/modules

	cd ${S}/puppet-manifests/src
	oe_runmake BINDIR=${D}/${bindir} \
		CONFIGDIR=${D}/${sysconfdir}/puppet/ \
		MODULEDIR=${D}/${datadir}/puppet/modules -f Makefile install

}

FILES_puppet-sysinv += " \
	${datadir}/puppet/modules/sysinv \
	"

FILES_puppet-sshd += " \
	${datadir}/puppet/modules/sshd \
	"

FILES_puppet-smapi += " \
	${datadir}/puppet/modules/smapi \
	"
FILES_puppet-patching += " \
	${datadir}/puppet/modules/patching \
	"

FILES_puppet-nfv += " \
	${datadir}/puppet/modules/nfv \
	"

FILES_puppet-mtce = "\
	${datadir}/puppet/modules/mtce \
	"

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
FILES_puppet-fm += " \
	${datadir}/puppet/modules/fm \
	"

FILES_puppet-dcorch += " \
	${datadir}/puppet/modules/dcorch \
	"

FILES_puppet-dcmanager += " \
	${datadir}/puppet/modules/dcmanager \
	"

FILES_puppet-dcdbsync += " \
	${datadir}/puppet/modules/dcdbsync \
	"
