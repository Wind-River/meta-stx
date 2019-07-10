SUMMARY = " StarlingX Single Server"

LICENSE = "MIT"

IMAGE_INSTALL_append = " \
	${CORE_IMAGE_BASE_INSTALL} \
	packagegroup-core-full-cmdline \
	packagegroup-core-security \
	packagegroup-core-selinux \
	packagegroup-cloud-compute \
	packagegroup-cloud-controller \
	packagegroup-cloud-network \
	packagegroup-cloud-debug \
	packagegroup-cloud-extras \
	"
IMAGE_FEATURES += "ssh-server-openssh"

inherit core-image
inherit openstack-base
inherit identity
inherit monitor

# check for 5G of free space, so we use 5G as a starting point.
# IMAGE_ROOTFS_EXTRA_SPACE_append += "+ 5000000"
# POST_KEYSTONE_SETUP_COMMAND = "/etc/keystone/hybrid-backend-setup"
