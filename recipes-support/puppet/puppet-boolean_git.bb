DESCRIPTION = "Logical Resource Management (LVM) features for Puppet."

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8cbd44f4ffbc81632df22fdd4ae87811"

SRCREV = "22b726dd78b0a60a224cc7054aebbf28e9306f62"
PROTOCOL = "https"
BRANCH = "master"
S = "${WORKDIR}/git"

SRC_URI = "git://github.com/adrienthebo/puppet-boolean;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH} \
	"

do_configure() {
	:
}

do_compile() {
	cd ${S}
	find . -type f -name ".*" -exec rm {} +
	find . -size 0 -exec rm {} +
	find . \( -name "*.pl" -o -name "*.sh"  \) -exec chmod +x {} +
	find . \( -name "*.pp" -o -name "*.py"  \) -exec chmod -x {} +
	find . \( -name "*.rb" -o -name "*.erb" \) -exec chmod -x {} +
	find . \( -name spec -o -name ext \) | xargs rm -rf
}

do_install() {
	cd ${S}
	install -d -m 0755 ${D}/${datadir}/puppet/modules/boolean
	cp -R ${S}/* ${D}/${datadir}/puppet/modules/boolean/
}

FILES_${PN} = " ${datadir}"
