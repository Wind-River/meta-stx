#!/bin/bash

ceph-volume lvm create --data /dev/sda12
OSDFSID=$(ceph-volume lvm list | sed -n -e "s/^.*osd fsid.* \([a-z0-9].*$\)/\1/pg")
ceph-volume lvm create --filestore --data /dev/sda12 --journal /dev/sda13
systemctl enable ceph-volume@lvm-0-$OSDFSID 
#ceph-volume lvm activate 0 $OSDFSID
#ceph-volume lvm activate 0 $OSDFSID
