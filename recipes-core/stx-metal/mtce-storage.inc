PACKAGES += " mtce-storage"

RDEPENDS_mtce-storage_append = " bash"

do_configure_prepend () {
	:
} 

do_compile_prepend () {
	:
}

do_install_prepend () {
	cd ${S}/mtce-storage/src/
	oe_runmake buildroot=${D} \
		_sysconfdir=${sysconfdir} _unitdir=${systemd_system_unitdir} _datarootdir=${datadir} \
		install

}

pkg_postinst_ontarget_mtce-storage_prepend () { 
	/bin/systemctl enable goenabled-storage.service
}

FILES_mtce-storage = " \
	${datadir}/licenses/mtce-storage-1.0/LICENSE \
	${systemd_system_unitdir}/goenabled-storage.service \
	${sysconfdir}/init.d/goenabledStorage \
	"
