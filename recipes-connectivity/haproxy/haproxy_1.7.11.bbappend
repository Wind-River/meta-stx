FILESEXTRAPATHS_prepend := "${THISDIR}/files:${THISDIR}/haproxy-1.7.11:"
SRC_URI += " \
	file://haproxy.service \
	file://haproxy.cfg \
	file://haproxy-${PV}-tpm-support.patch \
	"

do_install_append() {

	install -D -m 0644 ${WORKDIR}/haproxy.service ${D}${systemd_system_unitdir}/haproxy.service
	install -D -m 0644 ${WORKDIR}/haproxy.cfg ${D}${sysconfdir}/haproxy/haproxy.cfg
}

FILES_${PN}_append = " \
	${systemd_system_unitdir}/haproxy.service \
	${sysconfdir}/haproxy/haproxy.cfg \
	"
