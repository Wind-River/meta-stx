FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " \
	file://${PN}/0001-Roll-up-TIS-patches.patch \
	file://${PN}/0002-remove-puppetlabs-apt-as-a-requirement.patch \
	file://${PN}/0001-puppetlabs-postgresql-account-for-naming-diffs.patch \
	"
