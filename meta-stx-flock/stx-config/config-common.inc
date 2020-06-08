
DESCRIPTION = "stx-config"

PROTOCOL = "https"
BRANCH = "r/stx.3.0"
SRCREV = "b51e4ef738e0020f11f164fd3f86399872caf3c6"
S_DIR = "${WORKDIR}/git"
PV = "1.0.0"

SRC_URI = "git://opendev.org/starlingx/config.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

DEPENDS = "\
	puppet \
	python \
	python-pbr-native \
	"

# Other packages depend on tsconfig build it first
#require tsconfig.inc
#require config-gate.inc
#require controllerconfig.inc
#require storageconfig.inc
#require cgts-client.inc
#require sysinv.inc
#require sysinv-agent.inc
#require workerconfig.inc

DISTRO_FEATURES_BACKFILL_CONSIDERED_remove = "sysvinit"
