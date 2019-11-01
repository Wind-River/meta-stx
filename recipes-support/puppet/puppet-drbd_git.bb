DESCRIPTION = "puppet-drbd"

PV = "0.3.1"
SRCREV = "496b3ba9cd74a2d12636f9e90a718739a5451169"
PROTOCOL = "https"
BRANCH = "master"
S = "${WORKDIR}/git"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=6089b6bd1f0d807edb8bdfd76da0b038 "

SRC_URI = " \
	git://github.com/voxpupuli/puppet-drbd;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH} \
	file://drbd/Add-gemspec.patch \
	file://drbd/0001-TIS-Patches.patch \
	file://drbd/0002-Disable-timeout-for-mkfs-command.patch \
	file://drbd/0003-drbd-parallel-to-serial-synchronization.patch \
	file://drbd/0004-US-96914-reuse-existing-drbd-cinder-resource.patch \
	file://drbd/0005-Add-PausedSync-states-to-acceptable-cstate.patch \
	file://drbd/0006-CGTS-7164-Add-resource-options-cpu-mask-to-affine-drbd-kernel-threads.patch \
	file://drbd/0007-Add-disk-by-path-test.patch \
	file://drbd/0008-CGTS-7953-support-for-new-drbd-resources.patch \
	file://drbd/0009-drbd-slow-before-swact.patch \
	"
inherit ruby 

DEPENDS += " \
	ruby \
	facter \
	"

RDEPENDS_${PN} += " \
	ruby \
	facter \
	puppet \
	"

RUBY_INSTALL_GEMS = "${PN}-${PV}.gem"

do_install_append() {
	install -d -m 0755 ${D}/${datadir}/puppet/modules/drbd
	cp -r ${S}/* ${D}/${datadir}/puppet/modules/drbd
}

FILES_${PN} += "${datadir}"
