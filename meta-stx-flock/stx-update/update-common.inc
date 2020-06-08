
DESCRIPTION = "stx-update"

PROTOCOL = "https"
BRANCH = "r/stx.3.0"
SRCREV = "2542c5539bab060830009d02cbb257cc8bf4a376"
S_DIR = "${WORKDIR}/git"
PV = "1.0.0"


SRC_URI = "git://opendev.org/starlingx/update.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

DEPENDS = " \
	python \
	python-pbr-native \
	"
