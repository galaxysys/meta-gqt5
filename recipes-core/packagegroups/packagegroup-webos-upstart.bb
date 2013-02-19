DESCRIPTION = "Upstart components use by the Open webOS project"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PR = "r1"

inherit packagegroup

RDEPENDS_${PN} = " \
  connman-upstart \
  ofono-upstart \
"
