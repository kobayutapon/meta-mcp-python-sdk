SUMMARY = "ASGI web server implementation for Python"
DESCRIPTION = "High-performance ASGI web server based on the ASGI specification"
HOMEPAGE = "https://github.com/encode/uvicorn"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=5c778842f66a649636561c423c0eec2e"

PYPI_PACKAGE = "uvicorn"
PV = "0.34.2"

SRC_URI = "https://files.pythonhosted.org/packages/source/u/${PYPI_PACKAGE}/${PYPI_PACKAGE}-${PV}.tar.gz"
SRC_URI[sha256sum] = "0e929828f6186353a80b58ea719861d2629d766293b6d19baf086ba31d4f3328"

inherit pypi setuptools3

S = "${WORKDIR}/${PYPI_PACKAGE}-${PV}"

# PEP 517 をバイパスし、自前の setup.py でパッケージを組み立てる
do_configure:prepend() {
    # 元の pyproject.toml を除去
    rm -f ${S}/pyproject.toml

    # setup.py を生成
    cat << 'EOF' > ${S}/setup.py
from setuptools import setup, find_packages

setup(
    name="uvicorn",
    version="${PV}",
    # ソースツリー直下の "uvicorn" ディレクトリを自動検出
    packages=find_packages(),
    install_requires=[
        "click>=8.0",
        "h11>=0.8,<1",
        "anyio>=3.5.0,<5",
    ],
    entry_points={
        "console_scripts": [
            "uvicorn = uvicorn.main:main",
        ],
    },
)
EOF
}


do_install:append() {
    # テスト関連ファイルとディレクトリをインストール場所から削除
    # ${D} はパッケージのインストール先ルートディレクトリを示します
    # ${python_sitedir} はターゲットの Python site-packages ディレクトリを示します (例: /usr/lib/python3.12/site-packages)
    rm -rf ${D}${PYTHON_SITEPACKAGES_DIR}/tests
    # コンパイル済みバイトコードファイルも削除が必要な場合
    rm -rf ${D}${PYTHON_SITEPACKAGES_DIR}/__pycache__/tests
    # examples なども不要であれば同様に削除
    # rm -rf ${D}${python_sitedir}/examples
}

# ビルド時依存
DEPENDS = "\
    python3-setuptools-native \
    python3-wheel-native \
"

# 実行時依存
RDEPENDS:${PN} = "\
    python3-click \
    python3-h11 \
    python3-anyio \
"

# パッケージング対象
FILES:${PN} += "\
    ${PYTHON_SITEPACKAGES_DIR}/uvicorn* \
    ${bindir}/uvicorn \
"
