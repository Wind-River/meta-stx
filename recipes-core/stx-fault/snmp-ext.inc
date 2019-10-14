PACKAGES += " snmp-ext"

####################
# DEPENDS = " fm-common snmp-audittrail"
####################

do_compile_append() {
	cd ${S}/snmp-ext/sources
	oe_runmake -e PATCH=0 \
		INCLUDES="-I. -I${S}/fm-common/sources" \
		LDFLAGS="${LDFLAGS} -L${S}/fm-common/sources" \
		CCFLAGS="${CXXFLAGS} -fPIC" lib
}

do_install_append () {
	cd ${S}/snmp-ext/sources
	oe_runmake -e DEST_DIR=${D} LIB_DIR=${libdir} MIBVER=0 PATCH=0 install
}

FILES_snmp-ext_append = " \
	${datadir}/snmp/mibs/wrsAlarmMib.mib.txt \
	${datadir}/snmp/mibs/wrsEnterpriseReg.mib.txt \
	${libdir}/libcgtsAgentPlugin.so.1.0.0 \
	${libdir}/libcgtsAgentPlugin.so.1 \
	"
