meta-stx
=========

Introduction
------------------------

This layer enables starlingx on poky. 


Dependencies
-------------------------

This layer depends on:

```
	URI: git://git.openembedded.org/meta-openembedded
	branch: warrior
	revision: HEAD
	layers:
		openembedded-layer
		filesystem-layer
		meta-initramfs
		networking-layer
		perl-layer
		meta-python
		webserver
		

	URI: git://git.yoctoproject.org/meta-virtualization
	layer: virtualization-layer
	branch: warrior
	revision: HEAD

	URI: git://git.yoctoproject.org/meta-cloud-services
	branch: warrior
	revision: HEAD
	layers: cloud-services-layer
		openstack-layer
		

	URI: git://git.yoctoproject.org/meta-intel
	layer: intel
	branch: warrior
	revision: HEAD

	URI: git://git.yoctoproject.org/meta-security
	layer: security
	branch: warrior
	revision: HEAD

	URI: git://git.yoctoproject.org/meta-selinux
	layer: selinux
	branch: warrior
	revision: HEAD

	URI: https://github.com/intel-iot-devkit/meta-iot-cloud.git
	layer: iot-cloud
	branch: warrior
	revision: HEAD

	URI: git://git.openembedded.org/meta-python2 
	layer: meta-python2
	branch: warrior
	revision: HEAD

	URI: https://git.yoctoproject.org/git/meta-dpdk
	layer: dpdk
	branch: warrior
	revision: HEAD

```
You are solely responsible for determining the appropriateness of using or redistributing the above dependencies and assume any risks associated with your exercise of permissions under the license.

Maintenance
-------------------------

Maintainer:

Babak A. Sarashki

Build:
---------------------------
```
git clone --branch warrior git@github.com:zbsarashki/staging-stx.git
cd staging-stx
./setup.sh

```

Use Case:
---------------------------

License
-------
Copyright (C) 2019 Wind River Systems, Inc.

Source code included in tree for individual recipes is under the LICENSE
stated in each recipe (.bb file) unless otherwise stated.

The metadata is under the following license unless otherwise stated.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.


# Legal Notices

All product names, logos, and brands are property of their respective owners. All company, product and service names used in this software are for identification purposes only. Wind River is a registered trademarks of Wind River Systems, Inc. Linux is a registered trademark of Linus Torvalds.

Disclaimer of Warranty / No Support: Wind River does not provide support and maintenance services for this software, under Wind River’s standard Software Support and Maintenance Agreement or otherwise. Unless required by applicable law, Wind River provides the software (and each contributor provides its contribution) on an “AS IS” BASIS, WITHOUT WARRANTIES OF ANY KIND, either express or implied, including, without limitation, any warranties of TITLE, NONINFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A PARTICULAR PURPOSE. You are solely responsible for determining the appropriateness of using or redistributing the software and assume any risks associated with your exercise of permissions under the license.
