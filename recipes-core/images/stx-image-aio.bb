SUMMARY = " StarlingX Single Server"

LICENSE = "MIT"

CORE_IMAGE_EXTRA_INSTALL = " \
	packagegroup-core-base-utils  \
	"

IMAGE_INSTALL_append = " \
	${CORE_IMAGE_BASE_INSTALL} \
	packagegroup-core-full-cmdline \
	packagegroup-core-lsb \
	packagegroup-stx-upstream \
	packagegroup-stx-puppet \
	packagegroup-stx-fault \
	packagegroup-stx-metal \
	packagegroup-stx-nfv \
	packagegroup-stx-monitoring \
	packagegroup-stx-ha \
	packagegroup-stx-config-files \
	packagegroup-stx-update \
	packagegroup-stx-integ \
	packagegroup-stx-config \
	packagegroup-stx-distributedcloud \
	starlingx-dashboard \
	playbookconfig \
	distributedcloud-client-dcmanager \
	registry-token-server \
	"

IMAGE_FEATURES += " \
	package-management \
	ssh-server-openssh \
	"

# inherit stx-postrun
inherit core-image
inherit distro_features_check
inherit openstack-base
inherit identity
inherit monitor

# We need docker-ce
PACKAGE_EXCLUDE += " docker"
