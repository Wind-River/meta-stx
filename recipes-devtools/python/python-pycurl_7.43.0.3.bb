DESCRIPTION = "libcurl python bindings"
LICENSE = "LGPL | MIT"
LIC_FILES_CHKSUM = " \
	file://COPYING-LGPL;md5=4fbd65380cdd255951079008b364516c \
	file://COPYING-MIT;md5=2df767ed35d8ea83de4a93feb55e7815 \
	"

#SRC_URI[md5sum] = ""
SRC_URI[sha256sum] = "6f08330c5cf79fa8ef68b9912b9901db7ffd34b63e225dce74db56bb21deda8e"


PYPI_PACKAGE = "pycurl"
inherit pypi setuptools distutils

export BUILD_SYS
export HOST_SYS
export STAGING_INCDIR
export STAGING_LIBDIR

DEPENDS = " curl python"

BBCLASSEXTEND = " native"
