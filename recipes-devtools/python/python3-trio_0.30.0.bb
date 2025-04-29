SUMMARY = "A friendly Python library for async concurrency and I/O"
HOMEPAGE = "https://github.com/python-trio/trio"
LICENSE = "Apache-2.0 & MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=447ea202d14d2aee40d8a2c26c865da9 \
                    file://LICENSE.APACHE2;md5=3b83ef96387f14655fc854ddc3c6bd57 \
                    file://LICENSE.MIT;md5=5f229c828e5a6f0a2ce90c7d3c054721"

SRC_URI[sha256sum] = "0781c857c0c81f8f51e0089929a26b5bb63d57f927728a5586f7e36171f064df"

inherit pypi setuptools3

# 簡易 setup.py を生成して PEP517 をバイパス
do_configure:prepend() {
    # pyproject.toml の内容を無効化
    rm -f ${S}/pyproject.toml

    cat << 'EOF' > ${S}/setup.py
from setuptools import setup, find_packages

setup(
    name="${PYPI_PACKAGE}",
    version="${PV}",
    # ソースは src/ 以下にある
    package_dir={"": "src"},
    packages=find_packages(where="src"),
    install_requires=[
        "idna>=3.3",
        "sniffio>=1.3.0",
        "attrs>=23.2.0",
        "outcome>=1.2.0",
        "sortedcontainers>=2.4.0",
    ],
)
EOF
}

# ビルド時に必要なネイティブツール
DEPENDS = " \
    python3-setuptools-native \
    python3-wheel-native \
"

# 実行時依存
RDEPENDS:${PN} = " \
    python3-idna \
    python3-sniffio \
    python3-attrs \
    python3-outcome \
    python3-sortedcontainers \
"

# Site-packages 以下をパッケージに含める
FILES:${PN} += "${PYTHON_SITEPACKAGES_DIR}/trio*"

