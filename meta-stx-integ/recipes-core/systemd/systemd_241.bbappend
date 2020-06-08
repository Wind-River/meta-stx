
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
	file://0900-inject-milisec-in-syslog-date.patch \
	"

STX_DEFAULT_LOCALE ?= "en_US.UTF-8"

do_install_append () {
	install -d ${D}${sysconfdir}
	echo LANG=${STX_DEFAULT_LOCALE} >> ${D}${sysconfdir}/locale.conf
}

FILES_${PN} += "${sysconfdir}/locale.conf"
