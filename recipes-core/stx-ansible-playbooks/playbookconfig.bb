DESCRIPTION = " stx-ansible-playbooks"

# TODO:
# make this and others to .bbappends

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "c7390f63001219b5eb41a6e36f4f4643d0fc0208"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://opendev.org/starlingx/ansible-playbooks.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

DEPENDS = " \
	python \
	python-netaddr \
	python-ptyprocess \
	python-pexpect \
	python-ansible \
	"

do_configure () {
	:
} 

do_compile() {
	:
}

do_install () {
	cd ${S}/playbookconfig/playbookconfig
	oe_runmake -e \
		DESTDIR=${D}/${sysconfdir}/
	mv ${D}/${sysconfdir}/playbooks ${D}/${sysconfdir}/ansible
	chmod 644 ${D}/${sysconfdir}/ansible/ansible.cfg
	mv ${D}/${sysconfdir}/ansible/hosts ${D}/${sysconfdir}/ansible/stx-hosts
	chmod 644 ${D}/${sysconfdir}/ansible/hosts
}

#pkg_postinst_ontarget_${PN} () { }

# FILES_${PN}_append += " "
