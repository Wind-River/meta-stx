#
## Copyright (C) 2019 Wind River Systems, Inc.
#
#  Licensed under the Apache License, Version 2.0 (the "License");
#  you may not use this file except in compliance with the License.
#  You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
#  Unless required by applicable law or agreed to in writing, software
#  distributed under the License is distributed on an "AS IS" BASIS,
#  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#  See the License for the specific language governing permissions and
#  limitations under the License.

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " \
	file://${BPN}/0001-Roll-up-TIS-patches.patch \
	file://${BPN}/0002-Newton-rebase-fixes.patch \
	file://${BPN}/0003-Ceph-Jewel-rebase.patch \
	file://${BPN}/0004-US92424-Add-OSD-support-for-persistent-naming.patch \
	file://${BPN}/0005-Remove-puppetlabs-apt-as-ceph-requirement.patch \
	file://${BPN}/0006-ceph-disk-prepare-invalid-data-disk-value.patch \
	file://${BPN}/0007-Add-StarlingX-specific-restart-command-for-Ceph-moni.patch \
	file://${BPN}/0008-ceph-mimic-prepare-activate-osd.patch \
	file://${BPN}/0009-fix-ceph-osd-disk-partition-for-nvme-disks.patch \
	file://${BPN}/0010-wipe-unprepared-disks.patch \
	file://${BPN}/0011-puppet-ceph-changes-for-poky-stx.patch \
	"
inherit openssl10
