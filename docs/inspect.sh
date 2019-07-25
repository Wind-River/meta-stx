#!/bin/bash

# To use this script:
usage () {

cat << EOF

# This script needs the installed package list.
# 1 - build the project with 

bitbake openstack-image-aio

# 2 - From bitbake build environment:

bitbake openstack-image-aio -c devshell
cd ../rootfs
alias prpm='rpm --root=\$PWD --dbpath=/var/lib/rpm '
prpm -qa | sed -e 's/^kernel.*$//g;s/.*-d[be][gv].*//g' | grep -v ^$ > /<PATH>/<TO>/meta-starlingX/docs/openstack-image-aio.pkgs
exit

cd /<PATH>/<TO>/meta-starlingX/docs/
./inspect.sh

##################################################################
# Import Pack Packages_List_efforts.csv with ":" field delimeter.#
##################################################################
EOF
exit 0
}


INCLUDED_PKGS="python-ryu-common|python-libs|vim|audit|avahi|bzip2|dbus|e2fsprogs|file|fuse|gettext|kmod|libvirt|lvm2|ncurses|net|net|openssl|pciutils|perl|postgresql|rpm|rpm|systemd|xz|zlib|sqlite|openssl|openssh|wget|grub|elfutils|apr|btrfs-progs|httpd|iproute|libacl|libaio|libassuan|libattr|libcap-ng|libcurl|libdaemon|libffi|libgcc|libgomp|libICE|libidn|libmnl|libselinux|libselinux|libsepol|libsmartcols|libstdc++|libtirpc|libtool-ltdl|libunistring|libuuid|libwbclient|libwbclient|alsa-lib|cups-lib|gnupg2|gnutls|jansson|json-c|libedit|liblkid|libestr|libev|libgudev1|libibverbs|libjpeg-turbo|liboath|libogg|libpciaccess|libpipeline|libpcap|libpng|librdmacm|libsemanage|libsysfs|libteam|libtempter|lm_sensors-libs|lzo|mariadb-common|mtce-guestAgent|mtce-guestServer|newt|newt-python|numactl-libs|openstack-barbican|openstack-dashboard|openstack-keystone|passwd|pcre|pixman|pulseaudio-libs|pygpgme|pyparsing|python2-crypto|python2-docker|python-flask-restful|python2-pika_pool|python2-pyOpenSSL|python-3parclient|PyYAML|readline|samba-client-libs|seabios-bin|shadow-utils|spice-server|tcp_wrappers|yajl|cyrus-sasl|efivar-libs|expat|gdbm|glib2|gmp|groff-base|libnl3|mozjs17|openldap|opus|createrepo|c-ares|libXext|libXau|libXi|libXtst|mod_wsgi|popt|device-mapper-multipath|flac-libs|GeoIP|gpm-libs|librdmacm|ntfs|ntfs-progs|OpenIPMI-modalias|pytz|snappy|info|freetype|libutempter|lksctp-tools|lmsensors|erlang-os_mon|erlang-otp_mibs|erlang-public_key|erlang-runtime_tools|erlang-syntax_tools|libblkid|libmount|celt051|leveldb"

[ ! -f openstack-image-aio.pkgs ] && usage;
rm -f reports/*

# Parse the installed RPM packages into package name and package version 
# Package:Version
sed -e 's/\(^.*\)-\([0-9a-zA-Z].*-r[0-9]\)\(.*\)/\1:\2/g' openstack-image-aio.pkgs | sort > reports/ypkgs.lst
sed -e '1 s/^total .*$//g;s/^.*[0-9][0-9]:[0-9][0-9] //g;
	s/\(^.*[a-zA-Z]\)-\([0-9].*.[el7\|tis]\)\(.*\)/\1:\2/g;
	s/python2/python/g' packages.txt | grep -v "^$" | sort >  reports/stxpkgs.lst

awk 'BEGIN { FS = ":" }; {if($2!=$4) print $2":"$4}' Packages_List_efforts.csv > reports/renamed-pkgs.txt
awk 'BEGIN { FS = ":" }; {if ($4 == $2) print $2":"$4}' Packages_List_efforts.csv > reports/yocto-pkgs.txt

# Missing yocto-pkgs.txt

echo Missing Yocto pkgs > reports/missing_pkgs.txt
for f in $(cut -d':' -f1 reports/yocto-pkgs.txt | egrep -v $INCLUDED_PKGS); do
	grep -q -i -w $f reports/ypkgs.lst || echo $f | tee -a reports/missing_pkgs.txt
done

echo -e "\nMissing renamed Yocto pkgs" >> reports/missing_pkgs.txt
for f in $(cut -d':' -f2 reports/renamed-pkgs.txt | egrep -v $INCLUDED_PKGS); do
	echo $f | grep -q -w none && continue
	grep -q -i $f reports/ypkgs.lst || echo $f | tee -a reports/missing_pkgs.txt
done

echo -e "\nMissing Yocto recipes" >> reports/missing_pkgs.txt
for f in $(grep -w none reports/renamed-pkgs.txt | cut -d':' -f1 | egrep -v $INCLUDED_PKGS); do
	grep -q -i $f -w reports/ypkgs.lst && \
		echo $f >> reports/recipes_added.txt || echo $f | tee -a reports/missing_pkgs.txt
done

x=$(cat reports/ypkgs.lst)
for t in ${x[@]}; do
        p=$(echo $t | cut -d":" -f1)
        sed -i -e "1,$ s/\(^[0-9].*:$p:.*:.*:.*:\)\(stx-none:stx-none:\)\(.*:.*\)/\1$t:\3/g" Packages_List_efforts.csv
done
