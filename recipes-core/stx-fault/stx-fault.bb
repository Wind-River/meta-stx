
DESCRIPTION = "stx-fault"

PROTOCOL = "https"
BRANCH = "r/stx.3.0"
SRCREV = "2025f585c5b92890c8cb32c480b0151c7c1cb545"
S = "${WORKDIR}/git"
PV = "1.0.0"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = " \
	git://opendev.org/starlingx/fault.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH} \
	file://0001-Honor-the-build-system-LDFLAGS.patch \
	file://0001-Use-build-systems-LDFLAGS.patch \
	file://0001-snmp-ext-use-build-systems-LDFLAGS.patch \
	"

inherit setuptools
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

cgcs_doc_deploy = "/opt/deploy/cgcs_doc"

require fm-common.inc
require fm-api.inc
require fm-doc.inc
require fm-mgr.inc
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
