
PACKAGES += " pci-irq-affinity"

RDEPENDS_pci-irq-affinity  += " python-novaclient"
DESCRIPTION_pci-irq-affinity  = "StarlingX PCI Interrupt Affinity Agent Package"
SUMMARY_pci-irq-affinity  = "StarlingX PCI Interrupt Affinity Agent Package"

inherit setuptools distutils

do_configure_append() {
	cd ${S}/utilities/pci-irq-affinity-agent/pci_irq_affinity
	distutils_do_configure
}
do_compile_append() {
	cd ${S}/utilities/pci-irq-affinity-agent/pci_irq_affinity
	distutils_do_compile
}

do_install_append() {
	cd ${S}/utilities/pci-irq-affinity-agent/pci_irq_affinity
	distutils_do_install

	cd ${S}/utilities/pci-irq-affinity-agent/files

	install -p -d -m0755 ${D}/${sysconfdir}/init.d
	install -p -d -m0755 ${D}/${sysconfdir}/pmon.d
	install -p -d -m0755 ${D}/${sysconfdir}/pci_irq_affinity
	install -p -d -m0755 ${D}/${systemd_system_unitdir}
	install -p -d -m0755 ${D}/${bindir}

	install -m0755 pci-irq-affinity-agent ${D}/${sysconfdir}/init.d/pci-irq-affinity-agent
	install -m0644 pci-irq-affinity-agent.service ${D}/${systemd_system_unitdir}/pci-irq-affinity-agent.service

	install -m0755 nova-sriov ${D}/${bindir}/nova-sriov
	install -m0755 config.ini ${D}/${sysconfdir}/pci_irq_affinity/config.ini
}

FILES_pci-irq-affinity = "  \
	${bindir}/pci-irq-affinity-agent \
	${sysconfdir}/pci_irq_affinity/config.ini \
	${bindir}/nova-sriov \
	${sysconfdir}/init.d/pci-irq-affinity-agent \
	${systemd_system_unitdir}/pci-irq-affinity-agent.service \
	${PYTHON_SITEPACKAGES_DIR}/pci_irq_affinity/ \
	${PYTHON_SITEPACKAGES_DIR}/pci_irq_affinity_agent-${PV}-py${PYTHON_BASEVERSION}.egg-info/ \
	"
