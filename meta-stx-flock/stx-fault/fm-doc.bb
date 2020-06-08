
require fault-common.inc

S = "${S_DIR}/fm-doc/fm_doc"

do_configure () {
     :
}

do_compile () {
     :
}

do_install () {
    install -d -m 755 ${D}/${docdir}
    install -d -m 755 ${D}/${sysconfdir}/fm
    install -m 744 events.yaml ${D}/${sysconfdir}/fm/
    install -m 644 events.yaml ${D}/${docdir}
    install -m 755 checkEventYaml ${D}/${docdir}
    install -m 644 parseEventYaml.py ${D}/${docdir}
    install -m 644 check_missing_alarms.py ${D}/${docdir}
}
