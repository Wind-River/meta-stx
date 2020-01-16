SRC_URI = "\
	git://github.com/openstack/python-keystoneclient.git;branch=stable/rocky \
	file://keystone-api-check.sh \
	"

PV = "3.17.0+git${SRCPV}"
SRCREV = "234ea50d5dfa3c6b71c15d32223a2ddf84c1aa1e"
DEPENDS += " \
        python-pip \
        python-pbr \
        "
