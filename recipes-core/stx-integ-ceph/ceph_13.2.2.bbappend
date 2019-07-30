FILESEXTRAPATHS_prepend := "${THISDIR}/patches:${THISDIR}/files:"
SRC_URI_append = "file://0001-Add-hooks-for-orderly-shutdown-on-controller.patch \
                  file://ceph.conf   \
                  file://ceph-init-wrapper.sh   \
                  file://ceph-preshutdown.sh   \
                  file://ceph.service   \
                  file://mgr-restful-plugin.py   \
                  file://starlingx-docker-override.conf   \
                  file://ceph.conf.pmon   \
                  file://ceph-manage-journal.py   \
                  file://ceph-radosgw.service   \
                  file://ceph.sh   \
                  file://mgr-restful-plugin.service   \
"
    

do_install_append () {
    install -d ${D}${sysconfdir}/ceph
    install -m 0644 ${WORKDIR}/ceph.conf ${D}${sysconfdir}/ceph/
    install -m 0644 ${WORKDIR}/ceph-radosgw.service ${D}${systemd_system_unitdir}/ceph-radosgw@.service
    install -m 0644 ${WORKDIR}/ceph.service ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/mgr-restful-plugin.service ${D}${systemd_system_unitdir}

    install -m 0700 ${WORKDIR}/ceph-manage-journal.py ${D}${sbindir}/ceph-manage-journal
    install -Dm 0750 ${WORKDIR}/mgr-restful-plugin.py  ${D}${sysconfdir}/rc.d/init.d/mgr-restful-plugin
    install -m 0750 ${WORKDIR}/ceph.conf.pmon ${D}${sysconfdir}/ceph/

    install -d -m 0750 ${D}${sysconfdir}/services.d/controller
    install -d -m 0750 ${D}${sysconfdir}/services.d/storage
    install -d -m 0750 ${D}${sysconfdir}/services.d/worker

    install -m 0750 ${WORKDIR}/ceph.sh ${D}${sysconfdir}/services.d/controller
    install -m 0750 ${WORKDIR}/ceph.sh ${D}${sysconfdir}/services.d/storage
    install -m 0750 ${WORKDIR}/ceph.sh ${D}${sysconfdir}/services.d/worker

    install -Dm 0750 ${WORKDIR}/ceph-init-wrapper.sh ${D}${sysconfdir}/rc.d/init.d/ceph-init-wrapper
    install -m 0700 ${WORKDIR}/ceph-preshutdown.sh ${D}${sbindir}/ceph-preshutdown.sh
    
    install -Dm 0644 ${WORKDIR}/starlingx-docker-override.conf ${D}${systemd_system_unitdir}/docker.service.d/starlingx-docker-override.conf

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
