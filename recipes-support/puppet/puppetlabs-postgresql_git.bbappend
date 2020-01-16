FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " \
	file://${PN}/0001-Roll-up-TIS-patches.patch \
	file://${PN}/0002-remove-puppetlabs-apt-as-a-requirement.patch \
	file://${PN}/0001-puppetlabs-postgresql-account-for-naming-diffs.patch \
	file://${PN}/postgresql.service \
	"

#	file://${PN}/0004-postgresql-service-restart-with-systemctl.patch 

do_install_append() {
	install -d -m0755 ${D}/usr/lib/systemd/system
	install -m0644 ${WORKDIR}/${PN}/postgresql.service ${D}/usr/lib/systemd/system
}

FILES_${PN}_append = " /usr/lib/systemd/system/postgresql.service"
