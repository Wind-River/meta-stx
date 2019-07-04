DESCRIPTION = "mtce-guest"

# TODO: 
# Really need to fix the package;s Makefile

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "70609a3d55e5b7d2be82667fc35792505f9013c4"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://opendev.org/starlingx/config.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

require stx-nfv.inc

DEPENDS = " \
	mtce-common \
	mtce \
	json-c \
	"

RDEPENDS_${PN}_append = " bash"

do_configure () {
	:
} 

do_compile() {
	cd ${S}/mtce-guest/src/
	oe_runmake -e VER=1 VER_MJR=1 INCLUDES=" -I. " \
		CPPFLAGS="${CXXFLAGS}" LDFLAGS="${LDFLAGS}" build
}

do_install () {

	cd ${S}/mtce-guest/src/
	make install DESTDIR=${D} PREFIX=${D}/usr/ \
		       SYSCONFDIR=${D}/${sysconfdir} \
		            LOCALBINDIR=${D}/${bindir} \
			         UNITDIR=${D}/${systemd_system_unitdir}
}

pkg_postinst_ontarget_${PN} () { 
	/bin/systemctl enable guestServer.service
}

FILES_${PN}_append = " \
	systemd_system_unitdir} \
	${libdir} \
	${baselib} \
	run \
"
