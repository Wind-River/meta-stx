PACKAGES += " patch-alarm"

inherit setuptools

RDEPENDS_patch-alarm_append = " bash"

do_configure_append () {
	cd ${S}/patch-alarm/patch-alarm
	distutils_do_configure
} 

do_compile_append () {
	cd ${S}/patch-alarm/patch-alarm
	distutils_do_compile
}

do_install_append () {
	cd ${S}/patch-alarm/patch-alarm
	distutils_do_install

	cd ${S}/patch-alarm/

	install -m 755 -d ${D}/${bindir}
	install -m 755 -d ${D}/${sysconfdir}/init.d

	install -m 700 scripts/bin/patch-alarm-manager ${D}/${bindir}/
	install -m 700 scripts/bin/patch-alarm-manager ${D}/${sysconfdir}/init.d/
	

}

FILES_patch-alarm = " \
	${libdir}/python2.7/site-packages/patch_alarm \
	${libdir}/python2.7/site-packages/patch_alarm*.egg-info \
	${bindir}/patch-alarm-manager \
	${sysconfdir}/init.d/patch-alarm-manager \
	"
