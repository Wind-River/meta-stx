
PACKAGES += " mtce-guestagent"
PACKAGES += " mtce-guestserver"

require nfv-common.inc

S = "${S_DIR}/mtce-guest/src"

SRC_URI += "file://0001-mtce-guest-Fix-ldflags-usage.patch"

inherit systemd
SYSTEMD_PACKAGES += "mtce-guestagent"
SYSTEMD_PACKAGES += "mtce-guestserver"
SYSTEMD_SERVICE_mtce-guestagent = "guestAgent.service"
SYSTEMD_SERVICE_mtce-guestserver= "guestServer.service"

EXTRA_OEMAKE = '-e MAJOR="1" MINONR="0" \
		INCLUDES=" -I. -I${STAGING_INCDIR}/mtce-common/ -I${STAGING_INCDIR}/mtce-daemon/ " \
		CPPFLAGS="${CXXFLAGS}" LDFLAGS="${LDFLAGS}"'

do_install() {

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

FILE_${PN} = " "

