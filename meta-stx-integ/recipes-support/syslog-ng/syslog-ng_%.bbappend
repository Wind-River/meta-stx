
do_install_append () {
    rm -rf ${D}${systemd_unitdir}/system/multi-user.target.wants
}

SYSTEMD_AUTO_ENABLE = "disable"
