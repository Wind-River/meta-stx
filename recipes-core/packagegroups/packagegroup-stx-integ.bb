SUMMARY = "StarlingX/cgsc-root/stx/stx-integ/config-files"
PR = "r0"

#
# packages which content depend on MACHINE_FEATURES need to be MACHINE_ARCH
#
PACKAGE_ARCH = "${MACHINE_ARCH}"


inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = " \
	packagegroup-stx-integ \
	packagegroup-stx-integ-base \
	packagegroup-stx-integ-cff \
	packagegroup-stx-integ-puppet-modules \
	"

RDEPENDS_packagegroup-stx-integ = " \
	packagegroup-stx-integ-base \
	packagegroup-stx-integ-cff \
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
	pam-config \
	rsync-config \
	shadow-utils-config \
	sudo-config \
	syslog-ng-config \
	util-linux-config \
	"

RDEPENDS_packagegroup-stx-integ-puppet-modules = " \
	puppet \
	puppet-boolean \
	puppet-create-resources \
	puppet-dnsmasq \
	puppet-drbd \
	"
