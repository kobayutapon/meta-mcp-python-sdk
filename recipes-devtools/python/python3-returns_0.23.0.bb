# python3-returns_0.23.0.bb

SUMMARY = "Composable functional helpers for Python (monads, Result, Future, etc.)"
HOMEPAGE = "https://github.com/dry-python/returns"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=155fbcc756c8ae5265d252d23e20908f"

PV = "0.23.0"

# PyPI source sdist（アンダースコア版パス）  
SRC_URI = "https://files.pythonhosted.org/packages/52/b4/411f1e4026344f0ccabc5654aee02280ebe8a9783756e53c76437693dc9a/returns-${PV}.tar.gz"
SRC_URI[sha256sum] = "27594c28e5fc338e052d27ddf77fe1da82db4472f6d59901e7e9165be35a5256"

# 展開先ディレクトリ
S = "${WORKDIR}/returns-${PV}"

inherit pypi \
       python_pep517 \
       python3native \
       python3-dir \
       python3targetconfig \
       setuptools3-base

# ネイティブ版も生成
BBCLASSEXTEND = "native"

# ビルドバックエンドに必要なネイティブ依存
DEPENDS = "python3-poetry-core-native"

# ライセンスチェックを飛ばしたい場合（MD5をまだセットしていないときなど）
INSANE_SKIP_${PN} += " license"
