DESCRIPTION = "sm"

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "70609a3d55e5b7d2be82667fc35792505f9013c4"
# S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://opendev.org/starlingx/config.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

require stx-ha.inc

inherit pkgconfig

DEPENDS += " \
	sm-common \
	sm-db \
	fm-api \
	mtce \
	sqlite3 \
	"

#TODO: Shouldn't have to do this
LDFLAGS_remove = "-Wl,--as-needed"
do_configure () {
	:
} 

do_compile() {
	cd ${S}/service-mgmt/sm-1.0.0/src
	oe_runmake -e -j1 VER=0 VER_MJR=1 \
		INCLUDES="-I. $(pkg-config --cflags glib-2.0)" \
		CCFLAGS="${CXXFLAGS} -std=c++11" LDFLAGS="${LDFLAGS} -rdynamic"
	
}

do_install () {
	cd ${S}/service-mgmt/sm-1.0.0/src
	oe_runmake -e DEST_DIR=${D} BIN_DIR=${bindir} UNIT_DIR=${systemd_system_unitdir} \
			LIB_DIR=${libdir} INC_DIR=${includedir} VER=0 VER_MJR=1 install
}

pkg_postinst_ontarget_${PN} () {
	/usr/bin/systemctl enable sm.service >/dev/null 2>&1
	/usr/bin/systemctl enable sm-shutdown.service >/dev/null 2>&1
}
