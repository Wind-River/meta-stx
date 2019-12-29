FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
	file://rabbitmq-server.service \
	file://rabbitmq-server-fails-with-home-not-set.patch \
	"


SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "rabbitmq-server.service"
SYSTEMD_AUTO_ENABLE_${PN} = "enable"

do_install_append() {
	install -m 0644 ${WORKDIR}/rabbitmq-server.service ${D}${systemd_system_unitdir}
}

# Fails to build and needs multiple attempts if PARALLEL_MAKE > 20
# Work around for now: set PARALLEL_MAKE for this pacakge to 7
# Actual fix is in the package itself

EXTRA_OEMAKE_remove = " -j ${PARALLEL_MAKE}"
EXTRA_OEMAKE = " -j1"
