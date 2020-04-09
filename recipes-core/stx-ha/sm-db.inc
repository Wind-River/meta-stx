PACKAGES += " sm-db"

DEPENDS =+ "sqlite3-native"

do_configure_append () {
	:
} 

do_compile_append () {
	cd ${S}/service-mgmt/sm-db
	sqlite3 database/sm.db < database/create_sm_db.sql
	sqlite3 database/sm.hb.db < database/create_sm_hb_db.sql

	oe_runmake -e VER=0 VER_MJR=1 \
		INCLUDES="-I. -I${S}/service-mgmt/sm-common/src " \
		CCFLAGS="${CXXFLAGS} -fPIC" \
		LDFLAGS="${LDFLAGS} -shared -rdynamic -L${S}/service-mgmt/sm-common/src " \
		EXTRACCFLAGS="-D_TEST__AA__BB ${LDFLAGS} -L${S}/service-mgmt/sm-common/src "
}

do_install_append () {
	cd ${S}/service-mgmt/sm-db
	oe_runmake -e DEST_DIR=${D} BIN_DIR=${bindir} UNIT_DIR=${systemd_system_unitdir} \
			LIB_DIR=${libdir} INC_DIR=${includedir} VER=0 VER_MJR=1 install
}

FILES_sm-db = " \
	${bindir}/sm_db_build \
	${libdir}/libsm_db.so.1 \
	${libdir}/libsm_db.so.0 \
	${localstatedir}/lib/sm \
	"
