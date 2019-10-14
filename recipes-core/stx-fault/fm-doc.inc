PACKAGES += "fm-doc"

do_install_append () {
	cd ${S}/fm-doc/fm_doc/
	install -d -m 755 ${D}/${cgcs_doc_deploy}
	install -d -m 755 ${D}/${sysconfdir}/fm
	install -m 744 events.yaml ${D}/${sysconfdir}/fm/
	install -m 644 events.yaml ${D}/${cgcs_doc_deploy}
	install -m 755 checkEventYaml ${D}/${cgcs_doc_deploy}
	install -m 644 parseEventYaml.py ${D}/${cgcs_doc_deploy}
	install -m 644 check_missing_alarms.py ${D}/${cgcs_doc_deploy}
}

FILES_fm-doc = " \
	${sysconfdir}/fm/events.yaml \
	${cgcs_doc_deploy}/events.yaml \
	${cgcs_doc_deploy}/checkEventYaml \
	${cgcs_doc_deploy}/parseEventYaml.py \
	${cgcs_doc_deploy}/check_missing_alarms.py \
	"
