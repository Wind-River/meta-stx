FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " \
	file://puppet-oslo/0001-Remove-log_dir-from-conf-files.patch \
	file://puppet-oslo/0002-add-psycopg2-drivername-to-postgresql-settings.patch \
	"
