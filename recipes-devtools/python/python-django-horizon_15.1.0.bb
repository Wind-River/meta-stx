DESCRIPTION = "Simple database sharding (horizontal partitioning) library for Django applications."
HOMEPAGE = "http://www.freedesktop.org/wiki/Software/HarfBuzz"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1dece7821bf3fd70fe1309eaa37d52a2"

DEPENDS += " python"
inherit setuptools distutils pkgconfig

SRC_URI[md5sum] = "21c32ba58806b351ede4eca6804e6d3e"
SRC_URI[sha256sum] = "25cf663f8f9a0233edbd5ba322acc28805fca684df28290c3e62a79abf6724e9  "

SRC_URI = " \
	https://tarballs.openstack.org/horizon/horizon-15.1.0.tar.gz \
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
