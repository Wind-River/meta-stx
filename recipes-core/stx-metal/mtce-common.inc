PACKAGES += " mtce-common"

do_configure_prepend () {
	:
} 

do_compile_prepend () {
	cd ${S}/mtce-common/src/
	oe_runmake clean
	oe_runmake -e build VER=0 VER_MJR=1 \
		CCFLAGS="${CXXFLAGS} -DBUILDINFO=\"\\\"\$\$(date)\\\"\"" 
}

do_install_prepend () {

	cd ${S}/mtce-common/src/daemon
	install -m 755 -d ${D}/${libdir}
	install -m 755 -d ${D}/${includedir}/mtce-daemon
	install -m 644 -p -D libdaemon.a ${D}/${libdir}
# TODO: 
# See next TODO about mtce-common. Same is true for mtce-daemon. 
# We need pkgconfig files and then rewrite the packages Makefiles.
#
	install -m 644 -p -D daemon_ini.h ${D}/${includedir}
	install -m 644 -p -D daemon_common.h ${D}/${includedir}
	install -m 644 -p -D daemon_option.h ${D}/${includedir}

	cd ${S}/mtce-common/src/common
	install -m 755 -d ${D}/${includedir}/mtce-common
	install -m 644 -p -D libcommon.a ${D}/${libdir}
	install -m 644 -p -D libthreadUtil.a ${D}/${libdir}
	install -m 644 -p -D libipmiUtil.a ${D}/${libdir}
	install -m 644 -p -D libpingUtil.a ${D}/${libdir}
	install -m 644 -p -D libnodeBase.a ${D}/${libdir}
	install -m 644 -p -D libregexUtil.a ${D}/${libdir}
	install -m 644 -p -D libhostUtil.a ${D}/${libdir}

# TODO: 
# These files need to go into mtce-common. But the mtce package 
# includes these files as in include "nodeBase.h" and not 
# mtce-common/nodeBase.h. 
# Here rather than changing every source file, will install into 
# ${includedir} instead of ${includedir}/mtce-common to finish the 
# initial port. 
# The correct fix is to generate pkg_config files which then the 
# Makefiles can query for the c and ld flags.
# 

	install -m 644 -p -D fitCodes.h ${D}/${includedir}/
	install -m 644 -p -D logMacros.h ${D}/${includedir}/
	install -m 644 -p -D returnCodes.h ${D}/${includedir}/
	install -m 644 -p -D nodeTimers.h ${D}/${includedir}/
	install -m 644 -p -D hostClass.h ${D}/${includedir}/
	install -m 644 -p -D httpUtil.h ${D}/${includedir}/
	install -m 644 -p -D jsonUtil.h ${D}/${includedir}/
	install -m 644 -p -D msgClass.h ${D}/${includedir}/
	install -m 644 -p -D nodeBase.h ${D}/${includedir}/
	install -m 644 -p -D nodeEvent.h ${D}/${includedir}/
	install -m 644 -p -D nodeMacro.h ${D}/${includedir}/
	install -m 644 -p -D nodeUtil.h ${D}/${includedir}/
	install -m 644 -p -D timeUtil.h ${D}/${includedir}/
	install -m 644 -p -D alarmUtil.h ${D}/${includedir}/
	install -m 644 -p -D hostUtil.h ${D}/${includedir}/
	install -m 644 -p -D ipmiUtil.h ${D}/${includedir}/
	install -m 644 -p -D nlEvent.h ${D}/${includedir}/
	install -m 644 -p -D pingUtil.h ${D}/${includedir}/
	install -m 644 -p -D regexUtil.h ${D}/${includedir}/
	install -m 644 -p -D threadUtil.h ${D}/${includedir}/
	install -m 644 -p -D tokenUtil.h ${D}/${includedir}/
	install -m 644 -p -D secretUtil.h ${D}/${includedir}/
}

pkg_postinst_ontarget_mtce-common_prepend () { 
}

# Headers, and static devs go into stx-mtce-dev 
# and stx-mtce-staticdev packages respecitively
FILES_mtce-common = " "
