DESCRIPTION = "An image with Anaconda to do installation for StarlingX"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

# Support installation from initrd boot
do_image_complete[depends] += "core-image-anaconda-initramfs:do_image_complete"

DEPENDS += "isomd5sum-native"

CUSTOMIZE_LOGOS ??= "yocto-compat-logos"

# We override what gets set in core-image.bbclass
IMAGE_INSTALL = "\
    packagegroup-core-boot \
    packagegroup-core-ssh-openssh \
    ${@['', 'packagegroup-installer-x11-anaconda'][bool(d.getVar('XSERVER', True))]} \
    python3-anaconda \
    anaconda-init \
    kernel-modules \
    ${CUSTOMIZE_LOGOS} \
    dhcp-client \
    ldd \
    rng-tools \
"

IMAGE_LINGUAS = "en-us en-gb"

# Generate live image
IMAGE_FSTYPES_remove = "wic wic.bmap"
IMAGE_FSTYPES_append = " iso"

IMAGE_ROOTFS_EXTRA_SPACE =" + 102400"

inherit core-image stx-anaconda-image

KICKSTART_FILE ?= "${LAYER_PATH_meta-stx}/conf/distro/files/ks/poky_stx_aio_ks.cfg"

KICKSTART_FILE_EXTRA ?= " \
    ${LAYER_PATH_meta-stx}/conf/distro/files/ks/aio_ks.cfg \
    ${LAYER_PATH_meta-stx}/conf/distro/files/ks/aio_lowlatency_ks.cfg \
    ${LAYER_PATH_meta-stx}/conf/distro/files/ks/controller_ks.cfg \
    ${LAYER_PATH_meta-stx}/conf/distro/files/ks/net_controller_ks.cfg \
    ${LAYER_PATH_meta-stx}/conf/distro/files/ks/net_smallsystem_ks.cfg \
    ${LAYER_PATH_meta-stx}/conf/distro/files/ks/net_smallsystem_lowlatency_ks.cfg \
    ${LAYER_PATH_meta-stx}/conf/distro/files/ks/net_storage_ks.cfg \
    ${LAYER_PATH_meta-stx}/conf/distro/files/ks/net_worker_ks.cfg \
    ${LAYER_PATH_meta-stx}/conf/distro/files/ks/net_worker_lowlatency_ks.cfg \
"
