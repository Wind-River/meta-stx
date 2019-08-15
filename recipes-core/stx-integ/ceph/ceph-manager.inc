PACKAGES += " ceph-manager"

RDEPENDS_ceph-manager_append = " bash"

inherit setuptools

do_configure_prepend () {
	cd ${S}/ceph/ceph-manager/ceph-manager/
	distutils_do_configure
} 

do_compile_prepend () {
	cd ${S}/ceph/ceph-manager/ceph-manager/
	distutils_do_compile
}

do_install_prepend () {
	cd ${S}/ceph/ceph-manager/ceph-manager/
	distutils_do_install

	cd ${S}/ceph/ceph-manager/

	install -d -m 755 ${D}/${sysconfdir}/init.d
	install -p -D -m 700 scripts/init.d/ceph-manager ${D}/${sysconfdir}/init.d/ceph-manager

	install -d -m 755 ${D}/${bindir}
	install -p -D -m 700 scripts/bin/ceph-manager ${D}//${bindir}/ceph-manager

	install -d -m 755 ${D}${sysconfdir}/logrotate.d
	install -p -D -m 644 files/ceph-manager.logrotate ${D}/${sysconfdir}/logrotate.d/ceph-manager.logrotate

	install -d -m 755 ${D}/${systemd_system_unitdir}
	install -m 644 -p -D files/ceph-manager.service ${D}/${systemd_system_unitdir}/ceph-manager.service

}

# pkg_postinst_ontarget_${PN} () { }

FILES_ceph-manager_append += " \
	${systemd_system_unitdir}/ceph-manager.service \
	${sysconfdir}/init.d/ceph-manager \
	${sysconfdir}/logrotate.d/ceph-manager.logrotate \
	${bindir}/ceph-manager \
	${libdir}/python2.7/site-packages/ceph_manager \
	${libdir}/python2.7/site-packages/ceph_manager-1.0.0-py2.7.egg-info \
	${libdir}/python2.7/site-packages/ceph_manager/constants.pyc \
	${libdir}/python2.7/site-packages/ceph_manager/exception.pyc \
	${libdir}/python2.7/site-packages/ceph_manager/__init__.pyc \
	${libdir}/python2.7/site-packages/ceph_manager/i18n.py \
	${libdir}/python2.7/site-packages/ceph_manager/i18n.pyc \
	${libdir}/python2.7/site-packages/ceph_manager/exception.py \
	${libdir}/python2.7/site-packages/ceph_manager/constants.py \
	${libdir}/python2.7/site-packages/ceph_manager/ceph.pyc \
	${libdir}/python2.7/site-packages/ceph_manager/__init__.py \
	${libdir}/python2.7/site-packages/ceph_manager/monitor.pyc \
	${libdir}/python2.7/site-packages/ceph_manager/monitor.py \
	${libdir}/python2.7/site-packages/ceph_manager/server.pyc \
	${libdir}/python2.7/site-packages/ceph_manager/ceph.py \
	${libdir}/python2.7/site-packages/ceph_manager/server.py \
	${libdir}/python2.7/site-packages/ceph_manager-1.0.0-py2.7.egg-info/PKG-INFO \
	${libdir}/python2.7/site-packages/ceph_manager-1.0.0-py2.7.egg-info/top_level.txt \
	${libdir}/python2.7/site-packages/ceph_manager-1.0.0-py2.7.egg-info/dependency_links.txt \
	${libdir}/python2.7/site-packages/ceph_manager-1.0.0-py2.7.egg-info/SOURCES.txt \
	"
