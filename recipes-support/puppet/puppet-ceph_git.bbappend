SRC_URI += " \
	file://puppet-ceph/0001-Roll-up-TIS-patches.patch \
	file://puppet-ceph/0002-Newton-rebase-fixes.patch \
	file://puppet-ceph/0003-Ceph-Jewel-rebase.patch \
	file://puppet-ceph/0004-US92424-Add-OSD-support-for-persistent-naming.patch \
	file://puppet-ceph/0005-Remove-puppetlabs-apt-as-ceph-requirement.patch \
	file://puppet-ceph/0006-ceph-disk-prepare-invalid-data-disk-value.patch \
	file://puppet-ceph/0007-Add-StarlingX-specific-restart-command-for-Ceph-moni.patch \
	file://puppet-ceph/0008-ceph-mimic-prepare-activate-osd.patch \
	file://puppet-ceph/0009-fix-ceph-osd-disk-partition-for-nvme-disks.patch \
	file://puppet-ceph/0010-wipe-unprepared-disks.patch \
	"
