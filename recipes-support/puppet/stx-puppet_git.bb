DESCRIPTION = "stx-puppet modules"

STABLE = "starlingx/master"
PROTOCOL = "https"
SRCNAME = "stx-puppet"
BRANCH = "r/stx.3.0"
SRCREV = "678fe78b72b70e213eae32b1932afe97cc8c16b4"
S = "${WORKDIR}/git"
PV = "1.0.0"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = " \
	file://modules/puppet-dcdbsync/src/LICENSE;md5=0e5ccf641e613489e66aa98271dbe798 \
	file://modules/puppet-dcmanager/src/LICENSE;md5=0e5ccf641e613489e66aa98271dbe798 \
	file://modules/puppet-dcorch/src/LICENSE;md5=0e5ccf641e613489e66aa98271dbe798 \
	file://modules/puppet-fm/src/LICENSE;md5=0e5ccf641e613489e66aa98271dbe798 \
	file://modules/puppet-mtce/src/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
	file://modules/puppet-nfv/src/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
	file://modules/puppet-patching/src/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
	file://modules/puppet-smapi/src/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
	file://modules/puppet-sshd/src/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
	file://modules/puppet-sysinv/src/LICENSE;md5=0e5ccf641e613489e66aa98271dbe798 \
	file://puppet-manifests/src/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
	"

SRC_URI = " \
	git://opendev.org/starlingx/${SRCNAME}.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH} \
	file://${PN}/poky-dcmanager-updates.patch \
	file://${PN}/poky-dcorch-updates.patch \
	file://${PN}/poky-sysinv-updates.patch \
	file://${PN}/stx-puppet-poky.patch \
	file://${PN}/0001-puppet-manifest-apply-rebase-adjust-path.patch \
	file://${PN}/0001-puppet-manifests-port-Adjust-path-default-bindir.patch \
	"


RDEPENDS_${PN} += " \
	bash puppet \
	e2fsprogs-resize2fs \
	hiera \
        puppet-staging \
        puppet-oslo \
        puppetlabs-apache \
        puppetlabs-mysql \
	"

# WRS puppet modules
RDEPENDS_puppet-manifests += " \
	puppet-dcorch \
	puppet-dcmanager \
	puppet-mtce \
	puppet-nfv \
	puppet-patching \
	puppet-sysinv \
	puppet-sshd \
	puppet-smapi \
	puppet-fm \
	puppet-dcdbsync \
	"
# Openstack puppet modules
RDEPENDS_puppet-manifests += " \
	puppet-barbican \
	puppet-ceph \
	puppet-horizon \
	puppet-keystone \
	puppet-openstacklib \
	puppet-vswitch \
	puppet-memcached \
	"

# Puppetlabs puppet modules 
RDEPENDS_puppet-manifests += " \
        puppetlabs-concat \
        puppetlabs-create-resources \
        puppet-drbd \
        puppetlabs-firewall \
        puppetlabs-haproxy \
        puppetlabs-inifile \
        puppetlabs-lvm \
        puppetlabs-postgresql \
        puppetlabs-rabbitmq \
        puppetlabs-stdlib \
        puppet-sysctl \
        puppet-etcd \
	"

# 3rd party puppet modules
RDEPENDS_puppet-manifests += " \
        puppet-boolean \
        puppet-certmonger \
        puppet-dnsmasq \
        puppet-filemapper \
        puppet-kmod \
        puppet-ldap \
        puppet-network \
        puppet-nslcd \
        puppet-nssdb \
        puppet-puppi \
        puppet-vlan \
        puppet-collectd \
	"


 
RDEPENDS_puppet-mtce += " puppet"
RDEPENDS_puppet-dcdbsync += " puppet" 
RDEPENDS_puppet-dcmanager += " puppet"
RDEPENDS_puppet-dcorch += " puppet"
RDEPENDS_puppet-fm += " puppet"
RDEPENDS_puppet-nfv += " puppet"
RDEPENDS_puppet-patching += " puppet"
RDEPENDS_puppet-smapi += " puppet"
RDEPENDS_puppet-sshd += " puppet"
RDEPENDS_puppet-sysinv += " puppet"

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

	# fix the path for systemctl
	sed -i -e 's|${bindir}/systemctl|${base_bindir}/systemctl|' ${D}/${datadir}/puppet/modules/platform/manifests/*.pp

	# fix the path for mount
	sed -i -e 's|${bindir}|${base_bindir}|' ${D}/${datadir}/puppet/modules/platform/manifests/filesystem.pp
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
