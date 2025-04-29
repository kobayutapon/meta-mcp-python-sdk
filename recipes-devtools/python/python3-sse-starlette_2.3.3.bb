SUMMARY = "SSE plugin for Starlette"
DESCRIPTION = "Server-Sent Events support for Starlette and FastAPI"
HOMEPAGE = "https://github.com/sysid/sse-starlette"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=512780230e4edd77125d98c5f52abafe"

PYPI_PACKAGE = "sse-starlette"
PV = "2.3.3"

SRC_URI = "https://files.pythonhosted.org/packages/source/s/${PYPI_PACKAGE}/sse_starlette-${PV}.tar.gz"
SRC_URI[sha256sum] = "fdd47c254aad42907cfd5c5b83e2282be15be6c51197bf1a9b70b8e990522072"

inherit setuptools3

S = "${WORKDIR}/sse_starlette-${PV}"

# pyproject.toml しかないので簡易 setup.py を生成
do_configure:prepend() {
    cat << EOF > ${S}/setup.py
from setuptools import setup, find_packages

setup(
    name="${PYPI_PACKAGE}",
    version="${PV}",
    packages=find_packages(),
    install_requires=[
        "anyio>=4.7.0",
        "starlette>=0.41.3",
    ],
    extras_require={
        "uvicorn": ["uvicorn>=0.34.0"],
    },
)
EOF
}

DEPENDS = "\
    python3-setuptools-native \
    python3-wheel-native \
"

RDEPENDS:${PN} = "\
    python3-anyio \
    python3-starlette \
"

# モジュール本体とメタデータをパッケージに含める
FILES:${PN} += "\
    ${PYTHON_SITEPACKAGES_DIR}/sse_starlette* \
    ${PYTHON_SITEPACKAGES_DIR}/sse_starlette-${PV}-py*.egg-info \
"
