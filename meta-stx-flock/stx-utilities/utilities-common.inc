
DESCRIPTION = "stx-utilities"

PROTOCOL = "https"
SRCNAME = "utilities"
BRANCH = "r/stx.3.0"
SRCREV = "cbad6b792157d066dd601f0f9ce62dc177d4c848"
S_DIR = "${WORKDIR}/git"
PV = "1.0.0"

#LICENSE = "Apache-2.0"

#e7b3e2a120f5d4c0f6f562a52b6badf4  ./security/tpm2-openssl-engine/tpm2-openssl-engine/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./utilities/build-info/build-info-1.0/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./utilities/namespace-utils/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./utilities/namespace-utils/namespace-utils/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./utilities/nfscheck/LICENSE #3b83ef96387f14655fc854ddc3c6bd57  ./utilities/nfscheck/files/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./utilities/pci-irq-affinity-agent/files/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./utilities/platform-util/scripts/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./utilities/update-motd/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./utilities/update-motd/files/LICENSE
#3b83ef96387f14655fc854ddc3c6bd57  ./utilities/worker-utils/worker-utils/LICENSE

ALIC_FILES_CHKSUM = " \
	file://ceph/ceph-manager/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
	file://ceph/python-cephclient/python-cephclient/LICENSE;md5=41687b590435621fc0676ac02c51154f \
	file://security/stx-ssl/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
	file://tools/collector/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
	file://tools/collector/scripts/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
	file://tools/engtools/hostdata-collectors/scripts/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
	file://utilities/logmgmt/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
	"


SRC_URI = "git://opendev.org/starlingx/${SRCNAME}.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

DEPENDS = " \
	python-pip \
	python-pbr-native \
	systemd \
"

## Skip tpm2-openssl-engine2
