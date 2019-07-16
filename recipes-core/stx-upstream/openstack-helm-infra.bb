DESCRIPTION = "openstack-helm-infra"
###################################################
# TODO:
# Complete this recipe to match the rpm spec
# 
# Stage a repository file that only has a local repo
#cp %{SOURCE1} %{helm_home}/repository/repositories.yaml

## Host a server for the charts
#helm serve /tmp/charts --address localhost:8879 --url http://localhost:8879/charts &
#helm repo rm local
#helm repo add local http://localhost:8879/charts
#
## Make the charts. These produce tgz files
#make helm-toolkit
#make gnocchi
#make ingress
#make libvirt
#make mariadb
#make memcached
#make openvswitch
#make rabbitmq
#make ceph-rgw
#
## terminate helm server (the last backgrounded task)
#kill %1
#
#%install
#install -d -m 755 ${RPM_BUILD_ROOT}%{helm_folder}
#install -p -D -m 755 *.tgz ${RPM_BUILD_ROOT}%{helm_folder}
###################################################

STABLE = "master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "aae64213c95fbcea7a0a7671dcb9d8a366f16fa5"
S = "${WORKDIR}/git"
PV = "git"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

SRC_URI = "git://github.com/openstack/openstack-helm-infra.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH} \
	file://openstack-helm-infra/0001-Allow-multiple-containers-per-daemonset-pod.patch \
	file://openstack-helm-infra/0002-Add-imagePullSecrets-in-service-account.patch  \
	file://openstack-helm-infra/0003-Set-Min-NGINX-handles.patch \
	file://openstack-helm-infra/0004-Partial-revert-of-31e3469d28858d7b5eb6355e88b6f49fd6.patch \
	file://openstack-helm-infra/0005-Add-a-configmap-for-ingress-controller-config.patch \
	"
