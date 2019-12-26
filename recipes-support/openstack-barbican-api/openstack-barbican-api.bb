FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = " \
	file://LICENSE \
	file://barbican.conf \
	file://gunicorn-config.py \
	file://barbican-api-paste.ini \
	file://openstack-barbican-api.service \
	"

do_configure() {
	:
}


do_compile() {
	:
}


do_install() {

	install -m 0755 -d ${D}/${datadir}/starlingx/barbican/
	install -m 0755 -d ${D}/${datadir}/starlingx/barbican/backup/
	install -m 0755 -d ${D}/${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/barbican.conf ${D}/${datadir}/starlingx/barbican
	install -m 0644 ${WORKDIR}/barbican-api-paste.ini ${D}/${datadir}/starlingx/barbican
	install -m 0644 ${WORKDIR}/gunicorn-config.py ${D}/${datadir}/starlingx/barbican
	install -m 0644 ${WORKDIR}/openstack-barbican-api.service ${D}/${systemd_system_unitdir}/openstack-barbican-api.service
}

pkg_preinst_${PN}() {
	tar -C / -czpf /tmp/barbican.default.tar.gz ./etc/barbican
}

pkg_postinst_${PN}() {

	tar -C / -czpf /usr/share/starlingx/barbican/backup/barbican.$(date +%s).tar.gz ./etc/barbican
	rm -rf /etc/barbican/

	if [ ! -f /usr/share/starlingx/barbican/backup/barbican.default.tar.gz ]; then 
		if [ ! /tmp/barbican.default.tar.gz ]; then
			exit -1;
		fi;
		mv /tmp/barbican.default.tar.gz /usr/share/starlingx/barbican/backup/barbican.default.tar.gz
	fi;

	# Restore to default settings
	tar -C / -xzpf /usr/share/starlingx/barbican/backup/barbican.default.tar.gz

	cp /usr/share/starlingx/barbican/barbican-api-paste.ini /etc/barbican/
	cp /usr/share/starlingx/barbican/barbican.conf /etc/barbican/
	cp /usr/share/starlingx/barbican/gunicorn-config.py /etc/barbican/
	systemctl daemon-reload
}

pkg_prerm_${PN}() {
	tar -C / -czpf /usr/share/starlingx/barbican/backup/barbican.$(date +%s).tar.gz ./etc/barbican
	rm -rf /etc/barbican/

	# Restore to default settings
	tar -C / -xzpf /usr/share/starlingx/barbican/backup/barbican.default.tar.gz
}

FILES_${PN} = " \
	${datadir}/starlingx/barbican/ \
	${systemd_system_unitdir}/openstack-barbican-api.service \
	"
