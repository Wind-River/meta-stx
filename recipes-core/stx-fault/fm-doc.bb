DESCRIPTION = "fm-doc"

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "70609a3d55e5b7d2be82667fc35792505f9013c4"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://opendev.org/starlingx/config.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

inherit externalsrc
EXTERNALSRC_pn-${PN}="${EXTERNALREPO}/stx-fault"

require stx-fault.inc

# DEPENDS = " "
# RDEPENDS_${PN} += " bash"

do_configure () {
	:
} 

do_compile() {
	:
}

do_install () {
	cd ${S}/fm-doc/fm_doc/
	install -d -m 755 ${D}/${cgcs_doc_deploy}
	install -d -m 755 ${D}/${sysconfdir}/fm
	install -m 744 events.yaml ${D}/${sysconfdir}/fm/
	install -m 644 events.yaml ${D}/${cgcs_doc_deploy}
	install -m 755 checkEventYaml ${D}/${cgcs_doc_deploy}
	install -m 644 parseEventYaml.py ${D}/${cgcs_doc_deploy}
	install -m 644 check_missing_alarms.py ${D}/${cgcs_doc_deploy}
}

FILES_${PN}_append += " ${cgcs_doc_deploy} "

