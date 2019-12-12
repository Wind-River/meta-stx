FILESEXTRAPATHS_prepend := "${THISDIR}/patches:${THISDIR}/files:"
DESCRIPTION = " stx-ansible-playbooks"

# TODO:
# make this and others to .bbappends

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "c7390f63001219b5eb41a6e36f4f4643d0fc0208"
S = "${WORKDIR}/git"
PV = "19.01"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = " \
	git://opendev.org/starlingx/ansible-playbooks.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH} \
	file://0001-Adjust-absolute-path.patch \
	"

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
		DESTDIR=${D}/${datadir}/ansible/stx-ansible
}

pkg_postinst_ontarget_${PN}() { 
	cp /etc/ansible/ansible.cfg /etc/ansible/ansible.cfg.orig
	cp /etc/ansible/hosts /etc/ansible/hosts.orig
	cp /usr/share/ansible/stx-ansible/playbooks/{ansible.cfg,hosts} /etc/ansible

}

FILES_${PN}_append += " \
	${datadir} \
	\"
