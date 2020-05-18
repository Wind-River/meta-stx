meta-starlingx
=========

Build
------------------------
```
git clone --branch warrior git@github.com:zbsarashki/staging-stx.git
cd staging-stx
./setup.sh
```

Introduction
------------------------

This layer enables starlingx on poky. 

Introduction
------------------------

This  layer  is provides the base for starlingx port to yocto thud. By itself,
it provides set of generic recipes used by starlingx but without starlingx 
patches. StarlingX modifications, enhancements and patches are in meta-stx.

For further info: https://www.starlingx.io/


Dependencies
-------------------------

This layer depends on:

```
	 URI: https://github.com/zbsarashki/meta-stx.git
	 revision: HEAD
	 branch: master

	 URI: git://git.yoctoproject.org/poky.git
	 revision: HEAD
	 branch: thud

	 URI: git://git.openembedded.org/meta-openembedded
	 revision: HEAD
	 branch: thud
	 
	 URI: git://git.yoctoproject.org/meta-virtualization
	 revision: HEAD
	 branch: thud
	 
	 URI: git://git.yoctoproject.org/meta-cloud-services
	 revision: HEAD
	 branch: thud
	 
	 URI: git://git.yoctoproject.org/meta-selinux
	 revision: HEAD
	 branch: thud
	 
	 URI: git://git.yoctoproject.org/meta-security
	 revision: HEAD
	 branch: thud
	 
	 URI: https://github.com/jiazhang0/meta-secure-core.git
	 revision: HEAD
	 branch: thud
	 
	 URI: https://github.com/rauc/meta-rauc.git
	 revision: HEAD
	 branch: thud
	 
	 URI: git://git.yoctoproject.org/meta-intel
	 revision: HEAD
	 branch: thud
	 
	 URI: git://git.yoctoproject.org/meta-intel-qat
	 revision: HEAD
	 branch: thud
	 
	 URI: https://github.com/intel-iot-devkit/meta-iot-cloud.git
	 revision: HEAD
	 branch: thud
	 
```
You are solely responsible for determining the appropriateness of using or redistributing the above dependencies and assume any risks associated with your exercise of permissions under the license.

Maintenance
-------------------------

Maintainer:

Babak A. Sarashki

Building the meta-stx layer
---------------------------


Setup workspace
```
mkdir -p $P/workspace/{layers,build}
cd $P/workspace/layers

git clone --branch warrior git://git.yoctoproject.org/poky.git
git clone --branch warrior git://git.openembedded.org/meta-openembedded
git clone --branch warrior git://git.yoctoproject.org/meta-virtualization
git clone --branch warrior git://git.yoctoproject.org/meta-cloud-services
git clone --branch warrior git://git.yoctoproject.org/meta-security
git clone --branch warrior git://git.yoctoproject.org/meta-intel
git clone --branch warrior git://git.yoctoproject.org/meta-security
git clone --branch warrior git://git.yoctoproject.org/meta-selinux
git clone --branch warrior https://github.com/intel-iot-devkit/meta-iot-cloud.git
git clone --branch warrior git://git.openembedded.org/meta-python2
git clone --branch warrior https://git.yoctoproject.org/git/meta-dpdk
git clone --branch warrior git@github.com:Wind-River/meta-stx.git
git clone --branch warrior git@github.com:Wind-River/meta-stak-common.git

```
Add the following layers to conf/bblayers.conf

```

P=Path to workspace 

cd $P/workspace/layers/poky
source oe-init-build-env $P/workspace/build

cat > conf/bblayers.conf << EOF
# POKY_BBLAYERS_CONF_VERSION is increased each time build/conf/bblayers.conf
# changes incompatibly
POKY_BBLAYERS_CONF_VERSION = "2"
 
BBPATH = "\${TOPDIR}"
BBFILES ?= ""

BBLAYERS ?= " \\
$P/workspace/layers/poky/meta \\
	$P/workspace/layers/poky/meta-poky \\
	$P/workspace/layers/poky/meta-yocto-bsp \\
	$P/workspace/layers/meta-openembedded/meta-oe \\
	$P/workspace/layers/meta-openembedded/meta-networking \\
	$P/workspace/layers/meta-openembedded/meta-filesystems \\
	$P/workspace/layers/meta-openembedded/meta-perl \\
	$P/workspace/layers/meta-openembedded/meta-python \\
	$P/workspace/layers/meta-openembedded/meta-webserver \\
	$P/workspace/layers/meta-openembedded/meta-initramfs \\
	$P/workspace/layers/meta-virtualization \\
	$P/workspace/layers/meta-cloud-services \\
	$P/workspace/layers/meta-cloud-services/meta-openstack \\
	$P/workspace/layers/meta-intel \\
	$P/workspace/layers/meta-security \\
	$P/workspace/layers/meta-security/meta-security-compliance \\
	$P/workspace/layers/meta-selinux \\
	$P/workspace/layers/meta-iot-cloud \\
	$P/workspace/layers/meta-python2 \\
	$P/workspace/layers/meta-dpdk \\
	$P/workspace/layers/meta-stx \\
	$P/workspace/layers/meta-stak-common \\
	"
EOF
	sed -i -e 's/^\(#MACHINE.*\"qemuarm\"\)/MACHINE \?= \"intel-corei7-64\"\n\1/g' conf/local.conf
	echo 'PREFERRED_PROVIDER_virtual/kernel = "linux-yocto"' >> conf/local.conf

```

Use Case:
---------------------------


Building the meta-starlinx layer
---------------------------

In order to enable starlingx target, add the following to bblayers.conf:

```
BBLAYERS ?= " \
	/<abs_path_to>/layers/poky/meta \
	/<abs_path_to>/layers/poky/meta-poky \
	/<abs_path_to>/layers/poky/meta-yocto-bsp \
	/<abs_path_to>/layers/meta-openembedded/meta-oe \
	/<abs_path_to>/layers/meta-openembedded/meta-networking \
	/<abs_path_to>/layers/meta-openembedded/meta-filesystems \
	/<abs_path_to>/layers/meta-openembedded/meta-perl \
	/<abs_path_to>/layers/meta-openembedded/meta-python \
	/<abs_path_to>/layers/meta-openembedded/meta-webserver \
	/<abs_path_to>/layers/meta-openembedded/meta-initramfs \
	/<abs_path_to>/layers/meta-openembedded/meta-gnome \
	/<abs_path_to>/layers/meta-virtualization \
	/<abs_path_to>/layers/meta-cloud-services \
	/<abs_path_to>/layers/meta-cloud-services/meta-openstack \
	/<abs_path_to>/layers/meta-cloud-services/meta-openstack-aio-deploy \
	/<abs_path_to>/layers/meta-cloud-services/meta-openstack-compute-deploy \
	/<abs_path_to>/layers/meta-cloud-services/meta-openstack-controller-deploy \
	/<abs_path_to>/layers/meta-cloud-services/meta-openstack-qemu \
	/<abs_path_to>/layers/meta-cloud-services/meta-openstack-swift-deploy \
	/<abs_path_to>/layers/meta-secure-core/meta-signing-key \
	/<abs_path_to>/layers/meta-secure-core/meta-efi-secure-boot \
	/<abs_path_to>/layers/meta-secure-core/meta-encrypted-storage \
	/<abs_path_to>/layers/meta-secure-core/meta-integrity \
	/<abs_path_to>/layers/meta-secure-core/meta-tpm2 \
	/<abs_path_to>/layers/meta-secure-core/meta \
	/<abs_path_to>/layers/meta-security \
	/<abs_path_to>/layers/meta-security/meta-security-compliance \
	/<abs_path_to>/layers/meta-selinux \
	/<abs_path_to>/layers/meta-intel \
	/<abs_path_to>/layers/meta-intel-qat \
	/<abs_path_to>/layers/meta-rauc \
	/<abs_path_to>/layers/meta-iot-cloud \
	/<abs_path_to>/layers/meta-stx \
	/<abs_path_to>/layers/meta-starlingX \
	"
```

Build targets
-------------------------------

The currently available build target is stx-image-aio from meta-stx

```
bitbake stx-image-aio

```

# Legal Notices

All product names, logos, and brands are property of their respective owners. All company, product and service names used in this software are for identification purposes only. Wind River is a registered trademarks of Wind River Systems, Inc. Linux is a registered trademark of Linus Torvalds.

Disclaimer of Warranty / No Support: Wind River does not provide support and maintenance services for this software, under Wind River’s standard Software Support and Maintenance Agreement or otherwise. Unless required by applicable law, Wind River provides the software (and each contributor provides its contribution) on an “AS IS” BASIS, WITHOUT WARRANTIES OF ANY KIND, either express or implied, including, without limitation, any warranties of TITLE, NONINFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A PARTICULAR PURPOSE. You are solely responsible for determining the appropriateness of using or redistributing the software and assume any risks associated with your exercise of permissions under the license.
