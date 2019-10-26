PACKAGES += " snmp-audittrail"

##################
# DEPENDS = " fm-common net-snmp"
##################

do_compile_append() {
	cd ${S}/snmp-audittrail/sources
	oe_runmake -e \
		LDFLAGS="${LDFLAGS} -L${S}/fm-common/sources" \
		CCFLAGS="${CXXFLAGS} -fPIC" \
		INCLUDES="-I. -I${S}/fm-common/sources" \
		build
}

do_install_append () {
	cd ${S}/snmp-audittrail/sources
	oe_runmake -e DESTDIR=${D} LIB_DIR=${libdir} MIBVER=0 PATCH=0 install
}

FILES_snmp-audittrail = " \
	${libdir}/libsnmpAuditPlugin.so.1.0.0 \
	${libdir}/libsnmpAuditPlugin.so.1 \
	"

