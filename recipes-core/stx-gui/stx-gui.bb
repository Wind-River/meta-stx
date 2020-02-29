DESCRIPTION = "stx-gui"

PACKAGES = "starlingx-dashboard"

PROTOCOL = "https"
BRANCH = "r/stx.3.0"
SRCREV = "d1c22e49a95f92e91049b96f44e685f46785977c"
S = "${WORKDIR}/git"
PV = "1.0.0"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=1dece7821bf3fd70fe1309eaa37d52a2"



SRC_URI = "git://opendev.org/starlingx/gui.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

inherit distutils python-dir

DEPENDS = "\
	python \
	python-pbr-native \
	"

#RDEPENDS_starlingx-dashboard += " \
#	openstack-dashboard \
#	"

RDEPENDS_starlingx-dashboard_append = " \
	${PYTHON_PN}-cephclient \
	"

do_configure () {
	cd ${S}/starlingx-dashboard/starlingx-dashboard
	distutils_do_configure
}


do_compile () {
	cd ${S}/starlingx-dashboard/starlingx-dashboard
	distutils_do_compile
}


do_install () {
	cd ${S}/starlingx-dashboard/starlingx-dashboard
	distutils_do_install
	#install -d -m 0755 ${D}/${datadir}/openstack-dashboard/openstack_dashboard/enabled/
	#install -d -m 0755 ${D}/${datadir}/openstack-dashboard/openstack_dashboard/themes/starlingx/
	#install -d -m 0755 ${D}/${datadir}/openstack-dashboard/openstack_dashboard/local/local_settings.d
	#install -p -D -m 755 build/lib/starlingx_dashboard/enabled/* \
	#		${D}/${datadir}/openstack-dashboard/openstack_dashboard/enabled/
	#install -p -D -m 755 build/lib/starlingx_dashboard/themes/* \
	#		${D}/${datadir}/openstack-dashboard/openstack_dashboard/themes/starlingx_dashboard
	#install -p -D -m 755 build/lib/starlingx_dashboard/local/local_settings.d/* \
	#	${D}/${datadir}/openstack-dashboard/openstack_dashboard/local/local_settings.d
}

FILES_starlingx-dashboard = " \
	${PYTHON_SITEPACKAGES_DIR} \
	"
