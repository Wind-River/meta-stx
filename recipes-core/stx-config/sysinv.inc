PACKAGES += " sysinv"

do_configure_prepend() {
	echo "Configure sysinv..."
	cd ${S}/sysinv/cgts-client/cgts-client
	distutils_do_configure
	cd ${S}/sysinv/sysinv/sysinv
	distutils_do_configure
} 

do_compile_prepend() {
	echo "Building sysinv..."
	cd ${S}/sysinv/cgts-client/cgts-client
	distutils_do_compile
	cd ${S}/sysinv/sysinv/sysinv
	distutils_do_compile
}

do_install_prepend() {

	echo "Installing sysinv..."
	cd ${S}/sysinv/cgts-client/cgts-client
	distutils_do_install
	cd ${S}/sysinv/sysinv/sysinv
	distutils_do_install

	install -p -D -m 755 ${S}/sysinv/sysinv-agent/sysinv-agent ${D}/${sysconfdir}/init.d/sysinv-agent
	install -p -D -m 644 ${S}/sysinv/sysinv-agent/sysinv-agent.conf ${D}/${sysconfdir}/pmon.d/sysinv-agent.conf
	install -p -D -m 644 ${S}/sysinv/sysinv-agent/sysinv-agent.service ${D}/${systemd_system_unitdir}/sysinv-agent.service
}

FILES_sysinv = " \
	${bindir}/sysinv-agent \
	${bindir}/system \
	${bindir}/sysinv-conductor \
	${bindir}/sysinv-api \
	${bindir}/sysinv-helm \
	${bindir}/sysinv-dbsync \
	${bindir}/sysinv-dnsmasq-lease-update \
	${bindir}/sysinv-puppet \
	${bindir}/sysinv-rootwrap \
	${bindir}/sysinv-upgrade \
	${systemd_system_unitdir}/sysinv-agent.service  \
	${sysconfdir}/pmon.d/sysinv-agent.conf \
	${sysconfdir}/init.d/sysinv-agent \
	${libdir}/python2.7/site-packages/sysinv/ \
	${libdir}/python2.7/site-packages/cgtsclient/ \
	${libdir}/python2.7/site-packages/cgtsclient-*.egg-info \
	${libdir}/python2.7/site-packages/sysinv-*.egg-info \
	"
