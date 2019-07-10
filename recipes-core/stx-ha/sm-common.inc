PACKAGES += " sm-common"

#DEPENDS += " \
#	glib-2.0 \
#	sqlite3 \
#	"

do_configure_prepend () {
	:
} 

do_compile_prepend () {
	cd ${S}/service-mgmt/sm-common-1.0.0/src
	oe_runmake -e VER=0 VER_MJR=1 \
		INCLUDES="-I. $(pkg-config --cflags glib-2.0)" \
		CCFLAGS="${CXXFLAGS} -fPIC" LDFLAGS="${LDFLAGS} -shared -rdynamic"
	
}

do_install_prepend () {
	cd ${S}/service-mgmt/sm-common-1.0.0/src
	oe_runmake -e DEST_DIR=${D} BIN_DIR=${bindir} UNIT_DIR=${systemd_system_unitdir} \
			LIB_DIR=${libdir} INC_DIR=${includedir} VER=0 VER_MJR=1 install
}

#FILES_sm-common-dev_append = " var/lib/sm/watchdog/modules/libsm_watchdog_nfs.so"
FILES_sm-common = " \
	${libdir}/libsm_common.so.0 \
	var/lib/sm/watchdog/modules/libsm_watchdog_nfs.so.0 \
	"
FILES_sm-common-dev = " \
	${includedir}/sm_trap_thread.h \
	${includedir}/sm_time.h \
	${includedir}/sm_watchdog_nfs.h \
	${includedir}/sm_debug.h \
	${includedir}/sm_uuid.h \
	${includedir}/sm_timer.h \
	${includedir}/sm_node_stats.h \
	${includedir}/sm_limits.h \
	${includedir}/sm_trap.h \
	${includedir}/sm_watchdog_process.h \
	${includedir}/sm_netlink.h \
	${includedir}/sm_list.h \
	${includedir}/sm_utils.h \
	${includedir}/sm_selobj.h \
	${includedir}/sm_debug_thread.h \
	${includedir}/sm_node_utils.h \
	${includedir}/sm_util_types.h \
	${includedir}/sm_sha512.h \
	${includedir}/sm_thread_health.h \
	${includedir}/sm_eru_db.h \
	${includedir}/sm_types.h \
	${includedir}/sm_hw.h \
	${includedir}/sm_eru_process.h \
	${includedir}/sm_watchdog_module.h \
	"
