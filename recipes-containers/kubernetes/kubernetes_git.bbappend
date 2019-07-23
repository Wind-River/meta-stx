PV = "1.13.5+git${SRCREV_kubernetes}"
SRCREV_kubernetes = "2166946f41b36dea2c4626f90a77706f426cdea2"

SRC_URI = "git://github.com/kubernetes/kubernetes.git;branch=release-1.13;name=kubernetes \
           file://0001-hack-lib-golang.sh-use-CC-from-environment.patch \
           file://0001-cross-don-t-build-tests-by-default.patch \
           "

INSANE_SKIP_${PN} += "textrel"
INSANE_SKIP_${PN}-misc += "textrel"
INSANE_SKIP_kubelet += "textrel"
