SUMMARY = "StarlingX stx packages"

PR = "r0"

#
# packages which content depend on MACHINE_FEATURES need to be MACHINE_ARCH
#

PACKAGE_ARCH = "${MACHINE_ARCH}"


inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = " \
	packagegroup-stx-kube \
	packagegroup-stx-misc \
	packagegroup-stx-ceph \
	"

RDEPENDS_packagegroup-stx-kube = "\
	kubernetes \
	kubernetes-misc \
	kubeadm \
	kubelet \
	kubectl \
	docker \
	util-linux-unshare \
	containerd-opencontainers \
	runc-docker \
	docker \
	packagegroup-stx-misc \
	"
	
RDEPENDS_packagegroup-stx-misc = "\
	vim \
	vim-common \
	ntp \
	python3-pip \
	"

RDEPENDS_packagegroup-stx-ceph = "\
	ceph \
	ceph-python \
	openldap \
	rocksdb \
	snappy \
	lz4 \
	oath \
	python-prettytable \
	python-eventlet \
	util-linux-uuidgen \
	rdma-core \
	python3-pyopenssl \
	python3-bcrypt \
	python3-werkzeug \
	python3-pyroute2 \
	python3-requests \
	python3-cherrypy \
	python3-six \
	python3-mako \
	python3-pecan \
	python3-prettytable \
	python3-pycparser \
	python3-cffi \
	python3-cryptography \
	python3-more-itertools \
	python3-pytz \
	python3-jaraco-functools \
	python3-tempora \
	python3-portend \
	python3-zc-lockfile \
	python-oslo.messaging \
	"
#	tsconfig \
#	ceph-manager \
#	sysinv
