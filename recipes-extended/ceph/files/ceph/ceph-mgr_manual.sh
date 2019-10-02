#!/bin/bash

mkdir -p /var/lib/ceph/mgr/ceph-starlingx-host-aio
chown ceph:ceph /var/lib/ceph/mgr
chown ceph:ceph /var/lib/ceph/mgr/ceph-starlingx-host-aio
ceph auth get-or-create mgr.starlingx-host-aio mon 'allow profile mgr' osd ' allow *' mds ' allow *'
#systemctl enable ceph-mgr@starlingx-host-aio
systemctl start ceph-mgr@starlingx-host-aio
sleep 4
ceph mgr module enable status
ceph mgr module enable dashboard
ceph dashboard create-self-signed-cert
ceph dashboard set-login-credentials sysadmin sysadmin
