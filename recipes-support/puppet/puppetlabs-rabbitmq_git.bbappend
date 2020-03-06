FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
	file://puppetlabs-rabbitmq/0001-Roll-up-TIS-patches.patch \
	file://puppetlabs-rabbitmq/0002-Changed-cipher-specification-to-openssl-format.patch \
	file://puppetlabs-rabbitmq/0003-Eliminate-Puppet-4-deprecation-warnings.patch \
	file://puppetlabs-rabbitmq/0004-Partially-revert-upstream-commit-f7c3a4a637d59f3065d.patch \
	file://puppetlabs-rabbitmq/0005-Remove-the-rabbitmq_nodename-fact.patch \
	file://puppetlabs-rabbitmq/0006-Set-rabbitmq-dependency-to-nanliu-staging.patch \
	file://puppetlabs-rabbitmq/puppet-rabbitmq-poky.patch \
	"
