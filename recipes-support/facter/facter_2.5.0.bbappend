FILESEXTRAPATHS_append := ":${THISDIR}/files:"

SRC_URI += " \
	file://add_poky_lsbdistid.diff \
	file://0001-ps.patch \
	file://0002-personality.patch \
	file://0003-centos_remove-net-commands-that-can-timeout.patch;striplevel=2 \
	file://0004-centos_fix-ipv6-regex.patch \
	file://0005-Hardcode-ipaddress-fact-to-localhost.patch \
	"

