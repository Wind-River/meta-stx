SUMMARY = "StarlingX/cgsc-root/stx/stx-integ/config-files"
PR = "r0"

#
# packages which content depend on MACHINE_FEATURES need to be MACHINE_ARCH
#
PACKAGE_ARCH = "${MACHINE_ARCH}"


inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = " \
	packagegroup-stx-integ-cff \
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
