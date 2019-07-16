DESCRIPTION = "stx-base"

# TODO:
# Much of what is being done here must go into bbappends 


#STABLE = "starlingx/master"
#PROTOCOL = "https"
#BRANCH = "master"
#SRCREV = "8638c37d647a5989ee821276dc42524db7e644f8"
#S = "${WORKDIR}/git"
#PV = "19.05"
#
#LICENSE = "Apache-2.0"
#
#LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"
#
#SRC_URI = "git://opendev.org/starlingx/integ.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH} \
#	file://base/0001-cgcs-users-with-patch-ibsh-patches.patch \
#	file://base/0002-Add-DESTDIR-CFLAGS-and-LDFLAGS.patch \
#	"

# base
require base/cgcs-users.inc
require base/dhcp-config.inc
require base/dnsmasq-config.inc
require base/haproxy-config.inc
require base/initscripts-config.inc
require base/lighttpd-config.inc
require base/net-snmp-config.inc
require base/openssh-config.inc
require base/setup-config.inc
require base/systemd-config.inc

# ceph

# config

# config-files

# database

# filesystem

# kernel

# kubernetes

# ldap

# logging

# monitoring

# networking

# python

# security

# storage-drivers

# tools

# utilities 

# virt

do_configure () {
	:
} 

do_compile() {
	:
}

do_install () {
	:
}

FILES_${PN} = " "
