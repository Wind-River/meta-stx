DESCRIPTION = "puppet-drbd"

STABLE = "master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "496b3ba9cd74a2d12636f9e90a718739a5451169"
S = "${WORKDIR}/git"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=6089b6bd1f0d807edb8bdfd76da0b038 "

SRC_URI = "git://github.com/puppetlabs/puppetlabs-drbd.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH} \
	file://puppet-modules/0001-TIS-Patches.patch \
	file://puppet-modules/0002-Disable-timeout-for-mkfs-command.patch \
	file://puppet-modules/0003-drbd-parallel-to-serial-synchronization.patch \
	file://puppet-modules/0004-US-96914-reuse-existing-drbd-cinder-resource.patch \
	file://puppet-modules/0005-Add-PausedSync-states-to-acceptable-cstate.patch \
	file://puppet-modules/0006-CGTS-7164-Add-resource-options-cpu-mask-to-affine-drbd-kernel-threads.patch \
	file://puppet-modules/0007-Add-disk-by-path-test.patch \
	file://puppet-modules/0008-CGTS-7953-support-for-new-drbd-resources.patch \
	file://puppet-modules/0009-drbd-slow-before-swact.patch \
	"

DEPENDS += " \
	puppet \
	"
