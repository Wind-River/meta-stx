DESCRIPTION = "python-inventoryclient"

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "70609a3d55e5b7d2be82667fc35792505f9013c4"
S = "${WORKDIR}/git"
PV = "19.05"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://opendev.org/starlingx/config.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

require stx-metal.inc
inherit setuptools


DEPENDS = " \
	python \
	python-pbr-native \
	"
#RDEPENDS_${PN}_append = " bash"

do_configure () {
	cd ${S}/python-inventoryclient/inventoryclient
	distutils_do_configure
} 

do_compile() {
	cd ${S}/python-inventoryclient/inventoryclient
	distutils_do_compile
}

do_install () {
	cd ${S}/python-inventoryclient/inventoryclient
	distutils_do_install
	
	install -d -m 755 ${D}/${sysconfdir}/bash_completion.d
	install -p -D -m 664 tools/inventory.bash_completion ${D}/${sysconfdir}/bash_completion.d

}

# pkg_postinst_ontarget_${PN} () { } 
FILES_${PN}_append += " ${systemd_unitdir}/* "
