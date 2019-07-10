DESCRIPTION = "stx-ha"

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "73b77366b32eac3be1caaab2e44a1f02ee5c1980"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://opendev.org/starlingx/ha.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"


inherit setuptools
inherit pkgconfig

#	sm-common sm-db 
#	mtce 

DEPENDS += " \
	stx-fault \
	sqlite3 \
	python \
	python-pbr-native \
        glib-2.0 \
	sqlite3 \
	"

require sm-common.inc
#require sm-api.inc
#require sm-client.inc
#require sm-common.inc
#require sm-db.inc
#require sm-tools.inc
#require sm.inc

#TODO: Shouldn't have to do this
LDFLAGS_remove = "-Wl,--as-needed"
do_configure () {
	:
} 

do_compile() {
	:	
}

do_install () {
	:
}

pkg_postinst_ontarget_${PN} () {
}

FILES_${PN} = " "
