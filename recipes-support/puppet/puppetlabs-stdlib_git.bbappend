
# FIXME: 
# For now this is a Workaround to not install broken links; otherwise the package strip and split fails.

do_install_append () {
	rm -rf ${D}/usr/lib/ruby/gems/2.5.0/gems/puppetlabs-stdlib-4.10.0/spec/fixtures/modules/stdlib/manifests
	rm -rf ${D}/usr/lib/ruby/gems/2.5.0/gems/puppetlabs-stdlib-4.10.0/spec/fixtures/modules/stdlib/lib
	cp -r ${S}/manifests \
		${D}/usr/lib/ruby/gems/2.5.0/gems/puppetlabs-stdlib-4.10.0/spec/fixtures/modules/stdlib/
	cp -r ${S}/lib \
		${D}/usr/lib/ruby/gems/2.5.0/gems/puppetlabs-stdlib-4.10.0/spec/fixtures/modules/stdlib/
}
