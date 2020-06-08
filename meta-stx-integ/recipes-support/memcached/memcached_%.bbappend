
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://memcached.sysconfig"

inherit useradd

USERADD_PACKAGES = "${PN}"

USERADD_PARAM_${PN} = "-r -g memcached -d /run/memcached -s /sbin/nologin -c 'Memcached daemon' memcached"
GROUPADD_PARAM_${PN} = "-r memcached"

do_install_append () {
    install -d ${D}${sysconfdir}/sysconfig
    install -m 0644 ${WORKDIR}/memcached.sysconfig ${D}${sysconfdir}/sysconfig/memcached
}
