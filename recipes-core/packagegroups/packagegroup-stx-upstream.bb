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
# Port required/missing packages.

RDEPENDS_packagegroup-stx-upstream = " \
	python-barbicanclient \
	python-cinderclient \
	python-glanceclient \
	python-heatclient \
	python-horizon \
	python-keystoneauth1 \
	python-keystoneclient \
	python-magnumclient \
	python-neutronclient \
	python-novaclient \
	python-openstackclient \
	python-openstacksdk \
	stx-ocf-scripts \
	rabbitmq-server-config \
	"
