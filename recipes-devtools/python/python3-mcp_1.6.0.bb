# recipes-devtools/python/python3-mcp/python3-mcp_1.6.0.bb

SUMMARY = "Model Context Protocol SDK"
DESCRIPTION = "The official Python SDK for Model Context Protocol servers and clients"
HOMEPAGE = "https://github.com/modelcontextprotocol/python-sdk"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7ae711d8a91d3871696f50e34ad3c2d7"

PYPI_PACKAGE = "mcp"
PV = "1.6.0"

SRC_URI = "https://files.pythonhosted.org/packages/source/m/mcp/${PYPI_PACKAGE}-${PV}.tar.gz"
SRC_URI[sha256sum] = "d9324876de2c5637369f43161cd71eebfd803df5a95e46225cab8d280e366723"

inherit pypi setuptools3

S = "${WORKDIR}/${PYPI_PACKAGE}-${PV}"

# PEP 517 設定を無効化して、自前の setup.py を生成
do_configure:prepend() {
    rm -f ${S}/pyproject.toml

    cat << 'EOF' > ${S}/setup.py
from setuptools import setup, find_packages

setup(
    name="mcp",
    version="1.6.0",
    package_dir={"": "src"},
    packages=find_packages(where="src"),
    install_requires=[
        "anyio>=4.7.0",
        "httpx>=0.24.0",
        "starlette>=0.41.3",
        "pydantic>=2.0",
        "uv-dynamic-versioning>=0.15.1",
    ],
    extras_require={
        "cli": ["typer>=0.8.1", "python-dotenv>=1.0.0"],
        "rich": ["rich>=13.3.4"],
        "ws": ["websockets>=11.0.3"],
    },
    entry_points={
        "console_scripts": [
            "mcp = mcp.cli:main",
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

# ランタイム依存
RDEPENDS:${PN} = "\
    python3-anyio \
    python3-httpx \
    python3-starlette \
    python3-pydantic \
    python3-pydantic-core \
    python3-annotated-types \
    python3-uvicorn \
    python3-tomlkit \
    python3-dunamai \
    python3-returns \
    python3-pydantic-settings \
    python3-sse-starlette \
    python3-uv-dynamic-versioning \
"

RDEPENDS:${PN}-cli  = "python3-typer python3-python-dotenv"
RDEPENDS:${PN}-rich = "python3-rich"
RDEPENDS:${PN}-ws   = "python3-websockets"

# インストールされたモジュール群をパッケージに含める
FILES:${PN} += "${PYTHON_SITEPACKAGES_DIR}/mcp ${PYTHON_SITEPACKAGES_DIR}/mcp* ${PYTHON_SITEPACKAGES_DIR}/mcp/*"
FILES:${PN}-cli   += "${PYTHON_SITEPACKAGES_DIR}/mcp/cli*"
FILES:${PN}-rich  += "${PYTHON_SITEPACKAGES_DIR}/mcp/rich*"
FILES:${PN}-ws    += "${PYTHON_SITEPACKAGES_DIR}/mcp/ws*"
