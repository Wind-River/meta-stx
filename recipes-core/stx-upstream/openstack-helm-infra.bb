DESCRIPTION = "openstack-helm-infra"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://Makefile;md5=93e806b1a66ac32a348e4e59a2f6c132"

STABLE = "master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "aae64213c95fbcea7a0a7671dcb9d8a366f16fa5"
S = "${WORKDIR}/git"
PV = "1.0"

DEPENDS += " helm-native"

SRC_URI = "git://github.com/openstack/openstack-helm-infra.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH} \
	file://openstack-helm-infra/0001-Allow-multiple-containers-per-daemonset-pod.patch \
	file://openstack-helm-infra/0002-Add-imagePullSecrets-in-service-account.patch  \
	file://openstack-helm-infra/0003-Set-Min-NGINX-handles.patch \
	file://openstack-helm-infra/0004-Partial-revert-of-31e3469d28858d7b5eb6355e88b6f49fd6.patch \
	file://openstack-helm-infra/0005-Add-a-configmap-for-ingress-controller-config.patch \
	file://${PN}/repositories.yaml \
	"

do_configure() {
	:
}

do_compile() {
	export HELM_HOME=${WORKDIR}/build_home/.helm
	mkdir -p ${WORKDIR}/build_home/.helm/{repository/{cache,local},plugins,starters,cache,cache/archive}

	cp ${WORKDIR}/openstack-helm-infra/repositories.yaml ${HELM_HOME}/repository/

	helm serve $PWD/build_home/tmp/ --address localhost:8879 --url http://localhost:8879/charts &
	helm repo rm local
	helm repo add local http://localhost:8879/charts

	# Make the charts. These produce tgz files
	make helm-toolkit
	make gnocchi
	make ingress
	make libvirt
	make mariadb
	make memcached
	make openvswitch
	make rabbitmq
	make ceph-rgw

	# Kill the server
	kill %1
}

do_install() {
	install -m 0755 -d ${D}/${libdir}/helm
	install -p -D -m 755 *.tgz ${D}/${libdir}/helm
}

FILES_${PN} += " ${libdir}"
