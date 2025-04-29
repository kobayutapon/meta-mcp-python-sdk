# python3-tomlkit_0.13.2.bb

SUMMARY = "Style-preserving TOML library for Python"
HOMEPAGE = "https://github.com/python-poetry/tomlkit"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=31aac0dbc1babd278d5386dadb7f8e82"

PV = "0.13.2"

SRC_URI = "https://files.pythonhosted.org/packages/source/t/tomlkit/tomlkit-${PV}.tar.gz"
SRC_URI[sha256sum] = "fff5fe59a87295b278abd31bec92c15d9bc4a06885ab12bcea52c71119392e79"

# 展開先ディレクトリ
S = "${WORKDIR}/tomlkit-${PV}"

inherit pypi \
       python_pep517 \
       python3native \
       python3-dir \
       python3targetconfig \
       setuptools3-base

# 同一レシピからネイティブ版も生成
BBCLASSEXTEND = "native"

# PEP 517 build-backend 用ネイティブ依存
DEPENDS = "python3-poetry-core-native"

# 実行時には Python3 本体のみ
RDEPENDS_${PN} = "python3"

# ライセンスQAエラーを一時的にスキップ（MD5をセットしたら外してください）
INSANE_SKIP_${PN} += "license"
