PACKAGES += " libsm-common"
PACKAGES += " sm-common-libs"
PACKAGES += " sm-eru"

#DEPENDS += " \
#	glib-2.0 \
#	sqlite3 \
#	"

RDEPENDS_sm-eru += " bash"

do_configure_prepend () {
	:
} 

do_compile_prepend () {
	cd ${S}/service-mgmt/sm-common-1.0.0/src
	oe_runmake -e VER=0 VER_MJR=1 \
		INCLUDES="-I. $(pkg-config --cflags glib-2.0)" \
		CCFLAGS="${CXXFLAGS} -fPIC" LDFLAGS="${LDFLAGS} -shared -rdynamic" \
		EXTRACCFLAGS="${LDFLAGS}" 
	
}

do_install_prepend () {
	cd ${S}/service-mgmt/sm-common-1.0.0/src
	oe_runmake -e DEST_DIR=${D} BIN_DIR=${bindir} UNIT_DIR=${systemd_system_unitdir} \
			LIB_DIR=${libdir} INC_DIR=${includedir} VER=0 VER_MJR=1 install
 
	cd ${S}/service-mgmt/sm-common-1.0.0/
	install -d -m 755 ${D}/${systemd_system_unitdir}
	# install -m 755 -d ${D}/${sysconfdir}/pmon.d
	# install -m 755 -d ${D}/${sysconfdir}/init.d

	install -m 644 -p -D scripts/sm-eru.service ${D}/${systemd_system_unitdir}/sm-eru.service
	install -m 644 -p -D scripts/sm-watchdog.service ${D}/${systemd_system_unitdir}/sm-watchdog.service

	install -m 640 -p -D scripts/sm-eru.conf ${D}/${sysconfdir}/pmon.d/sm-eru.conf
	install -m 640 -p -D scripts/sm-watchdog.conf ${D}/${sysconfdir}/pmon.d/sm-watchdog.conf
	install -m 750 -p -D scripts/sm-eru ${D}/${sysconfdir}/init.d/sm-eru
	# install -m 750 -p -D scripts/sm-watchdog ${D}/${sysconfdir}/init.d/sm-watchdog
}

FILES_libsm-common = " \
	${libdir}/libsm_common.so.0 \
	${libdir}/libsm_common.so.1 \
	${libdir}/libsm_common.so \
	"

FILES_sm-common-libs = " \
	var/lib/sm/watchdog/modules/libsm_watchdog_nfs.so.0 \
	var/lib/sm/watchdog/modules/libsm_watchdog_nfs.so.1 \
	"

FILES_sm-eru = " \
	${bindir}/sm-eru \
	${bindir}/sm-eru-dump \
	${bindir}/sm-watchdog \
	${systemd_system_unitdir}/sm-eru.service \
	${systemd_system_unitdir}/sm-watchdog.service \
	${sysconfdir}/init.d/sm-eru \
	${sysconfdir}/pmon.d/sm-eru.conf \
	${sysconfdir}/pmon.d/sm-watchdog.conf \
	"
