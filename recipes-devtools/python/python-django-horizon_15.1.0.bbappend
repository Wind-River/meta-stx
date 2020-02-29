FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " \
	file://${PN}/guni_config.py \
	file://${PN}/horizon-assets-compress \
	file://${PN}/horizon-clearsessions \
	file://${PN}/horizon.init \
	file://${PN}/horizon.logrotate \
	file://${PN}/horizon-patching-restart \
	file://${PN}/openstack-dashboard-httpd-2.4.conf \
	file://${PN}/openstack-dashboard-httpd-logging.conf \
	file://${PN}/python-django-horizon-logrotate.conf \
	file://${PN}/python-django-horizon-systemd.conf \
	"
