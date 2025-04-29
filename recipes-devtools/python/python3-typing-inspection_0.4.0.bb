# python3-typing-inspection_0.4.0.bb

SUMMARY = "Runtime typing introspection tools for Python’s typing module"
HOMEPAGE = "https://github.com/pydantic/typing-inspection"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=07cbaa23fc9dd504fc1ea5acc23b0add"

PV = "0.4.0"

SRC_URI = "https://files.pythonhosted.org/packages/82/5c/e6082df02e215b846b4b8c0b887a64d7d08ffaba30605502639d44c06b82/typing_inspection-${PV}.tar.gz"
SRC_URI[sha256sum] = "9765c87de36671694a67904bf2c96e395be9c6439bb6c87b5142569dcdd65122"

# PyPI の sdist は typing_inspection-0.4.0/ に展開される
S = "${WORKDIR}/typing_inspection-${PV}"

inherit python_pep517 \
       python3native \
       python3-dir \
       python3targetconfig \
       setuptools3-base

# このレシピからネイティブ版（-native）も生成
BBCLASSEXTEND = "native"

# PEP 517 build-backend(hatchling.build) 用のネイティブ依存
DEPENDS = "python3-hatchling-native"

# 実行時には typing‐extensions を依存に
RDEPENDS_${PN} = "python3-typing-extensions"


# ビルド前に未知のクラスファイアを削除する
do_configure:prepend() {
    sed -i '/Programming Language :: Python :: 3\.14/d' ${S}/pyproject.toml
}
# --- ここまで ---


# LICENSE MD5 をまだセットしていない場合のみ一時的にスキップ
INSANE_SKIP_${PN} += " license"
