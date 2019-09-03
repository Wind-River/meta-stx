DESCRIPTION = "\
		"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e73f0786a936da3814896df06ad225a9"

SRCREV = "f74b7104c0124db56c123ed171f378e82bd207c2"
PROTOCOL = "https"
BRANCH = "stable-0.6.0"
S = "${WORKDIR}/git"

SRC_URI = "git://github.com/stefanberger/libtpms.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

DEPENDS += " tpm2-tss tpm2-tools tpm2-abrmd openssh openssl"

inherit autotools

EXTRA_OECONF += "--with-openssl --with-tpm2"
