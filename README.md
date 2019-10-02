meta-stx
=========

Introduction
------------------------

This layer  is intended to enable starlingx on poky. 


Dependencies
-------------------------

This layer depends on:

```
	URI: git://git.openembedded.org/meta-openembedded/
	revision: HEAD

	layes: meta-oe
	meta-python
	meta-networking
```
You are solely responsible for determining the appropriateness of using or redistributing the above dependencies and assume any risks associated with your exercise of permissions under the license.

Maintenance
-------------------------

Maintainer:
        Babak Sarashki  <babak.sarashki@windriver.com>


Building the meta-stx layer
---------------------------


Use Case: Virtual Network Setup
-------------------------------

This script is inteded to setup virtual network between ns's on the host.

```
mkdir -p layers
cd layers

git clone --branch thud git://git.yoctoproject.org/poky.git
git clone --branch thud git://git.openembedded.org/meta-openembedded
git clone --branch thud git://git.yoctoproject.org/meta-virtualization
git clone --branch thud git://git.yoctoproject.org/meta-cloud-services
git clone --branch thud git://git.yoctoproject.org/meta-intel
git clone --branch thud git://git.yoctoproject.org/meta-intel-qat

git clone --branch thud git://git.yoctoproject.org/meta-selinux
git clone --branch thud git://git.yoctoproject.org/meta-security
git clone --branch thud https://github.com/jiazhang0/meta-secure-core.git
git clone --branch thud https://github.com/intel-iot-devkit/meta-iot-cloud.git
git clone --branch thud https://github.com/rauc/meta-rauc.git
git clone https://github.com/zbsarashki/meta-stx.git

cd poky
. ./oe-init-build-env /path/to/prj/dir

```
Add the following layers to conf/bblayers.conf
```
PATH_TO_LOCAL_REPO/layers/poky/meta
PATH_TO_LOCAL_REPO/layers/poky/meta-poky
PATH_TO_LOCAL_REPO/layers/poky/meta-yocto-bsp
PATH_TO_LOCAL_REPO/layers/meta-openembedded/meta-oe
PATH_TO_LOCAL_REPO/layers/meta-openembedded/meta-networking
PATH_TO_LOCAL_REPO/layers/meta-openembedded/meta-filesystems
PATH_TO_LOCAL_REPO/layers/meta-openembedded/meta-perl
PATH_TO_LOCAL_REPO/layers/meta-openembedded/meta-python
PATH_TO_LOCAL_REPO/layers/meta-openembedded/meta-webserver
PATH_TO_LOCAL_REPO/layers/meta-openembedded/meta-initramfs
PATH_TO_LOCAL_REPO/layers/meta-openembedded/meta-gnome
PATH_TO_LOCAL_REPO/layers/meta-virtualization
PATH_TO_LOCAL_REPO/layers/meta-cloud-services
PATH_TO_LOCAL_REPO/layers/meta-cloud-services/meta-openstack
PATH_TO_LOCAL_REPO/layers/meta-cloud-services/meta-openstack-aio-deploy
PATH_TO_LOCAL_REPO/layers/meta-cloud-services/meta-openstack-compute-deploy
PATH_TO_LOCAL_REPO/layers/meta-cloud-services/meta-openstack-controller-deploy
PATH_TO_LOCAL_REPO/layers/meta-cloud-services/meta-openstack-qemu
PATH_TO_LOCAL_REPO/layers/meta-cloud-services/meta-openstack-swift-deploy
PATH_TO_LOCAL_REPO/layers/meta-measured
PATH_TO_LOCAL_REPO/layers/meta-secure-core/meta-signing-key
PATH_TO_LOCAL_REPO/layers/meta-secure-core/meta-efi-secure-boot
PATH_TO_LOCAL_REPO/layers/meta-secure-core/meta-encrypted-storage
PATH_TO_LOCAL_REPO/layers/meta-secure-core/meta-integrity
PATH_TO_LOCAL_REPO/layers/meta-secure-core/meta-tpm2
PATH_TO_LOCAL_REPO/layers/meta-secure-core/meta
PATH_TO_LOCAL_REPO/layers/meta-security
PATH_TO_LOCAL_REPO/layers/meta-security/meta-security-compliance
PATH_TO_LOCAL_REPO/layers/meta-selinux
PATH_TO_LOCAL_REPO/layers/meta-intel
PATH_TO_LOCAL_REPO/layers/meta-intel-qat
PATH_TO_LOCAL_REPO/layers/meta-rauc
PATH_TO_LOCAL_REPO/layers/meta-stx
PATH_TO_LOCAL_REPO/layers/local
PATH_TO_LOCAL_REPO/layers/meta-iot-cloud
```

# Legal Notices

All product names, logos, and brands are property of their respective owners. All company, product and service names used in this software are for identification purposes only. Wind River is a registered trademarks of Wind River Systems, Inc. Linux is a registered trademark of Linus Torvalds.

Disclaimer of Warranty / No Support: Wind River does not provide support and maintenance services for this software, under Wind River’s standard Software Support and Maintenance Agreement or otherwise. Unless required by applicable law, Wind River provides the software (and each contributor provides its contribution) on an “AS IS” BASIS, WITHOUT WARRANTIES OF ANY KIND, either express or implied, including, without limitation, any warranties of TITLE, NONINFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A PARTICULAR PURPOSE. You are solely responsible for determining the appropriateness of using or redistributing the software and assume any risks associated with your exercise of permissions under the license.
