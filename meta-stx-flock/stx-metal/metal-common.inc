
DESCRIPTION = "stx-metal"

STABLE = "starlingx/master"
PROTOCOL = "https"
BRANCH = "r/stx.3.0"
SRCREV = "be3cf4eeb50eef55910cf9c73ea47c168005ad64"
S_DIR = "${WORKDIR}/git"
PV = "1.0.0"

SRC_URI = "git://opendev.org/starlingx/metal.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

#		file://0001-mtce-compute-dont-install-empty-directory-unless-nee.patch \
#		file://0001-mtce-control-dont-install-empty-directory-unless-nee.patch \
#		file://0001-mtce-storage-dont-install-empty-directory-unless-nee.patch \
#		"
