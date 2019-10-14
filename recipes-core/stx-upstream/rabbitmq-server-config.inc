PACKAGES += " rabbitmq-server-config"

RDEPENDS_rabbitmq-server-config = " bash"

do_configure_prepend () {
	:
} 

do_compile_prepend () {
	:
}

do_install_prepend () {
	cd ${S}/openstack/rabbitmq-server-config/files

	install -d -m 755 ${D}/${libdir}/ocf/resource.d/rabbitmq
	# install -d -m 755 ${D}/${systemd_system_unitdir}/
	install -d -m 755 ${D}/${datadir}/starlingx

	install -m 0755 rabbitmq-server.ocf              ${D}/${libdir}/ocf/resource.d/rabbitmq/stx.rabbitmq-server
	install -m 0644 rabbitmq-server.logrotate        ${D}${datadir}/starlingx/stx.rabbitmq-server.logrotate
	# install -m 0644 rabbitmq-server.service.example  ${D}${systemd_system_unitdir}/rabbitmq-server.service


}

#	${systemd_system_unitdir}/rabbitmq-server.service 
FILES_rabbitmq-server-config = " \
	${libdir}/ocf/resource.d/rabbitmq/stx.rabbitmq-server \
	${datadir}/starlingx/stx.rabbitmq-server.logrotate \
	"
