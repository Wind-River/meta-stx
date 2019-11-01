FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
	file://rootdn-should-not-bypass-ppolicy.patch \
	"
