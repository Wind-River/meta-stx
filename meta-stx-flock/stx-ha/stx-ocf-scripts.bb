
S = "${S_DIR}/stx-ocf-scripts/src/ocf"

require ha-common.inc

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${S_DIR}/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

RDEPENDS_${PN} = " \
	bash \
	openstack-ras \
	"

do_install_append () {
	install -d -m 755 ${D}/usr/lib/ocf/resource.d/openstack
	install -p -D -m 755 $(find . -type f) ${D}/usr/lib/ocf/resource.d/openstack/ 
}

FILES_${PN}_append = " \
	${libdir}/ocf/resource.d/openstack/ \
	"
