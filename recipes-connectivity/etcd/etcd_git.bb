SUMMARY = "A distributed key-value store for shared config and service discovery"
DESCRIPTION = " \
    etcd is a distributed reliable key-value store for the most critical data \
    of a distributed system, with a focus on being: \
    \
    * Simple: well-defined, user-facing API (gRPC) \
    * Secure: automatic TLS with optional client cert authentication \
    * Fast: benchmarked 10,000 writes/sec \
    * Reliable: properly distributed using Raft \
"
HOMEPAGE = "https://github.com/coreos/etcd"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://src/import/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = " \
    git://github.com/coreos/etcd.git;branch=release-3.3 \
    file://bz1350875-disaster-recovery-with-copies.patch \
    file://expand-etcd-arch-validation.patch \
    file://etcd.service \
    file://etcd.conf \
    "

SRCREV = "98d308426819d892e149fe45f6fd542464cb1f9d"
PV = "3.3.13+git${SRCPV}"

S = "${WORKDIR}/git"

inherit go goarch systemd useradd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "etcd.service"
SYSTEMD_AUTO_ENABLE_${PN} = "enable"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = " \
    --system --shell ${sbindir}/nologin --comment 'etcd user' \
    --home-dir ${localstatedir}/lib --groups etcd --gid etcd etcd \
    "
GROUPADD_PARAM_${PN} = "etcd"

TARGET_CC_ARCH += "${LDFLAGS}"
GO_IMPORT = "import"

do_compile() {
	export GOARCH="${TARGET_GOARCH}"

	# Setup vendor directory so that it can be used in GOPATH.
	#
	# Go looks in a src directory under any directory in GOPATH but
	# uses 'vendor' instead of 'vendor/src'. We can fix this with a symlink.
	#
	# We also need to link in the ipallocator directory as that is not under
	# a src directory.
	ln -sfn . "${S}/src/import/cmd/vendor/src"
	mkdir -p "${S}/src/import/cmd/vendor/src/github.com/cockroachdb/cmux"
	ln -sfn "${S}/cmux" "${S}/src/import/cmd/vendor/github.com/cockroachdb/cmux"
	export GOPATH="${S}/src/import/cmd/vendor"

	# Pass the needed cflags/ldflags so that cgo
	# can find the needed headers files and libraries
	export CGO_ENABLED="1"
	export CFLAGS=""
	export LDFLAGS=""
	export CGO_CFLAGS="${BUILDSDK_CFLAGS} --sysroot=${STAGING_DIR_TARGET}"
	export CGO_LDFLAGS="${BUILDSDK_LDFLAGS} --sysroot=${STAGING_DIR_TARGET}"

	if [ "${TARGET_ARCH}" = "x86_64" ]; then
		export GOARCH="amd64"
	elif [ "${TARGET_ARCH}" = "i586" ]; then
		export GOARCH="386"
	fi

	./src/import/build
}

do_install() {
	install -d ${D}/${bindir}
	install -m 0755 ${S}/src/import/bin/etcd ${D}/${bindir}/etcd
	install -m 0755 ${S}/src/import/bin/etcdctl ${D}/${bindir}/etcdctl

	install -d ${D}${systemd_system_unitdir}
	install -m 0644 ${S}/src/import/contrib/systemd/etcd.service ${D}${systemd_system_unitdir}

	# etcd state is in /var/lib/etcd
	install -d ${D}/${localstatedir}/lib/${BPN}

	# we aren't creating a user, so we need to comment out this line
	sed -i '/User/s/^/#/' ${D}${systemd_unitdir}/system/etcd.service
}

deltask compile_ptest_base

RDEPENDS_${PN} = "bash"

# During packaging etcd gets the warning "no GNU hash in elf binary"
# This issue occurs due to compiling without ldflags, but a
# solution has yet to be found. For now we ignore this error with
# the line below.
#INSANE_SKIP_${PN} = "ldflags"
