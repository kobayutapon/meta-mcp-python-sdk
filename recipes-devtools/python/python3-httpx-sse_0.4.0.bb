# python3-httpx-sse_0.4.0.bb
# Yocto レシピ: httpx-sse を OS イメージに組み込む

SUMMARY = "Consume Server-Sent Event (SSE) messages with HTTPX"
HOMEPAGE = "https://github.com/florimondmanca/httpx-sse"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=847662516af24b720ff6168b0dfcc6b1"

PV = "0.4.0"
PYPI_PACKAGE = "httpx-sse"

SRC_URI = "https://files.pythonhosted.org/packages/source/h/httpx-sse/${PYPI_PACKAGE}-${PV}.tar.gz"
SRC_URI[sha256sum] = "1e81a3a3070ce322add1d3529ed42eb5f70817f45ed6ec915ab753f961139721"

# inherit pypi python_pep517
inherit pypi setuptools3

# ビルド時依存 (PEP 517 のビルドツール)
DEPENDS = " \
    python3-setuptools-native \
    python3-setuptools-scm-native \
    python3-wheel-native \
"

# 実行時依存
RDEPENDS:${PN} = " \
    python3-httpx \
"

FILES:${PN} += "${PYTHON_SITEPACKAGES_DIR}/httpx_sse* ${PYTHON_SITEPACKAGES_DIR}/httpx_sse-${PV}-py*.egg-info"


