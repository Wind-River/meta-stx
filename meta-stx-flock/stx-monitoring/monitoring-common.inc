
DESCRIPTION = "stx-monitoring"

PROTOCOL = "https"
BRANCH = "r/stx.3.0"
SRCNAME = "monitoring"
SRCREV = "8befe1720b02c5e1e3ddf637947643b9b0a0f96f"
S_DIR = "${WORKDIR}/git"
PV = "1.0.0"

SRC_URI = "git://opendev.org/starlingx/${SRCNAME}.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

DEPENDS += " \
	python \
	python-pbr-native \
	mtce \
	fm-common \
	json-c \
	openssl \
	libevent \
	libgcc \
	"
