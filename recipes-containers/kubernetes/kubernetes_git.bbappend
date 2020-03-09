FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
PV = "1.16.2+git${SRCREV_kubernetes}"
SRCREV_kubernetes = "c97fe5036ef3df2967d086711e6c0c405941e14b"

SRC_URI = "git://github.com/kubernetes/kubernetes.git;branch=release-1.16;name=kubernetes \
	file://0001-hack-lib-golang.sh-use-CC-from-environment.patch \
	file://0001-cross-don-t-build-tests-by-default.patch \
	file://kubernetes-accounting.conf \
	file://kubeadm.conf \
	file://kubelet-cgroup-setup.sh \
	"

INSANE_SKIP_${PN} += "textrel"
INSANE_SKIP_${PN}-misc += "textrel"
INSANE_SKIP_kubelet += "textrel"

SYSTEMD_AUTO_ENABLE_kubelet = "disable"

do_install_append() {
	# kubeadm:
	install -d -m 0755 ${D}/${sysconfdir}/systemd/system/kubelete.service.d
	install -m 0644 ${WORKDIR}/kubeadm.conf ${D}/${sysconfdir}/systemd/system/kubelete.service.d

	# kubelete-cgroup-setup.sh
	install -d -m 0755 ${D}/${bindir}
	install -m 0644 ${WORKDIR}/kubelet-cgroup-setup.sh ${D}/${bindir}

	# enable CPU and Memory accounting
	install -d -m 0755 ${D}/${sysconfdir}/systemd/system.conf.d
	install -m 0644 ${WORKDIR}/kubernetes-accounting.conf ${D}/${sysconfdir}//systemd/system.conf.d/
}

