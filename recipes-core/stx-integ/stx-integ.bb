
DESCRIPTION = "stx-integ"

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "r/stx.3.0"
SRCNAME = "integ"
SRCREV = "0bf4b546df8c7fdec8cfc6cb6f71b9609ee54306"
S = "${WORKDIR}/git"
PV = "1.0.0"

LICENSE = "Apache-2.0 & GPL-2.0"
LIC_FILES_CHKSUM = " \
	file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
	file://base/cgcs-users/cgcs-users-1.0/LICENSE;md5=3c7b4ff77c7d469e869911fde629c35c \
	file://virt/kvm-timer-advance/files/LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
	file://tools/storage-topology/storage-topology/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
	"

SRC_URI = "git://opendev.org/starlingx/${SRCNAME}.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH} \
	"
inherit distutils setuptools

do_configure () {
	:
} 

do_compile() {
	:
}

do_install () {
	
# kvm-timer-advance

	install -p -D -m 0755 ${S}/virt/kvm-timer-advance/files/setup_kvm_timer_advance.sh \
			${D}/${bindir}/setup_kvm_timer_advance.sh
	install -p -D -m 444 ${S}/virt/kvm-timer-advance/files/kvm_timer_advance_setup.service \
			${D}/${systemd_system_unitdir}/kvm_timer_advance_setup.service

}

FILES_${PN} = " "

PACKAGES += " kvm-timer-advance"
DESCRIPTION_kvm-timer-advance = "StarlingX KVM Timer Advance Package"

RDEPENDS_kvm-timer-advance += " \
	systemd \
	bash \
	"
# RDEPENDS_kvm-timer-advance += " bash"
FILES_kvm-timer-advance = " \
	${bindir}/setup_kvm_timer_advance.sh \
	${systemd_system_unitdir}/kvm_timer_advance_setup.service \
	"
