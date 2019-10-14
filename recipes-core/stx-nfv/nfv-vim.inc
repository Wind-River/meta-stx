PACKAGES += " nfv-vim"

inherit setuptools

do_configure_prepend () {
	cd ${S}/nfv/nfv-vim
	distutils_do_configure
} 

do_compile_prepend () {
	cd ${S}/nfv/nfv-vim
	distutils_do_compile
}

do_install_prepend () {
	cd ${S}/nfv/nfv-vim
	distutils_do_install
	install -d -m 755 ${D}/usr/lib/ocf/resource.d/nfv
	install -p -D -m 755 scripts/vim ${D}/usr/lib/ocf/resource.d/nfv/vim
	install -p -D -m 755 scripts/vim-api ${D}/usr/lib/ocf/resource.d/nfv/vim-api
	install -p -D -m 755 scripts/vim-webserver ${D}/usr/lib/ocf/resource.d/nfv/vim-webserver
	install -d -m 755 ${D}/${sysconfdir}/nfv/
	install -d -m 755 ${D}/${sysconfdir}/nfv/vim/
	install -p -D -m 600 nfv_vim/config.ini ${D}/${sysconfdir}/nfv/vim/config.ini
	install -p -D -m 600 nfv_vim/debug.ini ${D}/${sysconfdir}/nfv/vim/debug.ini

}

#pkg_postinst_ontarget_${PN} () {

FILES_nfv-vim_append = " \
	${sysconfdir}/nfv/vim \
	${libdir}/ocf/resource.d/nfv/vim \
	${libdir}/ocf/resource.d/nfv/vim-api \
	${libdir}/ocf/resource.d/nfv/vim-webserver \
	${bindir}/nfv-vim-webserver \
	${bindir}/nfv-vim-api \
	${bindir}/nfv-vim-manage \
	${bindir}/nfv-vim \
	${libdir}/python2.7/site-packages/nfv_vim \
	${libdir}/python2.7/site-packages/nfv_vim*egg-info \
	"
