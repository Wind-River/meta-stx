SUMMARY = "Distributed block device driver for Linux"

DESCRIPTION = " DRBD, developed by LINBIT, is a software that allows RAID 1 functionality over \
	TCP/IP and RDMA for GNU/Linux. DRBD is a block device which is designed to build high \
	availability clusters and software defined storage by providing a virtual shared device \
	which keeps disks in nodes synchronised using TCP/IP or RDMA. This simulates RAID 1 but \
	avoids the use of uncommon hardware (shared SCSI buses or Fibre Channel)."
HOMEPAGE = "http://www.drbd.org/"
SECTION = "admin"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=5574c6965ae5f583e55880e397fbb018"

SRC_URI = "git://github.com/LINBIT/drbd-8.4.git;name=drbd-utils \
	file://0001-skip_wait_con_int_on_simplex.patch \
	file://0002-drbd-conditional-crm-dependency.patch \
	file://0003-drbd_report_condition.patch \
	file://0004-drbdadm-ipaddr-change.patch \
	file://0005-drbd_reconnect_standby_standalone.patch \
	file://0006-avoid-kernel-userspace-version-check.patch \
	file://0007-Update-OCF-to-attempt-connect-in-certain-states.patch \
	file://0008-Increase-short-cmd-timeout-to-15-secs.patch \
	file://0009-Check-for-mounted-device-before-demoting-Primary-DRB.patch \
	file://0010-Include-sysmacros-for-major-minor-macros.patch \
	file://0011-Disable-documentation.patch \
	"

PV = "8.4.3"

# https://www.linbit.com/downloads/drbd/8.4/archive/

inherit autotools

DEPENDS += " \
	linux-libc-headers \
	glibc \
	"

SRCREV_drbd-utils = "89a294209144b68adb3ee85a73221f964d3ee515"

S = "${WORKDIR}/git"

# UPSTREAM_CHECK_URI = "https://github.com/LINBIT/drbd-utils/releases"

#SYSTEMD_SERVICE_${PN} = "drbd.service"
#SYSTEMD_AUTO_ENABLE = "disable"

inherit autotools-brokensep

EXTRA_OECONF = " \
		--with-utils			\
                --with-initdir=/etc/init.d	\
		--without-km			\
                --with-pacemaker		\
                --with-rgmanager		\
                --with-bashcompletion		\
		--with-udev			\
		--with-heartbeat		\
                --with-distro debian		\
               "

RDEPENDS_${PN} += "bash perl-module-getopt-long perl-module-exporter perl-module-constant perl-module-overloading perl-module-exporter-heavy"

FILES_${PN} = "\
	/var \
	/run \
	${base_sbindir}/drbdsetup \
	${base_sbindir}/drbdadm \
	${base_sbindir}/drbdmeta \
	${nonarch_base_libdir}/drbd/drbdsetup-83 \
	${nonarch_base_libdir}/drbd/drbdadm-83 \
	${sysconfdir}/init.d/drbd \
	${sysconfdir}/drbd.conf \
	${sysconfdir}/xen \
	${sysconfdir}/drbd.d \
	${sbindir}/drbd-overview \
	${libdir}/drbd/outdate-peer.sh \
	${libdir}/drbd/snapshot-resync-target-lvm.sh \
	${libdir}/drbd/unsnapshot-resync-target-lvm.sh \
	${libdir}/drbd/notify-out-of-sync.sh \
	${libdir}/drbd/notify-split-brain.sh \
	${libdir}/drbd/notify-emergency-reboot.sh \
	${libdir}/drbd/notify-emergency-shutdown.sh \
	${libdir}/drbd/notify-io-error.sh \
	${libdir}/drbd/notify-pri-lost-after-sb.sh \
	${libdir}/drbd/notify-pri-lost.sh \
	${libdir}/drbd/notify-pri-on-incon-degr.sh \
	${libdir}/drbd/notify.sh \
	${libdir}/drbd/rhcs_fence \
	${sysconfdir}/udev/rules.d/65-drbd.rules \
	${libdir}/drbd/crm-fence-peer.sh \
	${libdir}/drbd/crm-unfence-peer.sh \
	${libdir}/drbd/stonith_admin-fence-peer.sh \
	${libdir}/ocf/resource.d/linbit/drbd \
	${datadir}/cluster/drbd.sh \
	${datadir}/cluster/drbd.metadata \
	${sysconfdir}/ha.d/resource.d/drbddisk \
	${sysconfdir}/ha.d/resource.d/drbdupper \
	${sysconfdir}/bash_completion.d/drbdadm* \
	"
