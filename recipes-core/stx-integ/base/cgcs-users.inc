PACKAGES += " cgcs-users"

RDEPENDS_cgcs-users_append = " bash"

do_configure_append () {
	:
} 

do_compile_append () {
	cd ${S}/base/cgcs-users/cgcs-users-1.0
	oe_runmake -e ibsh
}

do_install_append () {
	cd ${S}/base/cgcs-users/cgcs-users-1.0
	# oe_runmake -e DESTDIR=${D} ibsh_install
	install -d -m 755 ${D}/${sysconfdir}/ibsh/cmds
	install -d -m 755 ${D}/${sysconfdir}/ibsh/xtns
	install -d -m 755 ${D}/${bindir}
	cp globals.cmds ${D}/${sysconfdir}/ibsh/
	cp globals.xtns ${D}/${sysconfdir}/ibsh/
	cp admin.cmds ${D}/${sysconfdir}/ibsh/cmds/
	cp admin.xtns ${D}/${sysconfdir}/ibsh/xtns/
	cp operator.cmds ${D}/${sysconfdir}/ibsh/cmds/
	cp operator.xtns ${D}/${sysconfdir}/ibsh/xtns/
	cp secadmin.cmds ${D}/${sysconfdir}/ibsh/cmds/
	cp secadmin.xtns ${D}/${sysconfdir}/ibsh/xtns/
	install -p -m 755 ibsh ${D}/${bindir}
}

FILES_cgcs-users = " \
	${sysconfdir}/ibsh/globals.cmds \
	${sysconfdir}/ibsh/globals.xtns \
	${sysconfdir}/ibsh/cmds/secadmin.cmds \
	${sysconfdir}/ibsh/cmds/operator.cmds \
	${sysconfdir}/ibsh/cmds/admin.cmds \
	${sysconfdir}/ibsh/xtns/operator.xtns \
	${sysconfdir}/ibsh/xtns/admin.xtns \
	${sysconfdir}/ibsh/xtns/secadmin.xtns \
	${bindir}/ibsh \
	"
