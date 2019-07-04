DESCRIPTION = "sudo-config"
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


STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "70609a3d55e5b7d2be82667fc35792505f9013c4"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://opendev.org/starlingx/config.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

require stx-integ-config-files.inc

RDEPENDS_${PN} += " bash"

do_configure () {
	:
} 

do_compile() {
	:
}

do_install () {
	cd ${S}/config-files/sudo-config 
	install -d -m755 ${D}/${sysconfdir}/sudoers.d

#	install -m644 files/sysadmin.sudo ${D}/${sysconfdir}/sudoers.d/
	install -m644 files/wrs.sudo ${D}/${sysconfdir}/sudoers.d/sysadmin
}

# FILES_${PN}_append += " ${datadir} "
