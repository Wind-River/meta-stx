
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " \
	file://${BPN}/0001-Roll-up-TIS-patches.patch \
	file://${BPN}/0002-remove-puppetlabs-apt-as-a-requirement.patch \
	file://${BPN}/0003-puppetlabs-postgresql-account-for-naming-diffs.patch \
	file://${BPN}/0004-poky-postgresql-updates.patch \
	file://${BPN}/0005-puppetlabs-postgresql-poky.patch \
	file://${BPN}/0006-adjust_path-remove-refs-to-local-bin.patch \
	file://${BPN}/postgresql.service \
	"

#	file://${PN}/0004-postgresql-service-restart-with-systemctl.patch 

RDEPENDS_${PN}_append = " \
	postgresql \
	postgresql-contrib \
	postgresql-client \
	postgresql-timezone \
	postgresql-plperl \
	postgresql-plpython \
	"
#postgresql-dev
#postgresql-pltcl
#postgresql-setup


do_install_append() {
	install -d -m0755 ${D}/usr/lib/systemd/system
	install -m0644 ${WORKDIR}/${PN}/postgresql.service ${D}/usr/lib/systemd/system
}

FILES_${PN}_append = " /usr/lib/systemd/system/postgresql.service"

inherit openssl10
