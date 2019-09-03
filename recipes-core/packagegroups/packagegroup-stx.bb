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
	packagegroup-stx-integ-k8s \
	packagegroup-stx-integ-ceph \
	packagegroup-stx-integ-tools \
	packagegroup-stx-integ-filesystem \
	packagegroup-stx-integ-logging \
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
	python-cephclient \
	packagegroup-stx-integ-ceph \
	packagegroup-stx-integ-tools \
	packagegroup-stx-integ-filesystem \
	packagegroup-stx-integ-logging \
	"

RDEPENDS_packagegroup-stx-config = " \
	config-gate-worker \
	controllerconfig \
	puppet-manifests \
	puppet-mtce \
	puppet-dcdbsync \
	puppet-patching \
	puppet-sshd \
	puppet-dcmanager \
	puppet-fm \
	puppet-nfv \
	puppet-smapi \
	puppet-sysinv \
	puppet-dcorch \
	storageconfig \
	worker-utils \
	workerconfig-standalone \
	workerconfig-subfunction \
	pm-qos-mgr \
	sysinv \
	cgts-client \
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
	packagegroup-stx-integ-tools \
	ldapscripts \
	"
#	packagegroup-stx-integ-ldap 

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
	resource-agents \
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
	pam-config  \
	shadow-utils-config \
	sudo-config \
	"

# TODO: resolve conflicts use bbappends instead
#	sudo-config \
#	shadow-utils-config 
#	pam-config 

RDEPENDS_packagegroup-stx-integ-k8s = " \
	etcd \
	registry-token-server \
        "


RDEPENDS_packagegroup-stx-integ-puppet-modules = " \
	puppet \
	puppet-create-resources \
	puppet-dnsmasq \
	puppet-lvm \
	puppet-boolean \
	puppet-ldap \
	puppet-drbd \
	puppet-postgresql \
	puppet-network \
	puppet-filemapper \
	puppet-puppi \
	puppet-nslcd \
	"
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
	cgcs-patch-agent \
	cgcs-patch-controller \
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

RDEPENDS_packagegroup-stx-integ-ceph = " \
	ceph \
	ceph-manager \
	ceph-python \
	python-cephclient \
	"

RDEPENDS_packagegroup-stx-integ-tools = " \
	collector \
	"

RDEPENDS_packagegroup-stx-integ-filesystem = " \
	filesystem-scripts \
	iscsi-initiator-utils-config \
	nfscheck \
	nfs-utils-config \
	"
#	TODO: Fix nfs-utils-config Conflicts. 


RDEPENDS_packagegroup-stx-integ-logging = " \
	logrotate-config \
	logmgmt \
	"
