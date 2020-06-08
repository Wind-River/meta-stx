
require metal-common.inc

S = "${S_DIR}/installer/pxe-network-installer/pxe-network-installer"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

DEPENDS += " syslinux"

RDEPENDS_${PN}_append += " \
		syslinux \
		bash \
		"

do_compile[noexec] = "1"

do_install() {
	install -d -m 0755 ${D}/pxeboot
	install -d -m 0755 ${D}/pxeboot/pxelinux.cfg.files
	install -d -m 0755 ${D}/pxeboot/rel-${STX_REL}
	install -d -m 0755 ${D}/pxeboot/rel-${STX_REL}/LiveOS/
	install -d -m 0755 ${D}/pxeboot/EFI
	install -d -m 0755 ${D}/pxeboot/EFI/stx_thud
	install -d -m 0755 ${D}/${sbindir}
	ln -fs /pxeboot/stx_thud ${D}/pxeboot/centos
	ln -fs ${libdir}/grub/x86_64-efi ${D}/pxeboot/EFI/stx_thud/

	# Install the files:
	if [ ! -z "${INSTALLER_IMG_DIR}" ]; then
		install -m 0644 ${INSTALLER_IMG_DIR}/vmlinuz ${D}/pxeboot/stx_thud/rel-${STX_REL}/installer-bzImage_1.0
		install -m 0644 ${INSTALLER_IMG_DIR}/initrd.img ${D}/pxeboot/stx_thud/rel-${STX_REL}/installer-intel-x86-64-initrd_1.0
		install -m 0644 ${INSTALLER_IMG_DIR}/squashfs.img ${D}/pxeboot/stx_thud/rel-${STX_REL}/LiveOS/squashfs.img
		ln -fs installer-bzImage_1.0 ${D}/pxeboot/rel-${STX_REL}/installer-bzImage
		ln -fs installer-intel-x86-64-initrd_1.0 ${D}/pxeboot/rel-${STX_REL}/installer-initrd
	fi

	install -m 755 pxeboot-update.sh ${D}/${sbindir}/pxeboot-update-${STX_REL}.sh

	install -m 644 ${S_DIR}/bsp-files/kickstarts/post_clone_iso_ks.cfg ${D}/pxeboot/post_clone_iso_ks.cfg

	install -m 644 default ${D}/pxeboot/pxelinux.cfg.files/default
	install -m 644 default.static ${D}/pxeboot/pxelinux.cfg.files/default.static
	install -m 644 centos-pxe-controller-install ${D}/pxeboot/pxelinux.cfg.files/pxe-controller-install-${STX_REL}
	install -m 644 centos-pxe-worker-install ${D}/pxeboot/pxelinux.cfg.files/pxe-worker-install-${STX_REL}
	install -m 644 centos-pxe-smallsystem-install ${D}/pxeboot/pxelinux.cfg.files/pxe-smallsystem-install-${STX_REL}
	install -m 644 centos-pxe-storage-install ${D}/pxeboot/pxelinux.cfg.files/pxe-storage-install-${STX_REL}
	install -m 644 centos-pxe-worker_lowlatency-install ${D}/pxeboot/pxelinux.cfg.files/pxe-worker_lowlatency-install-${STX_REL}
	install -m 644 centos-pxe-smallsystem_lowlatency-install ${D}/pxeboot/pxelinux.cfg.files/pxe-smallsystem_lowlatency-install-${STX_REL}

	# UEFI support
	install -m 644 pxe-grub.cfg ${D}/pxeboot/pxelinux.cfg.files/grub.cfg
	install -m 644 pxe-grub.cfg.static ${D}/pxeboot/pxelinux.cfg.files/grub.cfg.static

	# Copy EFI boot image. It will be used to create ISO on the Controller.
	if [ ! -z "${INSTALLER_IMG_DIR}" ]; then
		install -m 644 efiboot.img ${D}/pxeboot/rel-${STX_REL}/
	fi
	install -m 644 efi-centos-pxe-controller-install ${D}/pxeboot/pxelinux.cfg.files/efi-pxe-controller-install-${STX_REL}
	install -m 644 efi-centos-pxe-worker-install ${D}/pxeboot/pxelinux.cfg.files/efi-pxe-worker-install-${STX_REL}
	install -m 644 efi-centos-pxe-smallsystem-install ${D}/pxeboot/pxelinux.cfg.files/efi-pxe-smallsystem-install-${STX_REL}
	install -m 644 efi-centos-pxe-storage-install ${D}/pxeboot/pxelinux.cfg.files/efi-pxe-storage-install-${STX_REL}
	install -m 644 efi-centos-pxe-worker_lowlatency-install ${D}/pxeboot/pxelinux.cfg.files/efi-pxe-worker_lowlatency-install-${STX_REL}
	install -m 644 efi-centos-pxe-smallsystem_lowlatency-install ${D}/pxeboot/pxelinux.cfg.files/efi-pxe-smallsystem_lowlatency-install-${STX_REL}

	sed -i "s/xxxSW_VERSIONxxx/${STX_REL}/g" ${D}/pxeboot/pxelinux.cfg.files/pxe-* ${D}/pxeboot/pxelinux.cfg.files/efi-pxe-* 
	
	# Copy Titanium grub.cfg. It will be used to create ISO on the Controller.
	install -m 0644 ${S_DIR}/bsp-files/grub.cfg ${D}/pxeboot/EFI/ 

	# UEFI bootloader expect the grub.cfg file to be in /pxeboot/ so create a symlink for it
	ln -fs pxelinux.cfg/grub.cfg ${D}/pxeboot/grub.cfg
}

pkg_postinst_ontarget_pxe-network_installer() {
	install -m 0644 /usr/share/syslinux/menu.c32 /pxeboot
	install -m 0644 /usr/share/syslinux/vesamenu.c32 /pxeboot
	install -m 0644 /usr/share/syslinux/chain.c32 /pxeboot
	install -m 0644 /usr/share/syslinux/linux.c32 /pxeboot
	install -m 0644 /usr/share/syslinux/reboot.c32 /pxeboot
	install -m 0644 /usr/share/syslinux/pxechn.c32 /pxeboot
	install -m 0644 /usr/share/syslinux/pxelinux.0 /pxeboot
	install -m 0644 /usr/share/syslinux/gpxelinux.0 /pxeboot
}

FILES_${PN}_append  = " \
	/pxeboot \
	${sbindir}/pxeboot-update-${STX_REL}.sh \
	"
	

