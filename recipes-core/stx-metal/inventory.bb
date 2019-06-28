DESCRIPTION = "inventory"

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "70609a3d55e5b7d2be82667fc35792505f9013c4"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://opendev.org/starlingx/config.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

require stx-metal.inc
inherit setuptools


DEPENDS = " \
	python \
	python-pbr-native \
	"
RDEPENDS_${PN}_append = " bash"

do_configure () {
	cd ${S}/inventory/inventory
	distutils_do_configure
} 

do_compile() {
	cd ${S}/inventory/inventory
	distutils_do_compile
}

do_install () {
	cd ${S}/inventory/inventory
	distutils_do_install
	
	install -d -m 755 ${D}/${sysconfdir}/goenabled.d
	install -p -D -m 755 etc/inventory/inventory_goenabled_check.sh ${D}/${sysconfdir}/goenabled.d/inventory_goenabled_check.sh

	install -d -m 755 ${D}/${sysconfdir}/inventory
	install -p -D -m 755 etc/inventory/policy.json ${D}/${sysconfdir}/inventory/policy.json

	install -d -m 755 ${D}/${sysconfdir}/motd.d
	install -p -D -m 755 etc/inventory/motd-system ${D}/${sysconfdir}/motd.d/10-system-config

	install -m 755 -p -D scripts/inventory-api ${D}/${libdir}/ocf/resource.d/platform/inventory-api
	install -m 755 -p -D scripts/inventory-conductor ${D}/${libdir}/ocf/resource.d/platform/inventory-conductor

	install -d -m 0755 ${D}/${systemd_system_unitdir}/
	install -m 644 -p -D scripts/inventory-api.service ${D}/${systemd_system_unitdir}/
	install -m 644 -p -D scripts/inventory-conductor.service ${D}/${systemd_system_unitdir}/

	# Install sql migration
	# install -m 644 inventory/db/sqlalchemy/migrate_repo/migrate.cfg ${D}/${libdir}/inventory/db/sqlalchemy/migrate_repo/migrate.cfg

}

#pkg_postinst_ontarget_${PN} () {
# install default config files
#cd ${_builddir}/${name}-${version} && oslo-config-generator --config-file inventory/config-generator.conf --output-file ${_builddir}/${name}-${version}/inventory.conf.sample
#}

FILES_${PN}_append += " ${systemd_unitdir}/* "
