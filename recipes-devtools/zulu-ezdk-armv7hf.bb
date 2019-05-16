PV = "11.1.8"
PV_UPDATE = "CA"
BUILD_NUMBER = "11+28-LTS"

SUMMARY = "Azul Zulu Java Development Kit (JDK) binaries"
DESCRIPTION = "This the Embedded JDK for the 32 bit ARM architecture from \
 Azul Systems Inc. It is created using OpenJDK code, which is licensed under \
 GPLv2 and various other third party licenses. Azul offers a variety of \
 support plans, as well as patent indemnification and guarantees against \
 the risk of open source viral contamination, as part of its Zulu \
 Embedded commercial offerings."

BBCLASSEXTEND = "native"

LICENSE = "GPL-2.0-with-classpath-exception"
LIC_FILES_CHKSUM = "file://zulu11.1.8-ca-jdk11-c2-linux_aarch32hf/LICENSE;md5=3e0b59f8fac05c3c03d4a26bbda13f8f"

SRC_URI="http://cdn.azul.com/zulu-embedded/bin/zulu11.1.8-ca-jdk11-c2-linux_aarch32hf.tar.gz"

SRC_URI[md5sum] = "9aff035650bf9b7d923701bb31028844"
SRC_URI[sha256sum] = "a10825eb946014a8232718627e5fd54fe47e4423ddaa423e65c78fc7e76ebaf9"

PR = "u${PV_UPDATE}"
S = "${WORKDIR}"

do_install () {
  install -d -m 0755 ${D}${datadir}/jdk-${PV}_${PV_UPDATE}
  cp -a ${S}/zulu11.1.8-ca-jdk11-c2-linux_aarch32hf/* ${D}${datadir}/jdk-${PV}_${PV_UPDATE}
  install -d -m 0755 ${D}${bindir}
  ln -sf ${datadir}/jdk-${PV}_${PV_UPDATE}/bin/java ${D}${bindir}/java
}

# All the files are provided in a binaray package, and keeping all the
# files in a single package causes packaging QA errors and warnings.
# Avoid these packaging failure by skiping all the QA checks
INSANE_SKIP_${PN} = "${ERROR_QA} ${WARN_QA}"

FILES_${PN} = "/usr/"
RPROVIDES_${PN} = "zulu-jdk virtual/java"
PROVIDES += "virtual/java"

DEPENDS = "alsa-lib libxi libxrender libxtst"

