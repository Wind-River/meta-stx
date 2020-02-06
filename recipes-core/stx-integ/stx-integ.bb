DESCRIPTION = "stx-integ"

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCNAME = "integ"
SRCREV = "8638c37d647a5989ee821276dc42524db7e644f8"
S = "${WORKDIR}/git"
PV = "1.0.0"

LICENSE = "Apache-2.0 & GPL-2.0"

LIC_FILES_CHKSUM = " \
	file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
	file://base/cgcs-users/cgcs-users-1.0/LICENSE;md5=3c7b4ff77c7d469e869911fde629c35c \
	file://virt/kvm-timer-advance/files/LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263 \ 
	"

SRC_URI = "git://opendev.org/starlingx/${SRCNAME}.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH} \
	"
#	file://base/0001-cgcs-users-with-patch-ibsh-patches.patch \
#	file://base/0002-Add-DESTDIR-CFLAGS-and-LDFLAGS.patch \
#
#RDEPENDS_${PN}_append = " \
#	puppet-boolean \
#	puppetlabs-create-resources \
#	puppet-dnsmasq \
#	"

inherit distutils setuptools

#require	stx-base-cgcs-users.inc
#require	stx-collector.inc
#require	stx-filesystem.inc
#require stx-ldap.inc
#require stx-logging.inc
# networking
#require stx-networking.inc
# kernel

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
