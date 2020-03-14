pkg_postinst_ontarget_${PN}_append() {
	ln -fs ${libdir}/security/pam_pwquality.so /${baselib}/security/
}
