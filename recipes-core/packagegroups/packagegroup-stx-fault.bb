SUMMARY = "StarlingX/cgsc-root/stx/stx-fault"
PR = "r0"

#
# packages which content depend on MACHINE_FEATURES need to be MACHINE_ARCH
#
PACKAGE_ARCH = "${MACHINE_ARCH}"


inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = " \
	packagegroup-stx-fault\
	"

RDEPENDS_packagegroup-stx-fault = " \
	fm-api \
	fm-common \
	fm-doc \
	fm-mgr \
	fm-rest-api \
	python-fmclient \
	snmp-audittrail \
	snmp-ext \
	"
