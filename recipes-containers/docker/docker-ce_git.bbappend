do_install_append () {
	# remove the symlink and create actual dir
	rm -f ${D}${sysconfdir}/docker
	install -d -m 0755 ${D}${sysconfdir}/docker
}
