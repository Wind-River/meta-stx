PACKAGES += " docker-config"

RDEPENDS_docker-config += " bash"

do_configure_prepend () {
	:
} 

do_compile_prepend () {
	:
}

do_install_prepend () {
	cd ${S}/config-files/docker-config/files
	oe_runmake -e DATADIR=${D}/${datadir} \
		SYSCONFDIR=${D}/${sysconfdir} install
}

FILES_docker-config = " \
	${sysconfdir}/pmon.d/docker.conf \
	${sysconfdir}/systemd/system \
	${sysconfdir}/systemd/system/docker.service.d \
	${sysconfdir}/systemd/system/docker.service.d/docker-stx-override.conf \
	${sysconfdir}/logrotate.d/docker.logrotate \
	"
