FILESEXTRAPATHS_append:= ":${THISDIR}/linux:"

SRC_URI += "file://stx-kconfig.cfg"

KERNEL_EXTRA_FEATURES_append = " \
    features/xfs/xfs.scc \
    features/iommu/iommu.scc \
    features/vfio/vfio.scc \
    cfg/debug/sched/debug-sched.scc \
    "
