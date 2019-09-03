PACKAGES += " puppet-mtce"
RDEPENDS_puppet-mtce += " puppet"

do_install_append () {
	cd ${S}/puppet-modules-wrs/puppet-mtce/src
	oe_runmake -e MODULEDIR=${D}/${datadir}/puppet/modules install
}

FILES_puppet-mtce = "\
	${datadir}/puppet/modules/mtce \
	"
