PACKAGES += " openssh-config"

RDEPENDS_openssh-config += " bash"

do_configure_prepend () {
	:
} 

do_compile_prepend () {
	:
}

do_install_prepend () {
	cd ${S}/base/openssh-config
	install -d ${D}/${datadir}/starlingx
	install -d ${D}/${systemd_system_unitdir}

	install -m640 files/sshd.pam ${D}/${datadir}/starlingx/sshd.pam
	install -m640 files/sshd_config ${D}/${datadir}/starlingx/sshd_config
	install -m640 files/ssh_config ${D}/${datadir}/starlingx/ssh_config
	install -m644 files/sshd.service ${D}/${systemd_system_unitdir}/sshd.service

}

FILES_openssh-config_append += " \
	${datadir}/starlingx/sshd.pam \
	${datadir}/starlingx/sshd_config \
	${datadir}/starlingx/ssh_config \
	${systemd_system_unitdir}/sshd.service \
	"
