
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "\
    file://stx/sshd.init \
    file://stx/sshd-keygen \
    file://stx/sshd-keygen.service \
    file://stx/sshd.service \
    file://stx/sshd.sysconfig \
"

SYSTEMD_SERVICE_${PN}-sshd = "sshd.service"

do_install_append () {
    rm -f ${D}${systemd_system_unitdir}/sshd.socket
    rm -f ${D}${systemd_system_unitdir}/sshd@service.socket
    rm -f ${D}${systemd_system_unitdir}/sshdgenkeys.service

    install -d ${D}/${sysconfdir}/init.d/
    install -m 755 ${WORKDIR}/stx/sshd.init ${D}/${sysconfdir}/init.d/sshd

    install -d ${D}/${sysconfdir}/sysconfig/
    install -m 644 ${WORKDIR}/stx/sshd.sysconfig ${D}/${sysconfdir}/sysconfig/sshd

    install -m 755 ${WORKDIR}/stx/sshd-keygen ${D}/${sbindir}/sshd-keygen
    install -m644 ${WORKDIR}/stx/sshd.service ${D}/${systemd_system_unitdir}/sshd.service
    install -m644 ${WORKDIR}/stx/sshd-keygen.service ${D}/${systemd_system_unitdir}/sshd-keygen.service

    install -d ${D}/${sysconfdir}/tmpfiles.d
    echo "d ${localstatedir}/run/sshd 0755 root root -" >> ${D}/${sysconfdir}/tmpfiles.d/sshd.conf

}

RDEPENDS_${PN} += "bash"
RDEPENDS_${PN}-sshd += "bash"

# allow both systemd service and sysvinit scripts are installed
DISTRO_FEATURES_BACKFILL_CONSIDERED_remove = "sysvinit"

USERADD_PARAM_${PN}-sshd = "-r -d /var/empty/sshd -s /sbin/nologin -g sshd -c 'Privilege-separated SSH' sshd"
GROUPADD_PARAM_${PN}-sshd = "-r ssh_keys; -r sshd"
