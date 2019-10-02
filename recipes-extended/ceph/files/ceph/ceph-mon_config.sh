#!/bin/bash

rm -rf /tmp/ceph.mon.keyring /etc/ceph/ceph.client.admin.keyring /tmp/monmap
rm -rf /var/lib/ceph/mon/ceph-starlingx-host-aio
mkdir -p /var/lib/ceph/mon/ceph-starlingx-host-aio
mkdir -p /var/lib/ceph/mon/ceph-starlingx-host-aio
mkdir -p /var/run/ceph
chown ceph:ceph /var/run/ceph

ceph-authtool --create-keyring /tmp/ceph.mon.keyring --gen-key -n mon. --cap mon 'allow *'

ceph-authtool --create-keyring /etc/ceph/ceph.client.admin.keyring \
	--gen-key -n client.admin --set-uid=0 --cap mon 'allow *' \
	--cap osd 'allow *' --cap mds 'allow *' --cap mgr 'allow *' 

ceph-authtool --create-keyring /var/lib/ceph/bootstrap-osd/ceph.keyring \
	--gen-key -n client.bootstrap-osd --cap mon 'profile bootstrap-osd' 

ceph-authtool /tmp/ceph.mon.keyring --import-keyring /etc/ceph/ceph.client.admin.keyring
ceph-authtool /tmp/ceph.mon.keyring --import-keyring /var/lib/ceph/bootstrap-osd/ceph.keyring
monmaptool --create --add starlingx-host-aio 10.30.0.109 --fsid b4fb230f-a5e7-4d72-ba62-ecc8c7dacac5 /tmp/monmap
chown -R ceph:ceph /tmp/monmap
chown -R ceph:ceph /var/lib/ceph/mon/ceph-starlingx-host-aio
chown -R ceph:ceph /tmp/ceph.mon.keyring
sudo -u ceph ceph-mon --mkfs -i starlingx-host-aio --monmap /tmp/monmap --keyring /tmp/ceph.mon.keyring
sudo -u ceph touch /var/lib/ceph/mon/ceph-starlingx-host-aio/done
systemctl enable ceph-mon@starlingx-host-aio
systemctl start ceph-mon@starlingx-host-aio
