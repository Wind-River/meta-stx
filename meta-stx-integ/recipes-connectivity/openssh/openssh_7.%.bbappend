#
## Copyright (C) 2019 Wind River Systems, Inc.
#
#  Licensed under the Apache License, Version 2.0 (the "License");
#  you may not use this file except in compliance with the License.
#  You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
#  Unless required by applicable law or agreed to in writing, software
#  distributed under the License is distributed on an "AS IS" BASIS,
#  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#  See the License for the specific language governing permissions and
#  limitations under the License.

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

BRANCH = "r/stx.3.0"
SRCREV = "d778e862571957ece3c404c0c37d325769772fde"

SRC_URI += "\
    file://stx/sshd.init \
    file://stx/sshd-keygen \
    file://stx/sshd-keygen.service \
    file://stx/sshd.sysconfig \
    git://opendev.org/starlingx/config-files.git;protocol=https;rev=${SRCREV};branch=${BRANCH};destsuffix=stx-configfiles;subpath=openssh-config \
    file://openssh-config-rm-hmac-ripemd160.patch \
"

do_unpack_append() {
    bb.build.exec_func('do_copy_config_files', d)
}

do_copy_config_files () {
    cp -f ${WORKDIR}/stx-configfiles/files/sshd_config ${S}
    # remove the unsupported and deprecated options
    sed -i -e 's/^\(GSSAPIAuthentication.*\)/#\1/' \
           -e 's/^\(GSSAPICleanupCredentials.*\)/#\1/' \
           -e 's/^\(UsePrivilegeSeparation.*\)/#\1/' \
           ${S}/sshd_config
    cp -f ${WORKDIR}/stx-configfiles/files/ssh_config ${S}
}

SYSTEMD_SERVICE_${PN}-sshd = "sshd.service"

do_install_append () {
    rm -f ${D}${systemd_system_unitdir}/sshd.socket
    rm -f ${D}${systemd_system_unitdir}/sshd@service.socket
    rm -f ${D}${systemd_system_unitdir}/sshdgenkeys.service

    install -d ${D}/${sysconfdir}/init.d/
    install -m 755 ${WORKDIR}/stx/sshd.init ${D}/${sysconfdir}/init.d/sshd

    install -d ${D}/${sysconfdir}/sysconfig/
    install -m 644 ${WORKDIR}/stx/sshd.sysconfig ${D}/${sysconfdir}/sysconfig/sshd

    install -m 755 ${WORKDIR}/stx/sshd-keygen ${D}/${sbindir}/sshd-keygen
    install -m644 ${WORKDIR}/stx-configfiles/files/sshd.service ${D}/${systemd_system_unitdir}/sshd.service
    install -m644 ${WORKDIR}/stx/sshd-keygen.service ${D}/${systemd_system_unitdir}/sshd-keygen.service

    install -d ${D}/${sysconfdir}/tmpfiles.d
    echo "d ${localstatedir}/run/sshd 0755 root root -" >> ${D}/${sysconfdir}/tmpfiles.d/sshd.conf

}

RDEPENDS_${PN} += "bash"
RDEPENDS_${PN}-sshd += "bash"

# allow both systemd service and sysvinit scripts are installed
DISTRO_FEATURES_BACKFILL_CONSIDERED_remove = "sysvinit"

USERADD_PARAM_${PN}-sshd = "-r -d /var/empty/sshd -s /sbin/nologin -g sshd -c 'Privilege-separated SSH' sshd"
GROUPADD_PARAM_${PN}-sshd = "-r ssh_keys; -r sshd"
