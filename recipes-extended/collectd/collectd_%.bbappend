FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://collectd-fix-for-LIBPYTHON_LDFLAGS.patch"

PACKAGECONFIG += "python"

PACKAGECONFIG[python] = "--enable-python --with-libpython,--disable-python --with-libpython=no,python"
