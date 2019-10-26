PACKAGES += " lighttpd-config"

RDEPENDS_lighttpd-config += " bash"

do_configure_prepend () {
	:
} 

do_compile_prepend () {
	:
}

do_install_prepend () {
	cd ${S}/base/lighttpd-config
	install -d -m 1777 ${D}/www/tmp
	install -d ${D}/${sysconfdir}/lighttpd/ssl
	install -d ${D}/www/pages/dav
	install -d ${D}/${datadir}/starlingx

	install -m640 files/lighttpd.conf ${D}/${datadir}/starlingx/lighttpd.conf
	install -m755 files/lighttpd.init ${D}/${datadir}/starlingx/lighttpd.init
	install -m644 files/lighttpd-inc.conf ${D}/${sysconfdir}/lighttpd/lighttpd-inc.conf
	install -m644 files/index.html.lighttpd ${D}/www/pages/index.html
	
	# install -d ${D}/${sysconfdir}/logrotate.d
	install -m644 files/lighttpd.logrotate ${D}/${datadir}/starlingx/lighttpd.logrotate
	rm -rf ${D}/www/pages

}

# TODO:
# This and other .inc packages need to be bbappends to avoid conflicts
#	www/pages/index.html
#	www/pages/dav 

FILES_lighttpd-config = " \
	${datadir}/starlingx/lighttpd.conf \
	${datadir}/starlingx/lighttpd.init \
	${datadir}/starlingx/lighttpd.logrotate \
	${sysconfdir}/lighttpd/lighttpd-inc.conf \
	${sysconfdir}/lighttpd/ssl \
	www/tmp \
	"
