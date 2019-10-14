DESCRIPTION = "Logical Resource Management (LVM) features for Puppet."

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=751419260aa954499f7abaabaa882bbe"

SRCREV = "d0283da637ae24550fb4ba109a48ef8d5d8c8b84"
PROTOCOL = "https"
BRANCH = "master"
S = "${WORKDIR}/git"

SRC_URI = "git://github.com/puppetlabs/puppetlabs-lvm;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH} \
	file://lvm/0001-puppet-lvm-kilo-quilt-changes.patch;striplevel=5 \
	file://lvm/0002-UEFI-pvcreate-fix.patch;striplevel=5 \
	file://lvm/0003-US94222-Persistent-Dev-Naming.patch;striplevel=5 \
	file://lvm/0004-extendind-nuke_fs_on_resize_failure-functionality.patch;striplevel=5 \
	file://lvm/Fix-the-logical-statement-for-nuke_fs_on_resize.patch;striplevel=5 \
	"

do_configure() {
	:
}

do_compile() {
	:
}

do_install() {
	cd ${S}
	install -d -m 0755 ${D}/${datadir}/puppet/modules/lvm
	cp -R ${S}/* ${D}/${datadir}/puppet/modules/lvm/
}

FILES_${PN} = " ${datadir}"
