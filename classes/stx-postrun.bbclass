# This class is intended to include workarounds and finetuning of the rootfs
# Most of the stuff in here needs to go else where.

ROOTFS_POSTPROCESS_COMMAND_append = " stx_postprocess_rootfs;"
# ETHDEV = "enp0s5"

stx_postprocess_rootfs() {

#	# We will remove this. Problem is that the puppet modules call service instead of systemctl
#	# This workaround is to be removed and the actual fix is in the puppet modules.
#
	cat > ${IMAGE_ROOTFS}/usr/bin/service << \EOF
#!/bin/bash

service_name=$1
command=$2

if [ $command = "reload" ] ; then
        command="restart"
fi
systemctl $command $service_name
EOF
	chmod 755 ${IMAGE_ROOTFS}/usr/bin/service

	# Issue: #83 /dev/root does not exist
	# This workaround is to be removed once initramfs is added

	cat > ${IMAGE_ROOTFS}/etc/udev/rules.d/99-dev-root-symlink.rules << \EOF
KERNEL=="sda3", SYMLINK+="root"
EOF
}
