DESCRIPTION = "stx-gui"


STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "596136ecb8d0f07cf9124ed82db66e4031116344"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=1dece7821bf3fd70fe1309eaa37d52a2"



SRC_URI = "git://opendev.org/starlingx/gui.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

inherit externalsrc
EXTERNALSRC_pn-${PN}="${EXTERNALREPO}/stx-gui"
cgcs_doc_deploy = "/opt/deploy/cgcs_doc"

inherit setuptools

DEPENDS = "\
	python \
	python-pbr-native \
	"
# TODO: do we need openstack-dashboard here

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
	# Do we need this?
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
