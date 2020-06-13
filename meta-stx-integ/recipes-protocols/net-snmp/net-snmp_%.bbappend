inherit useradd

USERADD_PACKAGES = "net-snmp-server-snmpd"
USERADD_PARAM_net-snmp-server-snmpd = "-r -g snmpd -d /usr/share/snmp -s /sbin/nologin -c 'net-snmp' snmpd"
GROUPADD_PARAM_net-snmp-server-snmpd = "-r snmpd"
