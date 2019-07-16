PACKAGES += " sm"

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
}

FILES_sm = " \
	${bindir}/sm \
	"

pkg_postinst_ontarget_${PN}_append () {
        /usr/bin/systemctl enable sm.service >/dev/null 2>&1
        /usr/bin/systemctl enable sm-shutdown.service >/dev/null 2>&1
}
