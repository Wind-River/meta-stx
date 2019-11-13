FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
do_install_append() {
	install -d -m 0755 ${D}/${datadir}/puppet/modules/vswitch
	cp -R ${S}/* ${D}/${datadir}/puppet/modules/vswitch
}

FILES_${PN} += " ${datadir}"
