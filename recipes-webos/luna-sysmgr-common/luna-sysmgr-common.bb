# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "Library containing common parts of luna-sysmgr and webappmanager"
SECTION = "webos/libs"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "glib-2.0 luna-prefs luna-service2 cjson nyx-lib libpbnjson sqlite3 pmloglib librolegen serviceinstaller"
DEPENDS += "luna-webkit-api"
DEPENDS += "luna-sysmgr-ipc luna-sysmgr-ipc-messages"
DEPENDS += "qtbase"

# temporary until we have oe-core with this patch included
# http://lists.openembedded.org/pipermail/openembedded-core/2013-July/080893.html
DEPENDS += "virtual/${TARGET_PREFIX}binutils"

WEBOS_VERSION = "3.0.0-3_197f440025a5c5daa46e2b6857521c42a5d40490"

# Don't uncomment until all of the do_*() tasks have been moved out of the recipe
#inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_library
inherit webos_qmake5

SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

inherit webos-ports-submissions
SRCREV = "87c7dc68d660b196aad18f21046082ade720fa70"

OE_QMAKE_PATH_HEADERS = "${OE_QMAKE_PATH_QT_HEADERS}"

do_install() {
    oe_runmake install

    install -d ${D}${libdir}/pkgconfig
    install -d ${D}${includedir}/luna-sysmgr-common

    oe_libinstall -C release-common -so libLunaSysMgrCommon ${D}${libdir}

    install -v -m 644 ${S}/include/*.h ${D}${includedir}/luna-sysmgr-common

    sed -i -e s:${PKG_CONFIG_SYSROOT_DIR}::g release-common/LunaSysMgrCommon.pc
    install -v -m 644 release-common/LunaSysMgrCommon.pc ${D}${libdir}/pkgconfig
}
