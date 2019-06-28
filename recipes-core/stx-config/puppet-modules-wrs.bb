DESCRIPTION = "puppet-modules-wrs"

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "70609a3d55e5b7d2be82667fc35792505f9013c4"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://opendev.org/starlingx/config.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

inherit externalsrc
EXTERNALSRC_pn-${PN}="${EXTERNALREPO}/stx-config"

DEPENDS = " \
	puppet \
	"

do_configure () {
	:
} 

do_compile() {
	:
}

do_install () {

	install -d -m 0755 ${D}/${datadir}/puppet/modules/dcdbsync
	install -d -m 0755 ${D}/${datadir}/puppet/modules/dcocrch
	install -d -m 0755 ${D}/${datadir}/puppet/modules/mtce
	install -d -m 0755 ${D}/${datadir}/puppet/modules/patching
	install -d -m 0755 ${D}/${datadir}/puppet/modules/sshd
	install -d -m 0755 ${D}/${datadir}/puppet/modules/dcmanager
	install -d -m 0755 ${D}/${datadir}/puppet/modules/fm
	install -d -m 0755 ${D}/${datadir}/puppet/modules/nfv
	install -d -m 0755 ${D}/${datadir}/puppet/modules/smapi
	install -d -m 0755 ${D}/${datadir}/puppet/modules/sysinv
	cp -R ${S}/puppet-modules-wrs/puppet-dcdbsync/src/dcdbsync ${D}/${datadir}/puppet/modules
	cp -R ${S}/puppet-modules-wrs/puppet-dcorch/src/dcorch ${D}/${datadir}/puppet/modules
	cp -R ${S}/puppet-modules-wrs/puppet-patching/src/patching ${D}/${datadir}/puppet/modules
	cp -R ${S}/puppet-modules-wrs/puppet-sshd/src/sshd ${D}/${datadir}/puppet/modules
	cp -R ${S}/puppet-modules-wrs/puppet-dcmanager/src/dcmanager ${D}/${datadir}/puppet/modules
	cp -R ${S}/puppet-modules-wrs/puppet-fm/src/fm ${D}/${datadir}/puppet/modules
	cp -R ${S}/puppet-modules-wrs/puppet-nfv/src/nfv ${D}/${datadir}/puppet/modules
	cp -R ${S}/puppet-modules-wrs/puppet-smapi/src/smapi ${D}/${datadir}/puppet/modules
	cp -R ${S}/puppet-modules-wrs/puppet-sysinv/src/sysinv ${D}/${datadir}/puppet/modules


}

FILES_${PN}_append += " ${datadir}"
