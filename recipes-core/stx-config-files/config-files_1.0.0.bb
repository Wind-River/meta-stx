DESCRIPTION = "stx-config-files"

PROTOCOL = "https"
BRANCH = "r/stx.3.0"
SRCREV = "d778e862571957ece3c404c0c37d325769772fde"
SRCNAME = "config-files"
S = "${WORKDIR}/git"
PV = "1.0.0"


# TODO:
#3b83ef96387f14655fc854ddc3c6bd57  ./audit-config/files/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./docker-config/files/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./filesystem-scripts/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./filesystem-scripts/filesystem-scripts-1.0/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./io-scheduler/centos/files/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./iptables-config/files/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./lighttpd-config/files/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./logrotate-config/files/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./mlx4-config/files/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./ntp-config/files/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./openldap-config/files/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./openvswitch-config/files/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./shadow-utils-config/files/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./sudo-config/files/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./syslog-ng-config/files/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./systemd-config/files/LICENSE

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://systemd-config/files/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"


SRC_URI = "git://opendev.org/starlingx/${SRCNAME}.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"



do_configure () {
	:
}


do_compile () {
	:
}


do_install () {
	install -m 0755 -d ${D}/${datadir}/starlingx/config-files
	# for f in $(find ./ -not -path "./docker-config/*" -name '*\.spec' | cut -d '/' -f2);
	for f in $(find ./ -name '*\.spec' | cut -d '/' -f2);
	do 
		tar -c $f -f - | tar -C ${D}/${datadir}/starlingx/config-files -xf -;
	done
	find ${D}/${datadir}/starlingx/config-files -name centos -exec rm -rf {} +
	rm -rf ${D}/${datadir}/starlingx/config-files/centos-release-config 
	chown -R root:root ${D}/${datadir}/starlingx/config-files/
}

PACKAGES ?= ""
PACKAGES += "audit-config"
PACKAGES += "dhclient-config"
PACKAGES += "dnsmasq-config"
PACKAGES += "docker-config"
PACKAGES += "initscripts-config"
PACKAGES += "filesystem-scripts"
PACKAGES += "haproxy-config"
PACKAGES += "ioscheduler-config"
PACKAGES += "iptables-config"
PACKAGES += "iscsi-initiator-utils-config"
PACKAGES += "lighttpd-config"
PACKAGES += "logrotate-config"
PACKAGES += "memcached-custom"
PACKAGES += "mlx4-config"
PACKAGES += "net-snmp-config"
PACKAGES += "nfs-utils-config"
PACKAGES += "ntp-config"
PACKAGES += "openldap-config"
PACKAGES += "openssh-config"
PACKAGES += "openvswitch-config"
PACKAGES += "pam-config"
PACKAGES += "rabbitmq-server-config"
PACKAGES += "rsync-config"
PACKAGES += "setup-config"
PACKAGES += "shadow-utils-config"
PACKAGES += "sudo-config"
PACKAGES += "syslog-ng-config"
PACKAGES += "systemd-config"
PACKAGES += "util-linux-config"


FILES_audit-config = "${datadir}/starlingx/config-files/audit-config/"
FILES_dhclient-config = "${datadir}/starlingx/config-files/dhcp-config/"
FILES_dnsmasq-config = "${datadir}/starlingx/config-files/dnsmasq-config/"
FILES_docker-config = "${datadir}/starlingx/config-files/docker-config/"
FILES_initscripts-config = "${datadir}/starlingx/config-files/initscripts-config/"
FILES_filesystem-scripts= "${datadir}/starlingx/config-files/filesystem-scripts/"
FILES_haproxy-config= "${datadir}/starlingx/config-files/haproxy-config/"
FILES_ioscheduler-config= "${datadir}/starlingx/config-files/io-scheduler/"
FILES_iptables-config= "${datadir}/starlingx/config-files/iptables-config/"
FILES_iscsi-initiator-utils-config = "${datadir}/starlingx/config-files/iscsi-initiator-utils-config/"
FILES_lighttpd-config= "${datadir}/starlingx/config-files/lighttpd-config/"
FILES_logrotate-config= "${datadir}/starlingx/config-files/logrotate-config/"
FILES_memcached-custom = "${datadir}/starlingx/config-files/memcached-custom/"
FILES_mlx4-config= "${datadir}/starlingx/config-files/mlx4-config/"
FILES_net-snmp-config= "${datadir}/starlingx/config-files/net-snmp-config/"
FILES_nfs-utils-config= "${datadir}/starlingx/config-files/nfs-utils-config/"
FILES_ntp-config= "${datadir}/starlingx/config-files/ntp-config/"
FILES_openldap-config= "${datadir}/starlingx/config-files/openldap-config/"
FILES_openssh-config= "${datadir}/starlingx/config-files/openssh-config/"
FILES_openvswitch-config= "${datadir}/starlingx/config-files/openvswitch-config/"
FILES_pam-config= "${datadir}/starlingx/config-files/pam-config/"
FILES_rabbitmq-server-config= "${datadir}/starlingx/config-files/rabbitmq-server-config/"
FILES_rsync-config= "${datadir}/starlingx/config-files/rsync-config/"
FILES_setup-config= "${datadir}/starlingx/config-files/setup-config/"
FILES_shadow-utils-config= "${datadir}/starlingx/config-files/shadow-utils-config/"
FILES_sudo-config= "${datadir}/starlingx/config-files/sudo-config/"
FILES_syslog-ng-config= "${datadir}/starlingx/config-files/syslog-ng-config/"
FILES_systemd-config= "${datadir}/starlingx/config-files/systemd-config/"
FILES_util-linux-config= "${datadir}/starlingx/config-files/util-linux-config/"

RDEPENDS_audit-config += " audit"
RDEPENDS_dhclient-config += "dhcp-client"
RDEPENDS_dnsmasq-config += ""
RDEPENDS_docker-config += "docker-ce logrotate "
RDEPENDS_initscripts-config += "initscripts"
RDEPENDS_filesystem-scripts += ""
RDEPENDS_haproxy-config += "haproxy"
RDEPENDS_ioscheduler-config += ""
RDEPENDS_iptables-config += "iptables"
RDEPENDS_iscsi-initiator-utils-config += " iscsi-initiator-utils"
RDEPENDS_lighttpd-config += " lighttpd"
RDEPENDS_logrotate-config += " logrotate cronie"
RDEPENDS_memcached-custom += ""
RDEPENDS_mlx4-config += ""
RDEPENDS_net-snmp-config += " net-snmp"
RDEPENDS_nfs-utils-config += " nfs-utils"
RDEPENDS_ntp-config += " ntp"
RDEPENDS_openldap-config += " openldap"
RDEPENDS_openssh-config += " openssh"
RDEPENDS_openvswitch-config += " openvswitch"
RDEPENDS_pam-config += " libpam-runtime"
RDEPENDS_rabbitmq-server-config += " rabbitmq-server"
RDEPENDS_rsync-config += " rsync"
RDEPENDS_setup-config += ""
RDEPENDS_shadow-utils-config += " shadow"
RDEPENDS_sudo-config += " sudo"
RDEPENDS_syslog-ng-config += " syslog-ng"
RDEPENDS_systemd-config += " systemd"
RDEPENDS_util-linux-config += " util-linux"

pkg_postinst_ontarget_audit-config() {
	cp -f ${datadir}/starlingx/config-files/audit-config/syslog.conf ${sysconfdir}/audisp/plugins.d/syslog.conf
	chmod 640 ${sysconfdir}/audisp/plugins.d/syslog.conf
}

pkg_postinst_ontarget_dhclient-config() {
	install -m 0755 -p ${datadir}/starlingx/config-files/dhclient-config/dhclient-enter-hooks ${sysconfdir}/dhcp/dhclient-enter-hooks
	install -m 0755 -p ${datadir}/starlingx/config-files/dhclient-config/dhclient.conf ${sysconfdir}/dhcp/dhclient/dhclient.conf
	ln -fs ${sysconfdir}/dhcp/dhclient-enter-hooks ${sysconfdir}/dhclient-enter-hooks
}
	
pkg_postinst_ontarget_dnsmasq-config() {
	install -m 755 ${datadir}/starlingx/config-files/dnsmasq-config/init ${sysconfdir}/init.d/dnsmasq
}

pkg_postinst_ontarget_docker-config() {
	install -D -m 644 ${datadir}/starlingx/config-files/docker-config/docker-pmond.conf ${sysconfdir}/pmon.d/docker.conf
	install -d -m 0755 ${sysconfdir}/systemd/system/docker.service.d
	install -D -m 644 ${datadir}/starlingx/config-files/docker-config/docker-stx-override.conf \
			${sysconfdir}/systemd/system/docker.service.d/docker-stx-override.conf 
	install -D -m 644 ${datadir}/starlingx/config-files/docker-config/docker.logrotate ${sysconfdir}/logrotate.d/docker.logrotate
}

pkg_postinst_ontarget_filesystem-scripts() {
	install -D -m 755 ${datadir}/starlingx/config-files/filesystem-scripts/uexportfs ${sysconfdir}/init.d/uexportfs

	install -d -m 0755 /usr/lib/ocf/resource.d/platform/
	install -D -m 755 ${datadir}/starlingx/config-files/filesystem-scripts/nfsserver-mgmt /usr/lib/ocf/resource.d/platform/nfsserver-mgmt

	install -p -D -m 755 ${datadir}/starlingx/config-files/filesystem-scripts/nfsmount ${bindir}/nfs-mount
	install -D -m 755 ${datadir}/starlingx/config-files/filesystem-scripts/uexportfs.service ${systemd_system_unitdir}/uexportfs

	systemctl enable uexportfs.service
}


pkg_postinst_ontarget_haproxy-config() {

	install -d -m 755 ${sysconfdir}/haproxy/errors/
	install -m 755 ${datadir}/starlingx/config-files/haproxy-config/503.http ${sysconfdir}/haproxy/errors/503.http

	install -m 644 ${datadir}/starlingx/config-files/haproxy-config/haproxy.service ${sysconfdir}/systemd/system/
	install -p -D -m 0755 ${datadir}/starlingx/config-files/haproxy-config/haproxy.sh ${sysconfdir}/init.d/haproxy

	/bin/systemctl disable haproxy.service
	if test -s ${sysconfdir}/logrotate.d/haproxy ; then
	    echo '#See /etc/logrotate.d/syslog for haproxy rules' > ${sysconfdir}/logrotate.d/haproxy
	fi
}

pkg_postinst_ontarget_initscripts-config() {
	install -l  -m 755 ${sysconfdir}/sysconfig
	install -d  -m 755 ${sysconfdir}/initd.d
	install -d  -m 755 ${systemd_system_unitdir}

	install -m  644 ${datadir}/starlingx/config-files/initscripts-config/sysctl.conf ${datadir}/starlingx/stx.sysctl.conf
	install -m  644 ${datadir}/starlingx/config-files/initscripts-config/sysconfig-network.conf ${sysconfdir}/sysconfig/network
	install -m  755 ${datadir}/starlingx/config-files/initscripts-config/mountnfs.sh ${sysconfdir}/initd.d/mountnfs
	install -m  644 ${datadir}/starlingx/config-files/initscripts-config/mountnfs.service ${systemd_system_unitdir}/mountnfs.service


	cmp -s ${datadir}/starlingx/stx.sysctl.conf ${sysconfdir}/sysctl.conf
	if [ $? -ne 0 ] ; then
	        # Initial installation
		cp -f ${_datadir}/starlingx/stx.sysctl.conf ${sysconfdir}/sysctl.conf
		chmod 644 ${sysconfdir}/sysctl.conf
	fi
}

pkg_postinst_ontarget_iscsi-initiator-utils-config() {
#	%description
#	package StarlingX configuration files of iscsi-initiator-utils to system folder.

#	install -d  ${libdir}/tmpfiles.d
#	install -d  ${sysconfdir}/systemd/system
#	install -d  ${datadir}/starlingx

	SRCPATH=${datadir}/starlingx/config-files/iscsi-initiator-utils-config
	tmpfilesdir=${libdir}/tmpfiles.d

	install -m 0644 ${SRCPATH}/iscsi-cache.volatiles   ${tmpfilesdir}/iscsi-cache.conf
	install -m 0644 ${SRCPATH}/iscsi-shutdown.service  ${sysconfdir}/systemd/system
	install -m 0644 ${SRCPATH}/iscsid.conf             ${datadir}/starlingx/stx.iscsid.conf

	cmp -s ${datadir}/starlingx/stx.iscsid.conf ${sysconfdir}/iscsi/iscsid.conf
	if [ $? -ne 0 ] ; then
		# Initial installation
		cp -f ${datadir}/starlingx/stx.iscsid.conf ${sysconfdir}/iscsi/iscsid.conf
		chmod 0750 %{_sysconfdir}/iscsi
		chmod 0640 %{_sysconfdir}/iscsi/iscsid.conf
	fi
	
	/bin/systemctl disable iscsi-shutdown.service
}

pkg_postinst_ontarget_lighttpd-config() {
#	%description
#	StarlingX lighttpd configuration file

	CONFDIR=${sysconfdir}/lighttpd
	ROOTDIR=/www
	SRCPATH=${datadir}/starlingx/config-files/lighttpd-config

	install -d -m 1777 ${ROOTDIR}/tmp
	install -d ${CONFDIR}/ssl
	install -d ${ROOTDIR}/pages/dav
        install -m640 ${SRCPATH}/lighttpd.conf		${datadir}/starlingx/lighttpd.conf
	install -m755 ${SRCPATH}/lighttpd.init		${datadir}/starlingx/lighttpd.init
	install -m644 ${SRCPATH}/lighttpd-inc.conf	${CONFDIR}/lighttpd-inc.conf
	install -m644 ${SRCPATH}/index.html.lighttpd	${ROOTDIR}/pages/index.html

	install -d ${sysconfdir}/logrotate.d
	install -m644 ${SRCPATH}lighttpd.logrotate	${datadir}/starlingx/lighttpd.logrotate
	chmod 02770 ${sysconfdir}/lighttpd

	cmp -s ${datadir}/starlingx/lighttpd.conf  ${sysconfdir}/lighttpd/lighttpd.conf
	if [ $? -ne 0 ] ; then
	    cp --preserve=xattr -f ${datadir}/starlingx/lighttpd.conf  ${sysconfdir}/lighttpd/lighttpd.conf
	    chmod 640 ${sysconfdir}/lighttpd/lighttpd.conf
	    cp --preserve=xattr -f ${datadir}/starlingx/lighttpd.logrotate ${sysconfdir}/logrotate.d/lighttpd
	    chmod 644 ${sysconfdir}/logrotate.d/lighttpd
	fi

	# /etc/rc.d/init.d/lighttpd is not a config file, so replace it here if it doesn't match
	cmp --quiet ${datadir}/starlingx/lighttpd.init ${sysconfdir}/rc.d/init.d/lighttpd
	if [ $? -ne 0 ]; then
		cp --preserve=xattr -f ${datadir}/starlingx/lighttpd.init ${sysconfdir}/rc.d/init.d/lighttpd
		chmod 755 ${sysconfdir}/rc.d/init.d/lighttpd
	fi
}

pkg_postinst_ontarget_logrotate-config() {
#	%description
#	StarlingX logrotate configuration file

	SRCPATH=${datadir}/starlingx/config-files/logrotate-config

	install -m 644 ${SRCPATH}/logrotate-cron.d ${sysconfdir}/cron.d/logrotate
	install -m 644 ${SRCPATH}/logrotate.conf ${datadir}/starlingx/logrotate.conf

	cmp -s ${datadir}/starlingx/logrotate.conf ${sysconfdir}/logrotate.conf 
	if [ $? -ne 0 ] ; then
	    cp -f ${datadir}/starlingx/logrotate.conf ${sysconfdir}/logrotate.conf 
	    chmod 644 ${sysconfdir}/logrotate.conf
	    mv ${sysconfdir}/cron.daily/logrotate ${sysconfdir}/logrotate.cron
	    chmod 700 %{_sysconfdir}/logrotate.cron
	 fi
}


pkg_postinst_ontarget_memcached-custom() {
#	Summary: package memcached service files to system folder.

	SRCPATH=${datadir}/starlingx/config-files/memcached-custom/
	install -m 644 -p ${SRCPATH}/memcached.service ${sysconfdir}/systemd/system/memcached.service
}


pkg_postinst_ontarget_mlx4-config() {
	Source1: mlx4-configure.sh
	Source2: mlx4-config.service
	Source3: LICENSE
	Source4: mlx4_core_goenabled.sh
	Source5: mlx4_core_config.sh

#	%description
#	Wind River Mellanox port-type configuration scripts
	SRCPATH=${datadir}/starlingx/config-files/mlx4-config/

#	/bin/systemctl disable mlx4-config.service >/dev/null 2>&1

	install -m 755 ${SRCPATH}/mlx4-configure.sh	${_sysconfdir}/init.d/
	install -m 644 ${SRCPATH}/mlx4-config.service	${systemd_system_unitdir}/
	install -m 555 ${SRCPATH}/mlx4_core_goenabled.sh ${sysconfdir}/goenabled.d/
	install -m 755 ${SRCPATH}/mlx4_core_config.sh	${bindir}/

	/bin/systemctl enable mlx4-config.service >/dev/null 2>&1
}


pkg_postinst_ontarget_net-snmp-config() {
#	%description
#	package StarlingX configuration files of net-snmp to system folder.

	SRCPATH=${datadir}/starlingx/config-files/net-snmp-config/

	install -d ${datadir}/snmp

	install -m 644 ${SRCPATH}/stx.snmpd.conf    ${datadir}/starlingx/stx.snmpd.conf
	install -m 755 ${SRCPATH}/stx.snmpd         ${sysconfdir}/rc.d/init.d/snmpd
	install -m 660 ${SRCPATH}/stx.snmp.conf     ${datadir}/snmp/snmp.conf
	install -m 644 ${SRCPATH}/snmpd.service     ${sysconfdir}/systemd/system/snmpd.service
	
	
	cmp -s ${datadir}/starlingx/stx.snmpd.conf   ${sysconfdir}/snmp/snmpd.conf
	if [ $? -ne 0 ] ; then
	# Initial installation
		cp -f ${datadir}/starlingx/stx.snmpd.conf   ${sysconfdir}/snmp/snmpd.conf
		chmod 640 ${sysconfdir}/snmp/snmpd.conf
		chmod 640 ${sysconfdir}/snmp/snmptrapd.conf
	fi
	
	/bin/systemctl disable snmpd.service
}


pkg_postinst_ontarget_nfs-utils-config() {
#	%description
#	package customized configuration and service files of nfs-utils to system folder.


	SRCPATH=${datadir}/starlingx/config-files/nfs-utils-config/
	

	install -m 755 -p -D ${SRCPATH}/nfscommon		${sysconfdir}/init.d
	install -m 644 -p -D ${SRCPATH}/nfscommon.service	${systemd_system_unitdir}/
	install -m 755 -p -D ${SRCPATH}/nfsserver		${sysconfdir}/init.d
	install -m 644 -p -D ${SRCPATH}/nfsserver.service	${systemd_system_unitdir}
	install -m 644 -p -D ${SRCPATH}/nfsmount.conf		${datadir}/starlingx/stx.nfsmount.conf
	
	cmp -s ${datadir}/starlingx/stx.nfsmount.conf ${sysconfdir}/nfsmount.conf
	if [ $? -ne 0 ] ; then
		# Initial installation
		        cp -f ${datadir}/starlingx/stx.nfsmount.conf ${sysconfdir}/nfsmount.conf
			chmod 644 ${sysconfdir}/nfsmount.conf
	fi

	# STX - disable these service files as rpc-statd is started by nfscommon
	/bin/systemctl disable rpc-statd.service
	/bin/systemctl disable rpc-statd-notify.service
	/bin/systemctl disable nfs-lock.service
	/bin/systemctl disable nfslock.service 

	/bin/systemctl enable nfscommon.service  >/dev/null 2>&1 || :
	/bin/systemctl enable nfsserver.service  >/dev/null 2>&1 || :

	# For now skiping the preun rule
	#/bin/systemctl disable nfscommon.service >/dev/null 2>&1 || :
	#/bin/systemctl disable nfsserver.service >/dev/null 2>&1 || :

}

pkg_postinst_ontarget_ntp-config() {
#	%description
#	StarlingX ntp configuration file

	SRCPATH=${datadir}/starlingx/config-files/nfs-utils-config/
	install -D -m644 ${SRCPATH}/ntpd.sysconfig ${datadir}/starlingx/ntpd.sysconfig
	install -D -m644 ${SRCPATH}/ntp.conf ${datadir}/starlingx/ntp.conf

	cmp -s ${datadir}/starlingx/ntpd.sysconfig ${sysconfdir}/sysconfig/ntpd
	if [ $? -ne 0 ] ; then
		cp -f ${datadir}/starlingx/ntpd.sysconfig ${sysconfdir}/sysconfig/ntpd
		cp -f ${datadir}/starlingx/ntp.conf ${sysconfdir}/ntp.conf
		chmod 644 ${sysconfdir}/sysconfig/ntpd
		chmod 644 %{_sysconfdir}/ntp.conf
	fi
}


pkg_postinst_ontarget_openldap-config() {
#	%description
#	StarlingX openldap configuration file

	SRCPATH=${datadir}/starlingx/config-files/openldap-config/

	install -m 755 ${SRCPATH}/initscript ${sysconfdir}/rc.d/init.d/openldap
	install -m 600 ${SRCPATH}/slapd.conf ${sysconfdir}/openldap/slapd.conf

	install -m 600 ${SRCPATH}/initial_config.ldif ${sysconfdir}/openldap/initial_config.ldif

	install -m 644 ${SRCPATH}/slapd.service ${sysconfdir}/systemd/system/slapd.service
	install -m 644 ${SRCPATH}/slapd.sysconfig ${datadir}/starlingx/slapd.sysconfig 
	
	cmp -s ${datadir}/starlingx/slapd.sysconfig ${sysconfdir}/sysconfig/slapd
	if [ $? -ne 0] ; then
		cp -f ${datadir}/starlingx/slapd.sysconfig ${sysconfdir}/sysconfig/slapd
		chmod 644 ${systemd_system_unitdir}/slapd
	fi
}

pkg_postinst_ontarget_openssh-config() {
#	%description
#	package StarlingX configuration files of openssh to system folder.


	SRCPATH=${datadir}/starlingx/config-files/openssh-config/

	install -m 644 ${SRCPATH}/sshd.service  ${sysconfdir}/systemd/system/sshd.service
	install -m 644 ${SRCPATH}/ssh_config    ${datadir}/starlingx/ssh_config
	install -m 600 ${SRCPATH}/sshd_config   ${datadir}/starlingx/sshd_config
	
	cmp -s ${datadir}/starlingx/ssh_config  ${sysconfdir}/ssh/ssh_config
	if [ $? -ne 0 ] ; then
		# Initial installation
		cp -f ${datadir}/starlingx/ssh_config  ${sysconfdir}/ssh/ssh_config
		cp -f ${datadir}/starlingx/sshd_config ${sysconfdir}/ssh/sshd_config
	fi
}

pkg_postinst_ontarget_openvswitch-config() {
#	%description
#	StarlingX openvswitch configuration file

	SRCPATH=${datadir}/starlingx/config-files/openvswitch-config/

	install -m 0644 ${SRCPATH}/ovsdb-server.pmon.conf ${sysconfdir}/openvswitch/ovsdb-server.pmon.conf
	install -m 0644 ${SRCPATH}/ovs-vswitchd.pmon.conf ${sysconfdir}/openvswitch/ovs-vswitchd.pmon.conf
	install -m 0640 etc_logrotate.d_openvswitch ${datadir}/starlingx/etc_logrotate.d_openvswitch 
	
	cmp -s ${datadir}/starlingx/etc_logrotate.d_openvswitch ${sysconfdir}/logrotate.d/openvswitch
	if [ $? -ne 0 ] ; then
		cp -f ${datadir}/starlingx/etc_logrotate.d_openvswitch ${sysconfdir}/logrotate.d/openvswitch
	        chmod 644 ${sysconfdir}/logrotate.d/openvswitch
	fi
}

pkg_postinst_ontarget_pam-config() {
#	%description
#	package StarlingX configuration files of pam to system folder.

	SRCPATH=${datadir}/starlingx/config-files/pam-config/

	install  -m 644 ${SRCPATH}/sshd.pam        ${_datadir}/starlingx/sshd.pam
	install  -m 644 ${SRCPATH}/common-account  ${sysconfdir}/pam.d/common-account
	install  -m 644 ${SRCPATH}/common-auth     ${sysconfdir}/pam.d/common-auth
	install  -m 644 ${SRCPATH}/common-password ${sysconfdir}/pam.d/common-password
	install  -m 644 ${SRCPATH}/common-session  ${sysconfdir}/pam.d/common-session
	install  -m 644 ${SRCPATH}/common-session-noninteractive ${sysconfdir}/pam.d/common-session-noninteractive
	install  -m 644 ${SRCPATH}/system-auth.pamd ${datadir}/starlingx/stx.system-auth
	
	cmp -s ${datadir}/starlingx/stx.system-auth ${sysconfdir}/pam.d/system-auth

	if [ $1 -eq 1 ] ; then
		# Initial installation
		cp -f  ${datadir}/starlingx/stx.system-auth ${sysconfdir}/pam.d/system-auth
		cp -f ${datadir}/starlingx/sshd.pam    ${sysconfdir}/pam.d/sshd
	fi
}

pkg_postinst_ontarget_rabbitmq-server-config() {
#	%description
#	package StarlingX configuration files of rabbitmq-server to system folder.

	SRCPATH=${datadir}/starlingx/config-files/rabbitmq-server-config/

	install -d ${libdir}/ocf/resource.d/rabbitmq
	install -m 0755 ${SRCPATH}/rabbitmq-server.ocf              ${libdir}/ocf/resource.d/rabbitmq/stx.rabbitmq-server
	install -m 0644 ${SRCPATH}/rabbitmq-server.service.example  ${sysconfdir}/systemd/system/rabbitmq-server.service
	install -m 0644 ${SRCPATH}/rabbitmq-server.logrotate        ${datadir}/starlingx/stx.rabbitmq-server.logrotate
	
	
	cmp -s ${datadir}/starlingx/stx.rabbitmq-server.logrotate ${sysconfdir}/logrotate.d/rabbitmq-server
	if [ $? -ne 0 ] ; then
		# Initial installation 
		cp ${datadir}/starlingx/stx.rabbitmq-server.logrotate ${sysconfdir}/logrotate.d/rabbitmq-server
	fi
}

pkg_postinst_ontarget_rsync-config() {
#	%description
#	package StarlingX configuration files of rsync to system folder.

	SRCPATH=${datadir}/starlingx/config-files/rsync-config/

	install -m 644 ${SRCPATH}/rsyncd.conf  ${datadir}/starlingx/stx.rsyncd.conf
	
	cmp -s ${datadir}/starlingx/stx.rsyncd.conf  ${sysconfdir}/rsyncd.conf
	if [ $? -ne 0 ] ; then
		# Initial installation
		cp -f ${datadir}/starlingx/stx.rsyncd.conf  ${sysconfdir}/rsyncd.conf
	fi 
}

pkg_postinst_ontarget_setup-config() {
#	%description
#	package StarlingX configuration files of setup to system folder.

	SRCPATH=${datadir}/starlingx/config-files/setup-config/

	install -m 644 ${SRCPATH}/motd          ${datadir}/starlingx/stx.motd
	install -m 644 ${SRCPATH}/prompt.sh     ${sysconfdir}/profile.d/prompt.sh
	install -m 644 ${SRCPATH}/custom.sh     ${sysconfdir}/profile.d/custom.sh

	cmp -s ${datadir}/starlingx/stx.motd    ${sysconfdir}/motd
	if [ $? -ne 0 ] ; then
		# Initial installation
		cp -f ${datadir}/starlingx/stx.motd    ${sysconfdir}/motd
		chmod 600   ${sysconfdir}/{exports,fstab}
	fi 
}

pkg_postinst_ontarget_shadow-utils-config() {
#	%description
#	StarlingX shadow-utils configuration file

	SRCPATH=${datadir}/starlingx/config-files/shadow-utils-config/

	install -D -m644 ${SRCPATH}/login.defs ${datadir}/starlingx/login.defs
	install -D -m644 ${SRCPATH}/clear_shadow_locks.service  ${systemd_system_unitdir}/clear_shadow_locks.service

	cmp -s ${datadir}/starlingx/login.defs ${sysconfdir}/login.defs
	if [ $? -ne 0 ] ; then
		cp -f ${datadir}/starlingx/login.defs ${sysconfdir}/login.defs
		chmod 644 ${sysconfdir}/login.defs
		/bin/systemctl preset clear_shadow_locks.service
	fi
}

pkg_postinst_ontarget_sudo-config() {
#	%description
#	StarlingX sudo configuration file

	SYSADMIN_P="4SuW8cnXFyxsk"
	SRCPATH=${datadir}/starlingx/config-files/sudo-config/

	install -m 440 ${SRCPATH}/files/sysadmin.sudo  ${sysconfdir}/sudoers.d/sysadmin

	getent group sys_protected >/dev/null || groupadd -f -g 345 sys_protected
	getent passwd sysadmin > /dev/null || \
		useradd -m -g sys_protected -G root  -d /home/sysadmin -p ${SYSADMIN_P} -s /bin/sh sysadmin 2> /dev/null || :
}

pkg_postinst_ontarget_syslog-ng-config() {
#	%description
#	StarlingX syslog-ng configuration file

	SRCPATH=${datadir}/starlingx/config-files/syslog-ng-config/

	install -D -m644 ${SRCPATH}/syslog-ng.conf ${datadir}/starlingx/syslog-ng.conf
	install -D -m644 ${SRCPATH}/syslog-ng.logrotate ${datadir}/starlingx/syslog-ng.logrotate
	install -D -m644 ${SRCPATH}/remotelogging.conf ${sysconfdir}/syslog-ng/remotelogging.conf
	install -D -m700 ${SRCPATH}/fm_event_syslogger ${sbindir}/fm_event_syslogger
	install -D -m644 ${SRCPATH}/syslog-ng.service ${datadir}/starlingx/syslog-ng.service

	cmp -s ${datadir}/starlingx/syslog-ng.conf ${sysconfdir}/syslog-ng/syslog-ng.conf
	if [ $? -ne 0 ] ; then
		cp -f ${datadir}/starlingx/syslog-ng.conf ${sysconfdir}/syslog-ng/syslog-ng.conf
		chmod 644 %{_sysconfdir}/syslog-ng/syslog-ng.conf
		cp -f ${datadir}/starlingx/syslog-ng.logrotate ${sysconfdir}/logrotate.d/syslog
		chmod 644 ${sysconfdir}/logrotate.d/syslog
		cp -f ${datadir}/starlingx/syslog-ng.service ${systemd_system_unitdir}/syslog-ng.service
		chmod 644 ${systemd_system_unitdir}/syslog-ng.service
		/bin/systemctl preset syslog-ng.service
	fi
	/sbin/ldconfig 
# TODO
#preun:
#	%systemd_preun syslog-ng.service 
#postun:
#	ldconfig
#	%systemd_postun_with_restart syslog-ng.service 
#	systemctl daemon-reload 2>&1 || :
#	systemctl try-restart 
}

pkg_postinst_ontarget_systemd-config() {
#	%description
#	StarlingX systemd configuration file

	SRCPATH=${datadir}/starlingx/config-files/systemd-config/

	install -m644 ${SRCPATH}/60-persistent-storage.rules ${sysconfdir}/udev/rules.d/60-persistent-storage.rules
	install -m644 ${SRCPATH}/journald.conf ${datadir}/starlingx/journald.conf
	install -m644 ${SRCPATH}/systemd.conf.tmpfiles.d ${sysconfdir}/tmpfiles.d/systemd.conf
	install -m644 ${SRCPATH}/tmp.conf.tmpfiles.d ${sysconfdir}/tmpfiles.d/tmp.conf
	install -m644 ${SRCPATH}/tmp.mount ${sysconfdir}/systemd/system/tmp.mount

	cmp -s ${datadir}/starlingx/journald.conf ${sysconfdir}/systemd/journald.conf
	if [ $? -ne 0 ] ; then
		cp -f ${datadir}/starlingx/journald.conf ${sysconfdir}/systemd/journald.conf
	        chmod 644 ${sysconfdir}/systemd/journald.conf
	fi
}

pkg_postinst_ontarget_util-linux-config() {
#	%description
#	package StarlingX configuration files of util-linux to system folder.

	SRCPATH=${datadir}/starlingx/config-files/util-linux-config/

	install -m 644 ${SRCPATH}/stx.su     ${datadir}/starlingx/stx.su
	install -m 644 ${SRCPATH}/stx.login  ${datadir}/starlingx/stx.login

	%define _pamconfdir %{_sysconfdir}/pam.d
	cmp -s ${datadir}/starlingx/stx.su ${sysconfdir}/pam.d/su
	if [ $? -ne 0 ] ; then
		cp -f ${datadir}/starlingx/stx.su ${sysconfdir}/pam.d/su
		cp -f ${datadir}/starlingx/stx.login  ${sysconfdir}/pam.d/login
	fi
}

pkg_postinst_ontarget_ioscheduler-config() {
#	%description
#	CGCS io scheduler configuration and tuning.

	SRCPATH=${datadir}/starlingx/config-files/io-scheduler/

	install -m 644 ${SRCPATH}/60-io-scheduler.rules ${sysconfdir}/udev/rules.d/60-io-scheduler.rules

	/bin/udevadm control --reload-rules
	/bin/udevadm trigger --type=devices --subsystem-match=block
}

pkg_postinst_ontarget_iptables-config() {
#	%description
#	StarlingX iptables configuration file

	SRCPATH=${datadir}/starlingx/config-files/iptables-config/
	
	install -m 600 ${SRCPATH}/iptables.rules ${datadir}/starlingx/iptables.rules
	install -m 600 ${SRCPATH}/ip6tables.rules ${datadir}/starlingx/ip6tables.rules 
	
	cmp -s S{datadir}/starlingx/iptables.rules ${sysconfdir}/sysconfig/iptables
	if [ $? -ne 0 ] ; then
		cp -f S{datadir}/starlingx/iptables.rules ${sysconfdir}/sysconfig/iptables
		chmod 600 ${sysconfdir}/sysconfig/iptables
		cp -f ${datadir}/starlingx/ip6tables.rules ${sysconfdir}/sysconfig/ip6tables
		chmod 600 ${sysconfdir}/sysconfig/ip6tables
	fi
	/bin/systemctl enable iptables.service ip6tables.service >/dev/null 2>&1
}