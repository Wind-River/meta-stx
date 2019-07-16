PACKAGES += " sudo-config"
#
#TODO:
# Set the following in shadow file and add the user
# %define SYSADMIN_P 4SuW8cnXFyxsk
# useradd -m -g sys_protected -G root \
#    -d /home/sysadmin -p %{SYSADMIN_P} \
#       -s /bin/sh sysadmin 2> /dev/null || :
#
# TODO:
# the RPM spec installs sysadmin.sudo
# Source does not include sysadmin.sudo
# Use wrs.sudo for now to get passed
# +++++++ Replace with the correct file.


RDEPENDS_sudo-config += " bash"

do_configure_prepend () {
	:
} 

do_compile_prepend () {
	:
}

do_install_prepend () {
	cd ${S}/config-files/sudo-config 
	install -d -m755 ${D}/${sysconfdir}/sudoers.d

#	install -m644 files/sysadmin.sudo ${D}/${sysconfdir}/sudoers.d/
	install -m644 files/wrs.sudo ${D}/${sysconfdir}/sudoers.d/sysadmin
}

FILES_sudo-config = "  \
	${sysconfdir}/sudoers.d/sysadmin \
	"
