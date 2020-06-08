
DESCRIPTION = "stx-ha"

PROTOCOL = "https"
BRANCH = "r/stx.3.0"
SRCNAME = "ha"
SRCREV = "a7b7d35b9922a3f2a8462492b7f1958f135a612d"
S = "${WORKDIR}/git"
PV = "1.0.0"


#TODO:
#3b83ef96387f14655fc854ddc3c6bd57  ./LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./service-mgmt-api/sm-api/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./service-mgmt-client/LICENSE
#1dece7821bf3fd70fe1309eaa37d52a2  ./service-mgmt-client/sm-client/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./service-mgmt-tools/sm-tools/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./service-mgmt/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./service-mgmt/sm-common/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./service-mgmt/sm-db/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./service-mgmt/sm/LICENSE

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://opendev.org/starlingx/${SRCNAME}.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH} \
	file://0001-Allow-user-to-define-destination-libdir.patch \
	file://0002-Install-sm-eru-sm-eru-dump-and-sm-eru-watchdog.patch \
	file://0003-pragma-ignore-Wunused-result-errors-with-gcc-8.3.patch \
	file://0004-Cast-size_t-to-int-to-silence-gcc-8.3.patch \
	"

inherit setuptools
inherit pkgconfig
inherit systemd

DISTRO_FEATURES_BACKFILL_CONSIDERED_remove = "sysvinit"

DEPENDS += " \
	stx-fault \
	stx-metal \
	sqlite3 \
	python \
	python-pbr-native \
        glib-2.0 \
	sqlite3 \
	"

require sm-common.inc
require sm-db.inc
require sm.inc
require sm-api.inc
require sm-client.inc
require sm-tools.inc
require stx-ocf-scripts.inc

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

FILES_${PN} = " "
FILES_${PN}-dev += " \
	var/lib/sm/watchdog/modules/libsm_watchdog_nfs.so \
	"
#	var/lib/sm/watchdog/modules/libsm_watchdog_nfs.so.1 \
#	var/lib/sm/watchdog/modules/libsm_watchdog_nfs.so.0 \
#	${libdir}/libsm_common.so.1 \
#	${libdir}/libsm_common.so.0 
