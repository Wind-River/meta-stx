SUMMARY = "StarlingX/cgsc-root/stx/stx-upstream"
PR = "r0"

#
# packages which content depend on MACHINE_FEATURES need to be MACHINE_ARCH
#
PACKAGE_ARCH = "${MACHINE_ARCH}"


inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = " \
	packagegroup-stx-upstream \
	"

# TODO: 
# See docs/progress.txt file
# Here we are replacing packages with those already present in
# yocto layers. Validate that the packages meet the requirements.

# TODO:
# See docs/progress.txt file
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
