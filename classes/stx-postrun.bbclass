
ROOTFS_POSTPROCESS_COMMAND_append = " stx_postprocess_rootfs;"
ETHDEV = "enp0s4"

stx_postprocess_rootfs() {
	cat > ${IMAGE_ROOTFS}/etc/build.info << \EOF
OS="poky"
SW_VERSION="19.01"
BUILD_TARGET="Host Installer"
BUILD_TYPE="Formal"
BUILD_ID="r/stx.2.0"

JOB="STX_BUILD_2.0"
BUILD_BY="starlingx.build@cengn.ca"
BUILD_NUMBER="40"
BUILD_HOST="starlingx_mirror"
BUILD_DATE="2019-08-26 23:30:00 +0000"
EOF

	mkdir -p ${IMAGE_ROOTFS}/etc/platform/
	cat > ${IMAGE_ROOTFS}/etc/platform/platform.conf << \EOF
nodetype=controllcentoser
subfunction=controller,worker,lowlatency
system_type=All-in-one
security_profile=standard
management_interface=lo
http_port=8080
INSTALL_UUID=d0bb64fe-fb55-4996-8382-ce5fd4aa251c
UUID=e4939a75-c84f-48b5-ad49-af406859d352
sdn_enabled=no
region_config=no
system_mode=simplex
sw_version=19.08
security_feature="nopti nospectre_v2"
vswitch_type=none
region_config=False
cluster_host_interface=lo
oam_interface=enp0s4
EOF

	cat > ${IMAGE_ROOTFS}/etc/keystone/password-rules.conf << \EOF

[security_compliance]


unique_last_password_count = 2


password_regex = ^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()<>{}+=_\\\[\]\-?|~`,.;:]).{7,}$


password_regex_description = Password must have a minimum length of 7 characters, and must contain at least 1 upper case, 1 lower case, 1 digit, and 1 special character'
EOF

}
