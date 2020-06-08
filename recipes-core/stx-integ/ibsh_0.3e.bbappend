
PROTOCOL = "https"
BRANCH = "r/stx.3.0"
SRCNAME = "integ"
SRCREV = "0bf4b546df8c7fdec8cfc6cb6f71b9609ee54306"

SRC_URI += "git://opendev.org/starlingx/${SRCNAME}.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH};destsuffix=stx-patches;subpath=base/cgcs-users/cgcs-users-1.0"

do_patch_append () {
    bb.build.exec_func('stx_do_patch', d)
}

stx_do_patch () {
	cd ${S}
	patch -p1 < ${WORKDIR}/stx-patches/ibsh-0.3e.patch
	patch -p1 < ${WORKDIR}/stx-patches/ibsh-0.3e-cgcs.patch
	patch -p1 < ${WORKDIR}/stx-patches/ibsh-0.3e-cgcs-copyright.patch
}

do_install_append() {
	cp ${WORKDIR}/stx-patches/admin.cmds ${D}/${sysconfdir}/ibsh/cmds/
	cp ${WORKDIR}/stx-patches/admin.xtns ${D}/${sysconfdir}/ibsh/xtns/
	cp ${WORKDIR}/stx-patches/operator.cmds ${D}/${sysconfdir}/ibsh/cmds/
	cp ${WORKDIR}/stx-patches/operator.xtns ${D}/${sysconfdir}/ibsh/xtns/
	cp ${WORKDIR}/stx-patches/secadmin.cmds ${D}/${sysconfdir}/ibsh/cmds/
	cp ${WORKDIR}/stx-patches/secadmin.xtns ${D}/${sysconfdir}/ibsh/xtns/
}

