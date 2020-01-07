TRANSFORM_NAME = "s,grub,grub2,"
EXTRA_OECONF += "--program-transform-name=${TRANSFORM_NAME} \
"

FILES_${PN}-editenv = "${bindir}/grub2-editenv"
