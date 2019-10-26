DESCRIPTION = "A Puppet module for managing PostgreSQL databases."

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRCREV = "d022a56b28b2174456fc0f6adc51a4b54493afad"
PROTOCOL = "https"
BRANCH = "master"
S = "${WORKDIR}/git"

SRC_URI = " \
	git://github.com/puppetlabs/puppetlabs-postgresql;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH} \
	file://postgresql/0001-Roll-up-TIS-patches.patch \
	file://postgresql/0002-remove-puppetlabs-apt-as-a-requirement.patch \
	"

do_configure() {
	:
}

do_compile() {
	find . -type f -name ".*" -exec rm {} +
	find . -size 0 -exec rm {} +
	find . \( -name "*.pl" -o -name "*.sh"  \) -exec chmod +x {} +
	find . \( -name "*.pp" -o -name "*.py"  \) -exec chmod -x {} +
	find . \( -name "*.rb" -o -name "*.erb" \) -exec chmod -x {} +
	find . \( -name spec -o -name ext \) | xargs rm -rf
}

do_install() {
	cd ${S}
	install -d -m 0755 ${D}/${datadir}/puppet/modules/postgresql
	cp -rp ${S}/* ${D}/${datadir}/puppet/modules/postgresql
}

FILES_${PN} = " ${datadir}"
