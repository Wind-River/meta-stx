FILESEXTRAPATHS_prepend := "${THISDIR}/patches:${THISDIR}/files:"


SRC_URI += "\
	file://ceph/0001-Add-hooks-for-orderly-shutdown-on-controller.patch \
	file://ceph/ceph.conf \
	file://ceph/ceph-init-wrapper.sh \
        file://ceph/ceph-preshutdown.sh \
        file://ceph/ceph.service \
        file://ceph/mgr-restful-plugin.py \
        file://ceph/starlingx-docker-override.conf \
        file://ceph/ceph.conf.pmon \
        file://ceph/ceph-manage-journal.py \
        file://ceph/ceph-radosgw.service \
        file://ceph/ceph.sh \
        file://ceph/mgr-restful-plugin.service \
	"
    

do_install_append () {
    install -d ${D}${sysconfdir}/ceph
    install -m 0644 ${WORKDIR}/ceph/ceph.conf ${D}${sysconfdir}/ceph/
    install -m 0644 ${WORKDIR}/ceph/ceph-radosgw.service ${D}${systemd_system_unitdir}/ceph-radosgw@.service
    install -m 0644 ${WORKDIR}/ceph/ceph.service ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/ceph/mgr-restful-plugin.service ${D}${systemd_system_unitdir}

    install -m 0700 ${WORKDIR}/ceph/ceph-manage-journal.py ${D}${sbindir}/ceph-manage-journal
    install -Dm 0750 ${WORKDIR}/ceph/mgr-restful-plugin.py  ${D}${sysconfdir}/rc.d/init.d/mgr-restful-plugin
    install -m 0750 ${WORKDIR}/ceph/ceph.conf.pmon ${D}${sysconfdir}/ceph/

    install -d -m 0750 ${D}${sysconfdir}/services.d/controller
    install -d -m 0750 ${D}${sysconfdir}/services.d/storage
    install -d -m 0750 ${D}${sysconfdir}/services.d/worker

    install -m 0750 ${WORKDIR}/ceph/ceph.sh ${D}${sysconfdir}/services.d/controller
    install -m 0750 ${WORKDIR}/ceph/ceph.sh ${D}${sysconfdir}/services.d/storage
    install -m 0750 ${WORKDIR}/ceph/ceph.sh ${D}${sysconfdir}/services.d/worker

    install -Dm 0750 ${WORKDIR}/ceph/ceph-init-wrapper.sh ${D}${sysconfdir}/rc.d/init.d/ceph-init-wrapper
    install -m 0700 ${WORKDIR}/ceph/ceph-preshutdown.sh ${D}${sbindir}/ceph-preshutdown.sh
    
    install -Dm 0644 ${WORKDIR}/ceph/starlingx-docker-override.conf ${D}${systemd_system_unitdir}/docker.service.d/starlingx-docker-override.conf

    install -m 0644 -D ${S}/src/etc-rbdmap ${D}${sysconfdir}/ceph/rbdmap 
    install -m 0644 -D ${S}/etc/sysconfig/ceph ${D}${sysconfdir}/sysconfig/ceph
    install -m 0644 -D ${S}/src/logrotate.conf ${D}${sysconfdir}/logrotate.d/ceph

    install -m 0644 -D ${S}/COPYING ${D}${docdir}/ceph/COPYING    
    install -m 0644 -D ${S}/etc/sysctl/90-ceph-osd.conf ${D}${libdir}/sysctl.d/90-ceph-osd.conf
    install -m 0644 -D ${S}/udev/50-rbd.rules ${D}${libdir}/udev/rules.d/50-rbd.rules
    install -m 0644 -D ${S}/udev/60-ceph-by-parttypeuuid.rules ${D}${libdir}/udev/rules.d/60-ceph-by-parttypeuuid.rules

    mkdir -p ${D}${localstatedir}/ceph
    mkdir -p ${D}${localstatedir}/run/ceph
    mkdir -p ${D}${localstatedir}/log/ceph
    mkdir -p ${D}${localstatedir}/lib/ceph/tmp
    mkdir -p ${D}${localstatedir}/lib/ceph/mon
    mkdir -p ${D}${localstatedir}/lib/ceph/osd
    mkdir -p ${D}${localstatedir}/lib/ceph/mds
    mkdir -p ${D}${localstatedir}/lib/ceph/mgr
    mkdir -p ${D}${localstatedir}/lib/ceph/radosgw
    mkdir -p ${D}${localstatedir}/lib/ceph/bootstrap-osd
    mkdir -p ${D}${localstatedir}/lib/ceph/bootstrap-mds
    mkdir -p ${D}${localstatedir}/lib/ceph/bootstrap-rgw
    mkdir -p ${D}${localstatedir}/lib/ceph/bootstrap-mgr
    mkdir -p ${D}${localstatedir}/lib/ceph/bootstrap-rbd

    install -m 0750 -D ${S}/src/init-radosgw ${D}${sysconfdir}/rc.d/init.d/ceph-radosgw
    sed -i '/### END INIT INFO/a SYSTEMCTL_SKIP_REDIRECT=1' ${D}${sysconfdir}/rc.d/init.d/ceph-radosgw
    install -m 0750 -D ${S}/src/init-rbdmap ${D}${sysconfdir}/rc.d/init.d/rbdmap
    install -m 0750 -D ${B}/bin/init-ceph ${D}${sysconfdir}/rc.d/init.d/ceph
    install -m 0750 -D ${B}/bin/init-ceph ${D}${sysconfdir}/init.d/ceph
    install -d -m 0750 ${D}${localstatedir}/log/radosgw 
}

TARGET_CC_ARCH += "${LDFLAGS}"
RDEPENDS_${PN} += "\
        bash \
"

FILES_${PN} += "\
        ${localstatedir} ${libdir} ${docdir} \
        ${systemd_system_unitdir}/mgr-restful-plugin.service \
        ${systemd_system_unitdir}/ceph-radosgw@.service \
        ${systemd_system_unitdir}/ceph.service \
        ${systemd_system_unitdir}/docker.service.d \
        /run \
"
