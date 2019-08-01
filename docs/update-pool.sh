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
prpm -qa | sed -e '/^kernel.*$\|^.*-d[be][gv].*$/d' > /<PATH>/<TO>/meta-starlingX/docs/$PKGLIST
exit

cd /<PATH>/<TO>/meta-starlingX/docs/
./inspect.sh $PKGLIST $PKGPOOL

##################################################################
# Import Pack Packages_List_efforts.csv with ":" field delimeter.#
##################################################################
EOF
exit 0
}

INCLUDED_PKGS="python-ryu-common|python-libs|vim|audit|avahi|bzip2|dbus|e2fsprogs|file|fuse|gettext|kmod|libvirt|lvm2|ncurses|net|net|openssl|pciutils|perl|postgresql|rpm|rpm|systemd|xz|zlib|sqlite|openssl|openssh|wget|grub|elfutils|apr|btrfs-progs|httpd|iproute|libacl|libaio|libassuan|libattr|libcap-ng|libcurl|libdaemon|libffi|libgcc|libgomp|libICE|libidn|libmnl|libselinux|libselinux|libsepol|libsmartcols|libstdc++|libtirpc|libtool-ltdl|libunistring|libuuid|libwbclient|libwbclient|alsa-lib|cups-lib|gnupg2|gnutls|jansson|json-c|libedit|liblkid|libestr|libev|libgudev1|libibverbs|libjpeg-turbo|liboath|libogg|libpciaccess|libpipeline|libpcap|libpng|librdmacm|libsemanage|libsysfs|libteam|libtempter|lm_sensors-libs|lzo|mariadb-common|mtce-guestAgent|mtce-guestServer|newt|newt-python|numactl-libs|openstack-barbican|openstack-dashboard|openstack-keystone|passwd|pcre|pixman|pulseaudio-libs|pygpgme|pyparsing|python-crypto|python-docker|python-flask-restful|python-pika_pool|python-pyOpenSSL|python-3parclient|PyYAML|readline|samba-client-libs|seabios-bin|shadow-utils|spice-server|tcp_wrappers|yajl|cyrus-sasl|efivar-libs|expat|gdbm|glib2|gmp|groff-base|libnl3|mozjs17|openldap|opus|createrepo|c-ares|libXext|libXau|libXi|libXtst|mod_wsgi|popt|device-mapper-multipath|flac-libs|GeoIP|gpm-libs|librdmacm|ntfs|ntfs-progs|OpenIPMI-modalias|pytz|snappy|info|freetype|libutempter|lksctp-tools|lmsensors|erlang-os_mon|erlang-otp_mibs|erlang-public_key|erlang-runtime_tools|erlang-syntax_tools|libblkid|libmount|celt051|leveldb|python-paste-deploy|python-semantic_version|python-repoze-lru|python-django-horizon|python-scss|python-migrate|python-zmq|python-inotify|python-dogpile-cache|python-dogpile-core|python-jwt|python-aodhclient|python-gnocchiclient|python-ironicclient|python-jmespath|python-ldap3|python-muranoclient|python-oslo-cache|python-oslo-concurrency|python-oslo-config|python-oslo-context|python-oslo-db|python-oslo-i18n|python-oslo-log|python-oslo-messaging|python-oslo-middleware|python-oslo-policy|python-oslo-rootwrap|python-oslo-serialization|python-oslo-service|python-oslo-utils|python-oslo-versionedobjects|python-pankoclient|python-pyngus|python-requests-oauthlib|python-configobj|python-alembic|python-amqp|python-appdirs"

PKGLIST=$1
PKGPOOL=$2

[ ! -f $PKGLIST ] || [ ! -f $PKGPOOL ] && usage;
rm -f reports/*

# Parse the installed RPM packages into package name and package version 
# Package:Version
sed -e 's/\(^.*\)-\([0-9a-zA-Z].*-r[0-9]\)\(.*\)/\1:\2/g' $PKGLIST | sort > reports/ypkgs.txt

# Parse $PKGPOOL  into (1) renamed, (2) none or does not exist in yocto
# and (3) has a corresponding yocto package
awk 'BEGIN { FS = ":" }; {if($2!=$4 && $4!="none") print $2":"$4}' $PKGPOOL > reports/renamed-pkgs.txt
awk 'BEGIN { FS = ":" }; {if($2!=$4 && $4 ==  "none") print $2":"$4}' $PKGPOOL > reports/none-pkgs.txt
awk 'BEGIN { FS = ":" }; {if ($4 == $2) print $2":"$4}' $PKGPOOL > reports/yocto-pkgs.txt

# Missing yocto-pkgs.txt

for f in $(sed -n -e '2,$p' reports/renamed-pkgs.txt; \
		cat reports/yocto-pkgs.txt reports/none-pkgs.txt | egrep -v "INCLUDED_PKGS"); do
	spkg=$(echo $f | cut -d':' -f1);
	ypkg=$(echo $f | cut -d':' -f2);
	[ $ypkg == "none" ] && ypkg=$spkg
	grep -q -w ^$ypkg reports/ypkgs.txt && pp+="^[0-9]\+:$spkg:.*$\|" || pm+=("$spkg")
done

pp[$((${#pp[@]} - 1))]=${pp[$((${#pp[@]} - 1))]%\\|*}
echo "Missing ${#pm[@]} packages!"
sed -e "/${pp[@]}/d" $PKGPOOL > ${PKGPOOL%_[0-9]*}_$(date +%m-%d-%y).csv
