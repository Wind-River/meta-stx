DESCRIPTION = "pam-config"

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "70609a3d55e5b7d2be82667fc35792505f9013c4"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://opendev.org/starlingx/config.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

require stx-integ-config-files.inc

RDEPENDS_${PN} += " bash"

do_configure () {
	:
} 

do_compile() {
	:
}

do_install () {
	cd ${S}/config-files/pam-config/
	install -d -m0755 ${D}/${datadir}/starlingx
	install -d -m0755 ${D}/${sysconfdir}/pam.d

	install  -m 644 files/common-account  ${D}/${sysconfdir}/pam.d/common-account
	install  -m 644 files/common-auth     ${D}/${sysconfdir}/pam.d/common-auth
	install  -m 644 files/common-password ${D}/${sysconfdir}/pam.d/common-password
	install  -m 644 files/common-session  ${D}/${sysconfdir}/pam.d/common-session
	install  -m 644 files/common-session-noninteractive ${D}${sysconfdir}/pam.d/common-session-noninteractive
	install  -m 644 files/system-auth.pamd ${D}/${datadir}/starlingx/stx.system-auth
	install  -m 644 files/system-auth.pamd ${D}/${sysconfdir}/pam.d/system-auth
}

FILES_${PN}_append += " ${datadir} "
