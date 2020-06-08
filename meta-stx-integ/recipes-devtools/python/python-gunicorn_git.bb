
DESCRIPTION = "Gunicorn 'Green Unicorn' is a Python WSGI HTTP Server for UNIX. It's a pre-fork worker model ported from Ruby's Unicorn project. The Gunicorn server is broadly compatible with various web frameworks, simply implemented, light on server resource usage, and fairly speedy."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f75f3fb94cdeab1d607e2adaa6077752"


SRCREV = "67949dc71c54e435e62c1fcedc2315a9098d54ea"
PROTOCOL = "https"
BRANCH = "19.x"
S = "${WORKDIR}/git"
PV = "19.10.0+git${SRCPV}"

SRC_URI = "git://github.com/benoitc/gunicorn.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"


DEPENDS += " python"
inherit setuptools distutils pkgconfig
