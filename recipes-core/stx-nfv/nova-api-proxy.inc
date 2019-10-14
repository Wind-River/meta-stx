PACKAGES += " nova-api-proxy"

inherit setuptools


do_configure_append () {
	cd ${S}/nova-api-proxy/nova-api-proxy
	distutils_do_configure
} 

do_compile_append( ) {
	cd ${S}/nova-api-proxy/nova-api-proxy
	distutils_do_compile
}

do_install_append () {
	cd ${S}/nova-api-proxy/nova-api-proxy
	distutils_do_install

	install -d -m 755 ${D}/${systemd_system_unitdir}
	install -p -D -m 644 nova_api_proxy/scripts/api-proxy.service ${D}/${systemd_system_unitdir}/api-proxy.service
	install -d -m 755 ${D}/${sysconfdir}/rc.d/init.d
	install -p -D -m 755 nova_api_proxy/scripts/api-proxy ${D}/${sysconfdir}/rc.d/init.d/api-proxy

	install -d -m 755 ${D}/${sysconfdir}/proxy
	install -p -D -m 700 nova_api_proxy/nova-api-proxy.conf ${D}${sysconfdir}/proxy/nova-api-proxy.conf
	install -p -D -m 700 nova_api_proxy/api-proxy-paste.ini ${D}${sysconfdir}/proxy/api-proxy-paste.ini
	

}


FILES_nova-api-proxy = " \ 
	${bindir}/nova-api-proxy \
	${sysconfdir}/proxy/api-proxy-paste.ini \
	${sysconfdir}/proxy/api-proxy-paste.conf \
	${sysconfdir}/proxy/nova-api-proxy.conf \
	${sysconfdir}/rc.d/init.d/api-proxy \
	${systemd_system_unitdir}/api-proxy.service \
	${libdir}/python2.7/site-packages/nova_api_proxy/ \
	${libdir}/python2.7/site-packages/api_proxy*egg-info \
	"
