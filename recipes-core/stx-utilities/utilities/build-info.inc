
PACKAGES += " build-info"

DESCRIPTION_build-info  = "Build Info"
SUMMARY_update-motd  = "Build Info"

SRC_URI += "file://build.info"

do_install_append() {
	install -d ${D}/${sysconfdir}
	install -m 644 ${WORKDIR}/build.info ${D}/${sysconfdir}
	sed -i -e "s/@OS@/${DISTRO}/" \
	       -e "s/@STX_RELEASE@/${STX_REL}/" \
	       -e "s/@STX_ID@/${STX_ID}/" \
	       -e "s/@BUILD_DATE@/${STX_BUILD_DATE}/" \
	       ${D}/${sysconfdir}/build.info

}

do_install[vardepsexclude] += "STX_BUILD_DATE"

FILES_build-info = "${sysconfdir}/build.info"
