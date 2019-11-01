SUMMARY = "A Puppet module to manage non volatile network and route configuration."

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b43880e14353cbc12cf3981fb93a0944"


PV = "0.5.0"
SRCREV = "7deacd5fdc22c0543455878a8d1872f2f5417c1d"
PROTOCOL = "https"
BRANCH = "master"
S = "${WORKDIR}/git"

SRC_URI = " \
	git://github.com/voxpupuli/puppet-network;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH} \
	file://network/Add-gemspec.patch \
	file://network/metadata.json.patch \
	file://network/puppet-network-Kilo-quilt-changes.patch;striplevel=5 \
	file://network/puppet-network-support-ipv6.patch;striplevel=5 \
	file://network/Don-t-write-absent-to-redhat-route-files-and-test-fo.patch;striplevel=5 \
	file://network/fix-absent-options.patch;striplevel=5 \
	file://network/permit-inservice-update-of-static-routes.patch;striplevel=5 \
	file://network/ipv6-static-route-support.patch;striplevel=5 \
	file://network/route-options-support.patch;striplevel=5 \
	file://network/0001-Stx-uses-puppet-boolean-instead-of-adrien-boolean.patch \
	" 

inherit ruby 

DEPENDS += " \
	ruby \
	facter \
	"

RDEPENDS_${PN} += " \
	ruby \
	facter \
	puppet \
	"

RUBY_INSTALL_GEMS = "${PN}-${PV}.gem"


do_install_append() {
	install -d -m 0755 ${D}/${datadir}/puppet/modules/network
	cp -R ${S}/* ${D}/${datadir}/puppet/modules/network
	# Remove broken symlinks for now
	rm -f ${D}/${libdir}/ruby/gems/2.5.0/gems/puppet-network-0.5.0/spec/fixtures/modules/network/manifests
	rm -f ${D}/${libdir}/ruby/gems/2.5.0/gems/puppet-network-0.5.0/spec/fixtures/modules/network/templates
	rm -f ${D}/${libdir}/ruby/gems/2.5.0/gems/puppet-network-0.5.0/spec/fixtures/modules/network/files
	rm -f ${D}/${libdir}/ruby/gems/2.5.0/gems/puppet-network-0.5.0/spec/fixtures/modules/network/lib
	rm -f ${D}/${datadir}/puppet/modules/network/spec/fixtures/modules/network/files
}

FILES_${PN} += " ${datadir}"
