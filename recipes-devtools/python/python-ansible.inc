DESCRIPTION = "Ansible is a simple IT automation platform that makes your applications and systems easier to deploy."
HOMEPAGE = "https://github.com/ansible/ansible/"
SECTION = "devel/python"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=8f0e2cd40e05189ec81232da84bd6e1a"

SRCNAME = "ansible"

SRC_URI = "http://releases.ansible.com/ansible/${SRCNAME}-${PV}.tar.gz"

SRC_URI[md5sum] = "86f0c18250895338709243d997005de3"
SRC_URI[sha256sum] = "8e9403e755ce8ef27b6066cdd7a4c567aa80ebe2fd90d0ff8efa0a725d246986"


S = "${WORKDIR}/${SRCNAME}-${PV}"

CLEANBROKEN = "1"
