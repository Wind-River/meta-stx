DESCRIPTION = "openstack-helm"

STABLE = "master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "6c71637222f47d85681038994f02feac92f75bd2"
S = "${WORKDIR}/git"
PV = "0.1.0"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

SRC_URI = "git://github.com/openstack/openstack-helm.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH} \
	file://openstack-helm/0001-Add-Aodh-Chart.patch \
	file://openstack-helm/0002-Ceilometer-chart-add-the-ability-to-publish-events-t.patch \
	file://openstack-helm/0003-Remove-stale-Apache2-service-pids-when-a-POD-starts.patch \
	file://openstack-helm/0004-Fix-ssh-config-in-nova-to-support-cold-migrations.patch \
	file://openstack-helm/0005-Nova-console-ip-address-search-optionality.patch \
	file://openstack-helm/0006-Nova-chart-Support-ephemeral-pool-creation.patch \
	file://openstack-helm/0007-Horizon-Disable-apache2-status_module.patch \
	file://openstack-helm/0008-Neutron-Add-support-for-disabling-Readiness-Liveness.patch \
	file://openstack-helm/0009-Nova-Add-support-for-disabling-Readiness-Liveness-pr.patch \
	file://openstack-helm/0010-Ironic-Add-pxe-boot-support-for-centos-image.patch \
	file://openstack-helm/0011-Use-nova-s-ping-method-to-find-out-if-the-service-is.patch \
	"


DEPENDS = " \
	python \
	python-pbr-native \
	"

inherit setuptools

RDEPENDS_${PN} += " bash"

