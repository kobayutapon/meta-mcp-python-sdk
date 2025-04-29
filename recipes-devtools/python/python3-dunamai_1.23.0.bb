# python3-dunamai_1.23.0.bb

SUMMARY = "Dynamic version generation library and CLI"
HOMEPAGE = "https://github.com/mtkennerly/dunamai"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=059eed55dbfd3fea022510ea62c95dc1"
PV = "1.23.0"

# PyPI sdist の URL
SRC_URI = "https://files.pythonhosted.org/packages/source/d/dunamai/dunamai-${PV}.tar.gz"
SRC_URI[sha256sum] = "a163746de7ea5acb6dacdab3a6ad621ebc612ed1e528aaa8beedb8887fccd2c4"

# 展開先ディレクトリを明示
S = "${WORKDIR}/dunamai-${PV}"

inherit pypi \
       python_pep517 \
       python3native \
       python3-dir \
       python3targetconfig \
       setuptools3-base

# native レシピも自動生成
BBCLASSEXTEND = "native"

# ビルド時に必要なバックエンド
DEPENDS = "python3-poetry-core-native"

# 実行時依存（packaging と backport の importlib-metadata）
RDEPENDS_${PN} = "\
    python3-packaging \
    python3-importlib-metadata \
"
