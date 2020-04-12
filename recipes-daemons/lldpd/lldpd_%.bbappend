FILESEXTRAPATHS_prepend := "${THISDIR}/patches:${THISDIR}/files:"

DISTRO_FEATURES_BACKFILL_CONSIDERED_remove = "sysvinit"

SRC_URI += " \
	file://0001-lldpd-client-add-show-interfaces-cmd-from-upstream.patch \
	file://0002-Clear-station-bit-if-any-other-capability-is-enabled.patch \
	file://i40e-lldp-configure.sh \
	"

# TODO: 
# Check Yocto handling of i40e firmware?
# See i40e-lldp-configure.sh and lldpd-i40e-disable.patch

# file://lldpd.init 
# lldpd-create-run-dir.patch

do_install_append() {
	cd ${S}
	install -d -m 0755 ${D}/${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/i40e-lldp-configure.sh ${D}/${sysconfdir}/init.d/
}

FILES_${PN}_append = " \
	${sysconfdir}/init.d/i40e-lldp-configure.sh \
	"

RDEPENDS_${PN} += "bash"
