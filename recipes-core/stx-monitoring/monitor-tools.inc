PACKAGES += " monitor-tools"

# RDEPENDS_monitor-tools += " initscripts-config"

do_configure_append () {
	:
} 

do_compile_append() {
	:
}

do_install_append() {

	cd ${S}/monitor-tools/scripts
	install -m 755 -d ${D}/${bindir}
	# support files ; service and pmon conf
	install -m 644 memtop  ${D}/${bindir}
	install -m 600 schedtop ${D}/${bindir}
	install -m 600 occtop  ${D}/${bindir}
}

FILES_monitor-tools  = " \
	${bindir}/memtop \
	${bindir}/schedtop \
	${bindir}/occtop \
	"
