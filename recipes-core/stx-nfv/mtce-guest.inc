PACKAGES += " mtce-guestagent"
PACKAGES += " mtce-guestserver"

# RDEPENDS_mtce-guestagent_append = " "
# RDEPENDS_mtce-guestAgent_append = " "

#RDEPENDS_mtce-guestServer_append = " \
#	bash \
#	libstdc++ \
#	"

do_configure_prepend () {
	:
} 

do_compile_prepend () {
	cd ${S}/mtce-guest/src/
	oe_runmake -e VER=1 VER_MJR=1 INCLUDES=" -I. " \
		CPPFLAGS="${CXXFLAGS}" LDFLAGS="${LDFLAGS}" build
}

do_install_prepend () {

	cd ${S}/mtce-guest/src/
	oe_runmake -e install DESTDIR=${D} PREFIX=${D}/usr/ \
		       SYSCONFDIR=${D}/${sysconfdir} \
		            LOCALBINDIR=${D}/${bindir} \
			         UNITDIR=${D}/${systemd_system_unitdir}
	rm -rf ${D}/var
	rm -rf ${D}/var/run
}

FILES_mtce-guestserver = " \
	${sysconfdir}/mtc/tmp \
	${sysconfdir}/mtc/guestServer.ini \
	${sysconfdir}/pmon.d/guestServer.conf \
	${sysconfdir}/logrotate.d/guestServer.logrotate \
	${systemd_system_unitdir}/guestServer.service \
	${sysconfdir}/init.d/guestServer \
	${bindir}/guestServer \
	"

FILES_mtce-guestagent = " \ 
	${sysconfdir}/mtc/tmp \
	${sysconfdir}/mtc/guestAgent.ini \
	${systemd_system_unitdir}/guestAgent.service \
	${sysconfdir}/logrotate.d/guestAgent.logrotate \
	${sysconfdir}/init.d/guestAgent \
	${libdir}/ocf/resource.d/platform/guestAgent \
	${bindir}/guestAgent \
" 
