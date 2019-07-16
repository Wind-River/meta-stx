DESCRIPTION = " puppet-boolean"

STABLE = "master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "22b726dd78b0a60a224cc7054aebbf28e9306f62"
S = "${WORKDIR}/git"

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=8cbd44f4ffbc81632df22fdd4ae87811"

SRC_URI = "git://github.com/voxpupuli/puppet-boolean.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

do_configure () {
	:
} 

do_compile() {
	:
}

do_install () {
	cd ${S}/
	install -d -m 0755 ${D}/${datadir}/puppet/modules/boolean
	cp -R * ${D}/${datadir}/puppet/modules/boolean/
	# chown -R root:root ${D}/
}

FILES_${PN}_append += " \
	${datadir}/puppet/modules/boolean/ \
	"
