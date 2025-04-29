# python3-annotated-types_0.7.0.bb

SUMMARY = "Reusable constraint types to use with typing.Annotated"
HOMEPAGE = "https://github.com/annotated-types/annotated-types"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c6afb13fdc220497ee5cded1e717ed67"

PV = "0.6.0"


SRC_URI = "https://files.pythonhosted.org/packages/source/a/annotated_types/annotated_types-${PV}.tar.gz"
SRC_URI[sha256sum] = "563339e807e53ffd9c267e99fc6d9ea23eb8443c08f112651963e24e22f84a5d"

# PyPI の sdist は annotated_types-${PV}/ 以下に展開される
S = "${WORKDIR}/annotated_types-${PV}"

inherit \
       python_pep517 \
       python3native \
       python3-dir \
       python3targetconfig \
       setuptools3-base

# 同一レシピからネイティブ版も生成
BBCLASSEXTEND = "native"

# ビルド時に必要となる PEP-517 バックエンド
DEPENDS = " \
    python3-hatchling-native \
"

# ランタイム依存（Python 3.8 以下向けに typing-extensions を要求）
RDEPENDS_${PN} = " \
    python3-typing-extensions \
"

# ライセンスファイルの md5 をまだセットしていない場合は一時的にスキップ
INSANE_SKIP_${PN} += " license"
