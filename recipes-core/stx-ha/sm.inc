PACKAGES += " sm"

RDEPENDS_sm += " bash"
#DEPENDS = " \
#	sm-common \
#	sm-db \
# 	stx-metal \
# 	stx-fault
#	sqlite3 \
#	"

do_configure_prepend () {
	:
}

# TODO:
# Fix the Makefiles. We should not have to do this stuff here.

do_compile_prepend() {
	cd ${S}/service-mgmt/sm-1.0.0/src
	oe_runmake -e -j1 VER=0 VER_MJR=1 \
		INCLUDES="-I. -I${S}/service-mgmt/sm-common-1.0.0/src \
			-I${S}/service-mgmt/sm-db-1.0.0/src $(pkg-config --cflags glib-2.0)" \
		EXTRACCFLAGS="-I. -I${S}/service-mgmt/sm-common-1.0.0/src \
			-I${S}/service-mgmt/sm-db-1.0.0/src $(pkg-config --cflags glib-2.0) \
			-L${S}/service-mgmt/sm-common-1.0.0/src -L${S}/service-mgmt/sm-db-1.0.0/src \
				$(pkg-config --ldlags glib-2.0)" \
		CCFLAGS="${CXXFLAGS} -std=c++11" LDFLAGS="${LDFLAGS} -rdynamic"
}

do_install_prepend () {
	cd ${S}/service-mgmt/sm-1.0.0/src
	oe_runmake -e DEST_DIR=${D} BIN_DIR=${bindir} UNIT_DIR=${systemd_system_unitdir} \
		LIB_DIR=${libdir} INC_DIR=${includedir} VER=0 VER_MJR=1 install
	cd ${S}/service-mgmt/sm-1.0.0/scripts

	install -d ${D}/${sysconfdir}/init.d
	install sm ${D}/${sysconfdir}/init.d/sm
	install sm.shutdown ${D}/${sysconfdir}/init.d/sm-shutdown
	install -d ${D}/${sysconfdir}/pmon.d
	install -m 644 sm.conf ${D}/${sysconfdir}/pmon.d/sm.conf
	install -d ${D}/${sysconfdir}/logrotate.d
	install -m 644 sm.logrotate ${D}/${sysconfdir}/logrotate.d/sm.logrotate
	install -d 755 ${D}/usr/sbin
	install sm.notify ${D}/${sbindir}/stx-ha-sm-notify
	install sm.troubleshoot ${D}/${sbindir}/sm-troubleshoot
	install sm.notification ${D}/${sbindir}/sm-notification
	install -d $(D)${systemd_system_unitdir}
	install -m 644 *.service ${D}/${systemd_system_unitdir}
}

FILES_sm = " \
	${bindir}/sm \
	${sysconfdir}/init.d/sm \
	${sysconfdir}/init.d/sm-shutdown \
	${sysconfdir}/pmon.d/sm.conf \
	${sysconfdir}/logrotate.d/sm.logrotate \
	${sbindir}/stx-ha-sm-notify \
	${sbindir}/sm-troubleshoot \
	${sbindir}/sm-notification \
	${systemd_system_unitdir}/sm-shutdown.service \
	${systemd_system_unitdir}/sm.service \
	"

pkg_postinst_ontarget_sm_append () {
#        /usr/bin/systemctl enable sm.service >/dev/null 2>&1
#        /usr/bin/systemctl enable sm-shutdown.service >/dev/null 2>&1
	/usr/bin/update-alternatives --install /usr/sbin/sm-notify sm-notify /usr/sbin/stx-ha-sm-notify 5
}
