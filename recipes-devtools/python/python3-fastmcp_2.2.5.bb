# python3-fastmcp_2.2.5.bb
# Yoctoレシピ: PyPI の fastmcp を組み込む
# NOTE: LIC_FILES_CHKSUM の md5 は展開後の LICENSE ファイルから取得してください

SUMMARY = "FastMCP: Pythonic framework for building MCP servers"
HOMEPAGE = "https://github.com/jlowin/fastmcp"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2b42edef8fa55315f34f2370b4715ca9"

PV = "2.2.5"
PYPI_PACKAGE = "fastmcp"

SRC_URI[md5sum] = "6ce53036d59daf6fc9e73da6194d2fde"
SRC_URI[sha256sum] = "8a36ecdaae698a665403acf52faefb367fbdcbc2f5c34847710f34c6b374cbc6"

inherit pypi python_pep517

DEPENDS = "\
    python3-setuptools-native \
    python3-hatchling-native \
    python3-pydantic-native \
    python3-uv-dynamic-versioning-native \
    python3-annotated-types-native \
"


RDEPENDS_${PN} = "python3python3-asyncio"


