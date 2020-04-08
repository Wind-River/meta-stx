FILESEXTRAPATHS_append:= ":${THISDIR}/linux:"

# Enable:
# drbd
# IMA
# intel-e1000e
# intel-i40e
# intel-i40evf
# intel-ixgbe
# intel-ixgbevf
# qat17
# tpmdd
# Preempt_rt

SRC_URI += "file://stx-kconfig.cfg"

KERNEL_EXTRA_FEATURES_append = " features/xfs/xfs.scc"
