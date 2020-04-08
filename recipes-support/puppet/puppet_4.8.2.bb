SUMMARY = "Open source Puppet is a configuration management system"
HOMEPAGE = "https://puppetlabs.com/puppet/puppet-open-source"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7c9045ec00cc0d6b6e0e09ee811da4a0"

SRC_URI = " \
    https://downloads.puppetlabs.com/puppet/puppet-${PV}.tar.gz \
    file://${BPN}/${PV}/add_puppet_gemspec.patch \
    file://${BPN}/${PV}/puppet-updates-for-poky-stx.patch \
    file://${BPN}/${PV}/puppet-poky-dnf.patch \
    file://${BPN}/${PV}/puppet.conf \
    file://${BPN}/${PV}/puppet.init \
    file://${BPN}/${PV}/puppet.service \
"
SRC_URI[md5sum] = "095ef8bddd94dd1ee0562a3c816ab05f"
SRC_URI[sha256sum] = "fc71ca0be64b4b1282e0064b8fbf115c547cb87ca6b209da56e1b9569567404a"

inherit ruby update-rc.d systemd
inherit openssl10

DEPENDS += " \
        ruby \
        facter \
	libffi \
"

RDEPENDS_${PN} += " \
        ruby \
        facter \
        ruby-shadow \
        bash \
"

RUBY_INSTALL_GEMS = "puppet-${PV}.gem"

INITSCRIPT_NAME = "${BPN}"
INITSCRIPT_PARAMS = "start 02 5 3 2 . stop 20 0 1 6 ."

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "${BPN}.service"

do_install_append() {
    install -d ${D}${sysconfdir}/puppet
    install -d ${D}${sysconfdir}/puppet/manifests
    install -d ${D}${sysconfdir}/puppet/modules

    install -m 655 ${S}/conf/auth.conf ${D}${sysconfdir}/puppet/
    install -m 655 ${S}/conf/fileserver.conf ${D}${sysconfdir}/puppet/
    install -m 655 ${S}/conf/environment.conf ${D}${sysconfdir}/puppet/
    install -m 655 ${WORKDIR}/${BPN}/${PV}/puppet.conf ${D}${sysconfdir}/puppet/

    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/${BPN}/${PV}/puppet.service ${D}${systemd_unitdir}/system

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/${BPN}/${PV}/puppet.init ${D}${sysconfdir}/init.d/puppet
}
