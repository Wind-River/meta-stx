FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# This bbappend replaces stx sudo-config

SRC_URI += "file://sysadmin"

do_install_append () {
	install -m644 ${S}/../sysadmin ${D}/${sysconfdir}/sudoers.d/sysadmin
}

pkg_postinst_ontarget_sudo_append () {
SYSADMIN_P="4SuW8cnXFyxsk"
/usr/sbin/groupadd sys_protected
/usr/sbin/useradd -m -g sys_protected -G root \
	 	-d /home/sysadmin -p $SYSADMIN_P \
		-s /bin/sh sysadmin 2> /dev/null || :
/usr/bin/chage -d0 sysadmin
}
