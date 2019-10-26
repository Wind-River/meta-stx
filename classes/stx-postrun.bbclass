
ROOTFS_POSTPROCESS_COMMAND_append = " stx_postprocess_rootfs;"
ETHDEV = "enp0s4"

stx_postprocess_rootfs() {
	chmod -R go+r ${IMAGE_ROOTFS}/usr/lib/python2.7/site-packages/httpretty-0.9.5-py2.7.egg-info
	sed -i -e 's/^\(ExecStartPre.*gencert.sh\)/#\1/g' ${IMAGE_ROOTFS}/lib/systemd/system/haproxy.service
	mkdir -p ${IMAGE_ROOTFS}/usr/share/haproxy
	rm -f ${IMAGE_ROOTFS}/etc/systemd/system/haproxy.service

	sed -i -e 's/^physical_.*:eth0/physical_interface_mappings = provider:${ETHDEV}/g' \
		${IMAGE_ROOTFS}/etc/neutron/plugins/ml2/linuxbridge_agent.ini
	sed -i -e '/^l .*resolv.conf$/d' ${IMAGE_ROOTFS}/etc/default/volatiles/00_core
	sed -i -e '/^f .*resolv.conf none$/d' ${IMAGE_ROOTFS}/etc/default/volatiles/00_core

	for srv in $(echo apache2 kubelet cinder-init glance-api glance-init glance-registry keystone-init \
			neutron-init nova-compute nova-consoleauth nova-console nova-init nova-network \
			nova-xvpvncproxy nova-spicehtml5proxy openvswitch rabbitmq-server \
			registry-token-server)
	do
		rm -f ${IMAGE_ROOTFS}/etc/systemd/system/multi-user.target.wants/$srv.service
		ln -s /dev/null ${IMAGE_ROOTFS}/etc/systemd/system/multi-user.target.wants/$srv.service
	done
	chown etcd:etcd ${IMAGE_ROOTFS}/var/lib/etcd

	echo 'sysadmin ALL=(ALL) NOPASSWD:ALL' >> ${IMAGE_ROOTFS}/etc/sudoers

	CPWD=$(pwd)
	cd ${IMAGE_ROOTFS}/etc/rc.d/init.d
	for srv in $(echo hbsAgent runservices mtclog mtcalarm mtcClient \
			pmon hwmon hostw lmon guestAgent guestServer fm-api \
			)
	do
		rm -f $srv
		ln -s /etc/init.d/$srv .
		sed -i -e 's:^DAEMON="/usr/local/bin:DAEMON="/usr/bin:g' ../../init.d/$srv
	done
	cd $CPWD

	cat > ${IMAGE_ROOTFS}/etc/build.info << \EOF
OS="poky"
SW_VERSION="19.01"
BUILD_TARGET="Host Installer"
BUILD_TYPE="Formal"
BUILD_ID="r/stx.2.0"

JOB="STX_BUILD_2.0"
BUILD_BY="starlingx.build@cengn.ca"
BUILD_NUMBER="40"
BUILD_HOST="starlingx_mirror"
BUILD_DATE="2019-08-26 23:30:00 +0000"
EOF

	mkdir -p ${IMAGE_ROOTFS}/etc/platform/
	cat > ${IMAGE_ROOTFS}/etc/platform/platform.conf << \EOF
nodetype=controllcentoser
subfunction=controller,worker,lowlatency
system_type=All-in-one
security_profile=standard
management_interface=lo
http_port=8080
INSTALL_UUID=d0bb64fe-fb55-4996-8382-ce5fd4aa251c
UUID=e4939a75-c84f-48b5-ad49-af406859d352
sdn_enabled=no
region_config=no
system_mode=simplex
sw_version=19.08
security_feature="nopti nospectre_v2"
vswitch_type=none
region_config=False
cluster_host_interface=lo
oam_interface=enp0s4
EOF

	cat > ${IMAGE_ROOTFS}/etc/keystone/password-rules.conf << \EOF

[security_compliance]


unique_last_password_count = 2


password_regex = ^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()<>{}+=_\\\[\]\-?|~`,.;:]).{7,}$


password_regex_description = Password must have a minimum length of 7 characters, and must contain at least 1 upper case, 1 lower case, 1 digit, and 1 special character'
EOF

	cat > ${IMAGE_ROOTFS}/home/root/.vimrc << \EOF
set background=dark
set noundofile
set nobackup
map b : buffer
map `r : register
EOF

}
