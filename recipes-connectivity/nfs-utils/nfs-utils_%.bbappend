
do_install_append() {
	mv ${D}/${sbindir}/sm-notify ${D}/${sbindir}/nfs-utils-client_sm-notify
}
