do_install_append () {
	# remove the symlinks
	rm ${D}/www/logs
	rm ${D}/www/var

	# use tmpfile to create dirs
	install -d ${D}${sysconfdir}/tmpfiles.d/
	echo "d /www/var 0755 www root -" > ${D}${sysconfdir}/tmpfiles.d/${BPN}.conf
	echo "d /www/var/log 0755 www root -" >> ${D}${sysconfdir}/tmpfiles.d/${BPN}.conf
}
