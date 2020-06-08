
DESCRIPTION = "stx-monitoring"

PROTOCOL = "https"
BRANCH = "r/stx.3.0"
SRCNAME = "monitoring"
SRCREV = "8befe1720b02c5e1e3ddf637947643b9b0a0f96f"
S = "${WORKDIR}/git"
PV = "1.0.0"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = " \
	file://collectd-extensions/src/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
	file://influxdb-extensions/src/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
	file://monitor-tools/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
	file://monitor-tools/scripts/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
	file://vm-topology/vm-topology/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
	"

SRC_URI = "git://opendev.org/starlingx/${SRCNAME}.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

DEPENDS += " \
	python \
	python-pbr-native \
	stx-metal \
	stx-fault \
	json-c \
	openssl \
	libevent \
	libgcc \
	"

require collectd-extensions.inc
require influxdb-extensions.inc
require monitor-tools.inc
require vm-topology.inc

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
