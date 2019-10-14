PACKAGES += " stx-ocf-scripts"

RDEPENDS_stx-ocf-scripts = " bash"

do_configure_prepend () {
	:
} 

do_compile_prepend () {
	:
}

do_install_prepend () {
	cd ${S}/openstack/stx-ocf-scripts/src/

	install -d ${D}/${libdir}/ocf/resource.d/openstack
	install -p -D -m 0755 ocf/* ${D}/${libdir}/ocf/resource.d/openstack/


}

#pkg_postinst_ontarget_${PN} () { }

FILES_stx-ocf-scripts = " \
	${libdir}/ocf/resource.d/openstack/barbican-worker \
	${libdir}/ocf/resource.d/openstack/dbmon \
	${libdir}/ocf/resource.d/openstack/aodh-evaluator \
	${libdir}/ocf/resource.d/openstack/murano-api \
	${libdir}/ocf/resource.d/openstack/barbican-keystone-listener \
	${libdir}/ocf/resource.d/openstack/heat-api-cloudwatch \
	${libdir}/ocf/resource.d/openstack/heat-api-cfn \
	${libdir}/ocf/resource.d/openstack/ceilometer-agent-notification \
	${libdir}/ocf/resource.d/openstack/validation \
	${libdir}/ocf/resource.d/openstack/nova-compute \
	${libdir}/ocf/resource.d/openstack/aodh-listener \
	${libdir}/ocf/resource.d/openstack/nova-placement-api \
	${libdir}/ocf/resource.d/openstack/magnum-api \
	${libdir}/ocf/resource.d/openstack/panko-api \
	${libdir}/ocf/resource.d/openstack/aodh-notifier \
	${libdir}/ocf/resource.d/openstack/heat-api \
	${libdir}/ocf/resource.d/openstack/nova-serialproxy \
	${libdir}/ocf/resource.d/openstack/magnum-conductor \
	${libdir}/ocf/resource.d/openstack/murano-engine \
	${libdir}/ocf/resource.d/openstack/barbican-api \
	${libdir}/ocf/resource.d/openstack/ironic-api \
	${libdir}/ocf/resource.d/openstack/aodh-api \
	${libdir}/ocf/resource.d/openstack/ironic-conductor \
	${libdir}/ocf/resource.d/openstack/cinder-backup \
	${libdir}/ocf/resource.d/openstack/nova-conductor \
	"
