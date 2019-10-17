
ROOTFS_POSTPROCESS_COMMAND += " stx_postprocess_rootfs"

stx_postprocess_rootfs() {
	chmod -R go+r usr/lib/python2.7/site-packages/httpretty-0.9.5-py2.7.egg-info
	sed -i -e 's/^\(ExecStartPre.*gencert.sh\)/#\1/g' lib/systemd/system/haproxy.service
	rm etc/systemd/system/haproxy.service

	sed -i -e 's/^physical_.*:eth0/physical_interface_mappings = provider:eno1/g' \
		etc/neutron/plugins/ml2/linuxbridge_agent.ini
	sed -i -e '/^l .*resolv.conf$/d' etc/default/volatiles/00_core
	sed -i -e '/^f .*resolv.conf none$/d' etc/default/volatiles/00_core

	cd etc  
	rm resolv.conf 
	ln -s resolv-conf.systemd resolv.conf 
	cd ..

	systemctl --root=${D} disable apache2.service
	systemctl --root=${D} disable cinder-volume.service
	systemctl --root=${D} disable cinder-api.service
	systemctl --root=${D} disable cinder-scheduler.service
	systemctl --root=${D} disable cinder-backup.service
	systemctl --root=${D} enable systemd-resolved.service
	chown etcd:etcd var/lib/etcd
}
