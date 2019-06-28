DESCRIPTION = "distributedcloud"



STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "fd498c843c95519acf6296b9ddcf4f684d92a9be"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=1dece7821bf3fd70fe1309eaa37d52a2"



SRC_URI = "git://opendev.org/starlingx/distcloud.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

DEPENDS = " \
	python-crypto \
	python-cryptography \
	python2-devel \
	python-eventlet \
	python-setuptools \
	python2-pip \
	python2-wheel \
	python-jsonschema >= 2.0.0 \
	python-keyring \
	python-keystonemiddleware \
	python-keystoneauth1 >= 3.1.0 \
	python-netaddr \
	python-oslo-concurrency \
	python-oslo-config \
	python-oslo-context \
	python-oslo-db \
	python-oslo-i18n \
	python-oslo-log \
	python-oslo-messaging \
	python-oslo-middleware \
	python-oslo-policy \
	python-oslo-rootwrap \
	python-oslo-serialization \
	python-oslo-service \
	python-oslo-utils \
	python-oslo-versionedobjects \
	python-pbr >= 1.8 \
	python-pecan >= 1.0.0 \
	python-routes >= 1.12.3 \
	python-sphinx \
	python-sphinxcontrib-httpdomain \
	pyOpenSSL \
	systemd \
	python-babel \
	"

#AUTOTOOLS_SCRIPT_PATH = ""
#inherit autotools
#inherit pkgconfig
#inherit python-dir
#EXTRA_OECONF = ""
#do_configure_append () { }
#do_install_append() { }
#pkg_postinst_ontarget_${PN} () { }
