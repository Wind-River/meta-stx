PACKAGES += " sm-db"

# inherit pkgconfig
#DEPENDS += " \
#	sm-common \
#	"

do_configure_prepend () {
	:
} 

do_compile_prepend () {
	cd ${S}/service-mgmt/sm-db-1.0.0/src
	oe_runmake -e VER=0 VER_MJR=1 \
		INCLUDES="-I. $(pkg-config --cflags glib-2.0)" \
		CCFLAGS="${CXXFLAGS} -fPIC" LDFLAGS="${LDFLAGS} -shared -rdynamic"
	
}

do_install_prepend () {
	cd ${S}/service-mgmt/sm-db-1.0.0/src
	oe_runmake -e DEST_DIR=${D} BIN_DIR=${bindir} UNIT_DIR=${systemd_system_unitdir} \
			LIB_DIR=${libdir} INC_DIR=${includedir} VER=0 VER_MJR=1 install
}

FILES_sm-db = " "
