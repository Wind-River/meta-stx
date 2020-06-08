
inherit update-alternatives
ALTERNATIVE_PRIORITY = "70"
ALTERNATIVE_${PN} = "drbd.sh"
ALTERNATIVE_LINK_NAME[drbd.sh] = "${datadir}/cluster/drbd.sh"

do_install_append() {
	mv ${D}${datadir}/cluster/drbd.sh ${D}${datadir}/cluster/drbd.sh.${PN}
}
