SUMMARY = "StarlingX stx packages"

PR = "r0"

#
# packages which content depend on MACHINE_FEATURES need to be MACHINE_ARCH
#

PACKAGE_ARCH = "${MACHINE_ARCH}"


inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = " \
	packagegroup-stx \
	packagegroup-stx-config \
	packagegroup-stx-fault \
	packagegroup-stx-ha \
	packagegroup-stx-integ \
	packagegroup-stx-integ-base \
	packagegroup-stx-integ-cff \
	packagegroup-stx-integ-puppet-modules \
	packagegroup-stx-metal \
	packagegroup-stx-nfv \
	packagegroup-stx-update \
	packagegroup-stx-upstream \
	"

RDEPENDS_packagegroup-stx = " \
	packagegroup-stx-config \
	packagegroup-stx-fault \
	packagegroup-stx-ha \
	packagegroup-stx-integ \
	packagegroup-stx-metal \
	packagegroup-stx-nfv \
	packagegroup-stx-update \
	packagegroup-stx-upstream \
	starlingx-dashboard \
	"

RDEPENDS_packagegroup-stx-config = " \
	config-gate \
	controllerconfig \
	puppet-manifests \
	puppet-modules-wrs \
	storageconfig \
	worker-utils \
	workerconfig-standalone \
	workerconfig-subfunction \
	pm-qos-mgr \
	sysinv \
	"

RDEPENDS_packagegroup-stx-fault = " \
	fm-api \
	fm-common \
	fm-doc \
	fm-mgr \
	fm-rest-api \
	python-fmclient \
	snmp-audittrail \
	snmp-ext \
	"

RDEPENDS_packagegroup-stx-ha = " \
	sm-api \
	sm \
	sm-client \
	libsm-common \
	sm-common-libs \
	sm-db \
	sm-tools \
	sm-eru \
	"


RDEPENDS_packagegroup-stx-integ = " \
	packagegroup-stx-integ-base \
	packagegroup-stx-integ-cff \
	packagegroup-stx-integ-k8s \
	packagegroup-stx-integ-puppet-modules \
	"

RDEPENDS_packagegroup-stx-integ-base = " \
	cgcs-users \
	dhcp-config \
	dnsmasq-config \
	haproxy-config \
	initscripts-config \
	net-snmp-config \
	openssh-config \
	setup-config \
	systemd-config \
	lighttpd-config \
	"

RDEPENDS_packagegroup-stx-integ-cff = " \
	audit-config \
	docker-config \
	io-scheduler \
	iptables-config \
	memcached-custom \
	ntp-config \
	rsync-config \
	syslog-ng-config \
	util-linux-config \
	"
RDEPENDS_packagegroup-stx-integ-k8s = " \
        registry-token-server \
        "
# To resolve conflicts use bbappends instead
#	sudo-config \
#	shadow-utils-config 
#	pam-config 

RDEPENDS_packagegroup-stx-integ-puppet-modules = " \
	"
#	puppet-boolean \
#	puppet-create-resources \
#	puppet-dnsmasq \
#	puppet-drbd \
#	"
#	puppet 


# The mtce-common contains dev components only and since
# the dev components endup in stx-metal-dev package, not 
# adding mtce-common to packagegroup.

RDEPENDS_packagegroup-stx-metal = " \
	inventory \
	mtce \
	mtce-pmon \
	mtce-hwmon \
	mtce-hostw \
	mtce-lmon \
	mtce-compute \
	mtce-control \
	mtce-storage \
	python-inventoryclient \
	"

RDEPENDS_packagegroup-stx-nfv = " \
	nfv-common \
	nfv-plugins \
	nfv-tools \
	nfv-vim \
	nfv-client \
	mtce-guestagent \
	mtce-guestserver \
	nova-api-proxy \
	"

RDEPENDS_packagegroup-stx-update = " \
	cgcs-patch \
	enable-dev-patch \
	patch-alarm \
	tsconfig \
	requests-toolbelt \
	"

# TODO:
# Port required/missing packages :
#	openstack-helm-infra.bb
#	openstack-helm.bb 
#	python-horizon.bbappend

RDEPENDS_packagegroup-stx-upstream = " \
	python-barbicanclient \
	python-cinderclient \
	python-glanceclient \
	python-aodhclient \
	python-heatclient \
	python-horizon \
	python-ironicclient \
	python-keystoneauth1 \
	python-keystoneclient \
	python-magnumclient \
	python-muranoclient \
	python-neutronclient \
	python-novaclient \
	python-openstackclient \
	python-openstacksdk \
	stx-ocf-scripts \
	rabbitmq-server-config \
	openstack-ras \
	python-gnocchiclient \
	python-pankoclient \
	"
