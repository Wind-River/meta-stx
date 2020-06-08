
require nfv-common.inc

S = "${S_DIR}/nfv/nfv-client"

inherit setuptools


do_install_append() {
	
	install -d -m 755 ${D}/${sysconfdir}/bash_completion.d
	install -m 444 scripts/sw-manager.completion ${D}/${sysconfdir}/bash_completion.d/sw-manager

}

FILES_${PN}_append = " \
	${sysconfdir}/bash_completion.d/sw-manager \
	"
