DESCRIPTION = "stx-fault"

INSANE_SKIP_${PN} = "ldflags"


STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "eeb2dee9023347f73d7153325190550a5dc87e7a"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://opendev.org/starlingx/fault.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH} \
	file://0001-Honor-the-build-system-LDFLAGS.patch \
	file://0001-Use-build-systems-LDFLAGS.patch \
	file://0001-snmp-ext-use-build-systems-LDFLAGS.patch \
	"

DEPENDS = " \
	util-linux \
	postgresql \
	python \
	python-pbr-native \
	python-six \
	python-oslo.i18n \
	python-oslo.utils \
	python-requests \
	bash \
	net-snmp \
"

RDEPENDS_${PN} += " bash"

inherit setuptools
cgcs_doc_deploy = "/opt/deploy/cgcs_doc"

require fm-common.inc
require fm-mgr.inc
require fm-api.inc
require fm-doc.inc
require fm-rest-api.inc
require python-fmclient.inc
require snmp-audittrail.inc
require snmp-ext.inc

do_configure() {
	:
}

do_compile() {
	:
}

do_install() {
	:
}

FILES_${PN} = " "
