DESCRIPTION = "openssh-config"

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "70609a3d55e5b7d2be82667fc35792505f9013c4"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://opendev.org/starlingx/config.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

require stx-integ.inc

RDEPENDS_${PN} += " bash"

do_configure () {
	:
} 

do_compile() {
	:
}

do_install () {
	cd ${S}/base/openssh-config
	install -d ${D}/${datadir}/starlingx
	install -d ${D}/${systemd_system_unitdir}

	install -m640 files/sshd.pam ${D}/${datadir}/starlingx/sshd.pam
	install -m640 files/sshd_config ${D}/${datadir}/starlingx/sshd_config
	install -m640 files/ssh_config ${D}/${datadir}/starlingx/ssh_config
	install -m644 files/sshd.service ${D}/${systemd_system_unitdir}/sshd.service

}

FILES_${PN}_append += " \
	${systemd_system_unitdir} \
	${datadir} \
	"
		
