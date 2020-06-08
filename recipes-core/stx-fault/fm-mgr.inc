
PACKAGES += " fm-mgr"

###################
# DEPENDS = " fm-common"
###################

do_compile_append() {
	cd ${S}/fm-mgr/sources/
	oe_runmake -e \
		LDFLAGS="${LDFLAGS} -L${S}/fm-common/sources" \
		CCFLAGS="${CXXFLAGS}" \
		INCLUDES="-I. -I${S}/fm-common/sources" \
		build
}

do_install_append () {
	cd ${S}/fm-mgr/sources/
	install -d -m0755 ${D}/${systemd_system_unitdir} 
	oe_runmake -e DESTDIR=${D} BINDIR=${bindir} \
		LIBDIR=${libdir} UNITDIR=${systemd_system_unitdir} \
			SYSCONFDIR=${sysconfdir} \
			install
	rm -rf ${D}/usr/lib/systemd

	# fix the path for init scripts
	sed -i -e 's|rc.d/||' ${D}/${systemd_system_unitdir}/*.service

	# fix the path for binaries
	sed -i -e 's|/usr/local/bin/|${bindir}/|' ${D}${sysconfdir}/init.d/fminit
}

FILES_fm-mgr = "  \
	${bindir}/fmManager \
	${systemd_system_unitdir}/fminit.service \
	${sysconfdir}/init.d/fminit \
	${sysconfdir}/logrotate.d/fm.logrotate \
	"

inherit useradd

USERADD_PACKAGES = "fm-mgr"
USERADD_PARAM_fm-mgr = "-r -g fm -u 195 -d /var/lib/fm -s /sbin/nologin -c 'fm-mgr' fm"
GROUPADD_PARAM_fm-mgr = "-r -g 195 fm"

RDEPENDS_fm-mgr += "net-snmp-server-snmpd"
