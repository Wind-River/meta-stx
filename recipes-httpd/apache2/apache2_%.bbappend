
APACHE_PORT_NUM ?= "81"

do_install_append () {
	sed -i -e 's/80/${APACHE_PORT_NUM}/' ${D}/${sysconfdir}/${BPN}/httpd.conf
}
