DESCRIPTION = "rabbitmq-server-config"

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "70609a3d55e5b7d2be82667fc35792505f9013c4"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://opendev.org/starlingx/config.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

require stx-upstream.inc


RDEPENDS_${PN}_append = " bash"

do_configure () {
	:
} 

do_compile() {
	:
}

do_install () {
	cd ${S}/openstack/rabbitmq-server-config/files

	install -d ${D}/${libdir}/ocf/resource.d/rabbitmq
	install -d ${D}/${systemd_system_unitdir}/
	install -d ${D}/${datadir}/starlingx

	install -m 0755 rabbitmq-server.ocf              ${D}/${libdir}/ocf/resource.d/rabbitmq/stx.rabbitmq-server
	install -m 0644 rabbitmq-server.service.example  ${D}${systemd_system_unitdir}/rabbitmq-server.service
	install -m 0644 rabbitmq-server.logrotate        ${D}${datadir}/starlingx/stx.rabbitmq-server.logrotate


}

#pkg_postinst_ontarget_${PN} () { }

FILES_${PN}_append += " \
	${libdir} \
	${systemd_system_unitdir} \
	${datadir} \
	"
