# Fails to build and needs multiple attempts if PARALLEL_MAKE > 20
# Work around for now: set PARALLEL_MAKE for this pacakge to 7
# Actual fix is in the package itself

EXTRA_OEMAKE_remove = " -j ${PARALLEL_MAKE}"
EXTRA_OEMAKE = " -j7"
