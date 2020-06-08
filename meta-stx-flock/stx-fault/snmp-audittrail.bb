require fault-common.inc

DEPENDS += "fm-common net-snmp"

SRC_URI += "file://0001-snmp-audittrail-add-LDFLAGS.patch"

S = "${S_DIR}/snmp-audittrail/sources"

EXTRA_OEMAKE = '-e PATCH=0 \
                INCLUDES="-I. -I${S}/fm-common/sources" \
                LDFLAGS="${LDFLAGS} -shared -L${S}/fm-common/sources" \
                CCFLAGS="${CXXFLAGS} -fPIC" \
                DEST_DIR="${D}" LIB_DIR="${libdir}" \
                MIBVER=0'

do_install() {
	cd ${S}
	oe_runmake -e DESTDIR=${D} BIN_DIR=${bindir} UNIT_DIR=${systemd_system_unitdir} \
		LIB_DIR=${libdir} INC_DIR=${includedir} DATA_DIR=${datadir} install
}
