
DESCRIPTION = "stx-metal"

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "r/stx.3.0"
SRCREV = "be3cf4eeb50eef55910cf9c73ea47c168005ad64"
S_DIR = "${WORKDIR}/git"
PV = "1.0.0"

SRC_URI = "git://opendev.org/starlingx/metal.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

DISTRO_FEATURES_BACKFILL_CONSIDERED_remove = "sysvinit"
