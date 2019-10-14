PACKAGES += " inventory"

#DEPENDS = " \
#	python \
#	python-pbr-native \
#	"

RDEPENDS_inventory += " bash"

do_configure_prepend () {
	cd ${S}/inventory/inventory
	distutils_do_configure
} 

do_compile_prepend () {
	cd ${S}/inventory/inventory
	distutils_do_compile
}

do_install_prepend () {
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

#pkg_postinst_ontarget_inventory () {
# install default config files
#cd ${_builddir}/${name}-${version} && oslo-config-generator --config-file inventory/config-generator.conf --output-file ${_builddir}/${name}-${version}/inventory.conf.sample
#}

FILES_inventory = " \
	${systemd_system_unitdir}/inventory-api.service \
	${systemd_system_unitdir}/inventory-conductor.service \
	${bindir}/inventory-api \
	${bindir}/inventory-conductor \
	${bindir}/inventory-dnsmasq-lease-update \
	${bindir}/inventory-agent \
	${bindir}/inventory-dbsync \
	${libdir}/ocf/resource.d/platform/inventory-api \
	${libdir}/ocf/resource.d/platform/inventory-conductor \
	${libdir}/python2.7/site-packages/inventory*.egg-info/ \
	${libdir}/python2.7/site-packages/inventory/ \
	${sysconfdir}/goenabled.d/inventory_goenabled_check.sh \
	${sysconfdir}/motd.d/10-system-config \
	${sysconfdir}/inventory/policy.json \
	"
