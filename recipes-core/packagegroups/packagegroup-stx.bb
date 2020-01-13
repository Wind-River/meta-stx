SUMMARY = "StarlingX stx packages"

PR = "r0"

#
# packages which content depend on MACHINE_FEATURES need to be MACHINE_ARCH
#

PACKAGE_ARCH = "${MACHINE_ARCH}"
inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = " \
	packagegroup-stx-kube \
	packagegroup-stx-misc \
	packagegroup-stx-ceph \
	packagegroup-stx-config \
	packagegroup-stx-fault \
	packagegroup-stx-ha \
	packagegroup-stx-integ \
	packagegroup-stx-integ-base \
	packagegroup-stx-integ-cff \
	packagegroup-stx-puppet \
	packagegroup-stx-metal \
	packagegroup-stx-nfv \
	packagegroup-stx-update \
	packagegroup-stx-upstream \
	packagegroup-stx-integ-k8s \
	packagegroup-stx-integ-ceph \
	packagegroup-stx-integ-tools \
	packagegroup-stx-integ-filesystem \
	packagegroup-stx-integ-logging \
	packagegroup-stx-openldap \
	packagegroup-stx \
	"

RDEPENDS_packagegroup-stx = " \
        packagegroup-stx-kube \
	packagegroup-stx-misc \
	packagegroup-stx-ceph \
        packagegroup-stx-config \
        packagegroup-stx-fault \
        packagegroup-stx-ha \
        packagegroup-stx-integ \
        packagegroup-stx-metal \
        packagegroup-stx-nfv \
        packagegroup-stx-update \
        packagegroup-stx-upstream \
	packagegroup-stx-puppet \
        starlingx-dashboard \
        python-cephclient \
        packagegroup-stx-integ-ceph \
        packagegroup-stx-integ-tools \
        packagegroup-stx-integ-filesystem \
        packagegroup-stx-integ-logging \
	packagegroup-stx-openldap \
	packagegroup-stx-python \
        "

RDEPENDS_packagegroup-stx-openldap = " \
	openldap \
	openldap-backend-dnssrv \
	openldap-backend-ldap \
	openldap-backend-meta \
	openldap-backend-monitor \
	openldap-backend-null \
	openldap-backend-passwd \
	openldap-backend-shell \
	openldap-bin \
	openldap-slapd \
	openldap-config \
	openldap-dev \
	"

RDEPENDS_packagegroup-stx-kube = "\
	kubernetes \
	kubernetes-misc \
	kubeadm \
	kubelet \
	kubectl \
	util-linux-unshare \
	containerd-opencontainers \
	runc-docker \
	docker-ce \
	packagegroup-stx-misc \
	"
	
RDEPENDS_packagegroup-stx-misc = "\
	vim \
	vim-common \
	ntp \
	python3-pip \
	python-keystone \
	python-keystoneauth1 \
	python-keystoneclient \
	python-ansible \
	python-pynacl \
	python-psycopg2 \
	python-voluptuous \
	python-daemon \
	playbookconfig \
	tzdata \
	go-dep \
	mariadb \
	auditd \
	spice-html5 \
	postgresql-setup \
	bind-utils \
	nscd \
	dnsmasq \
	platform-util \
	namespace-utils \
	drbd-utils \
	grubby \
	grub \
	grub-efi \
	hiera \
	gssproxy \
	polkit \
	e2fsprogs-resize2fs \
	libmysqld libmysqlclient libmysqlclient-r libmysqlclient-dev libmysqlclient-r-dev \
	nss-pam-ldapd \
	xfsprogs \
	xfsprogs-fsck \
	xfsprogs-mkfs \
	xfsprogs-repair \
	libhandle \
	"

RDEPENDS_packagegroup-stx-puppet = "\
	puppet \
	puppetlabs-concat \
	puppet-openstacklib \
	puppetlabs-haproxy \
	puppet-vlan \
	puppetlabs-firewall \
	puppet-barbican \
	puppet-nssdb \
	puppetlabs-apache \
	puppetlabs-rabbitmq \
	puppet-keystone \
	puppet-ceph \
	puppet-etcd \
	puppet-kmod \
	puppetlabs-inifile \
	puppet-staging \
	puppet-oslo \
	puppet-sysctl \
	puppet-memcached \
	puppet-horizon \
	puppet-certmonger \
	puppetlabs-stdlib \
	puppetlabs-mysql \
	puppet-collectd \
	puppet-vswitch \
	puppetlabs-create-resources \
	puppet-dnsmasq \
	puppetlabs-lvm \
	puppet-boolean \
	puppet-ldap \
	puppet-drbd \
	puppetlabs-postgresql \
	puppet-network \
	puppet-filemapper \
	puppet-puppi \
	puppet-nslcd \
	"

RDEPENDS_packagegroup-stx-ceph = "\
	ceph \
	ceph-python \
	openldap \
	rocksdb \
	snappy \
	lz4 \
	oath \
	python-prettytable \
	python-eventlet \
	util-linux-uuidgen \
	rdma-core \
	python3-pyopenssl \
	python3-bcrypt \
	python3-werkzeug \
	python3-pyroute2 \
	python3-requests \
	python3-cherrypy \
	python3-six \
	python3-mako \
	python3-pecan \
	python3-prettytable \
	python3-pycparser \
	python3-cffi \
	python3-cryptography \
	python3-more-itertools \
	python3-pytz \
	python3-jaraco-functools \
	python3-tempora \
	python3-portend \
	python3-zc-lockfile \
	python3-netaddr \
	python3-rpm \
	python2-rpm \
	python-oslo.messaging \
	python-keyring \
	python-cephclient \
	python-pyudev \
	python-ldap \
	python-configparser \
	python-httpretty \
	"
#	ceph-manager \
#	python3-keyring \
#	sysinv \
# 	controllerconfig \
# 	worker-utils 

# stx-upstream /**** Port needed */
# python-keyrings.alt
##############
# FIXME:
# /usr/local/bin/puppet-manifests.sh to /usr/bin/puppet-manifests.sh in
# 	stx-ansible/playbooks/bootstrap/roles/apply-bootstrap-manifest/tasks/main.yml
# 
# Add user sysinv
# fm-api fm-common fm-rest-api
# python-fmclient
# cgcs-patch cgcs-patch-agent cgcs-patch-controller cgcs-users
# python-platform-utils from stx-integ
# python-ldap cyrus-sasl
# python-rpm
# sql_connection failed
# FIXME: ./site-packages/ansible/modules/cloud/docker/docker_swarm_service.py refers to ubuntu
# 	"engine.labels.operatingsystem == ubuntu 14.04"

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
	stx-platform-helm \
	helm \
	openstack-helm-infra \
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
	openstack-barbican-api \
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

#RDEPENDS_packagegroup-stx-integ-ceph = " \
#	ceph \
#	ceph-manager \
#	ceph-python \
#	python-cephclient \
#	"

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
