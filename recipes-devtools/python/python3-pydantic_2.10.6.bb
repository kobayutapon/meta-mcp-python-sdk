# python3-pydantic_2.11.3.bb

SUMMARY = "Data validation and settings management using Python type annotations"
HOMEPAGE = "https://github.com/pydantic/pydantic"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=09280955509d1c4ca14bae02f21d49a6"

PV = "2.10.6"

# PyPI ソース sdist（アンダースコア版パス）
SRC_URI = "https://files.pythonhosted.org/packages/10/2e/ca897f093ee6c5f3b0bee123ee4465c50e75431c3d5b6a3b44a47134e891/pydantic-${PV}.tar.gz"
SRC_URI[sha256sum] = "ca5daa827cce33de7a42be142548b0096bf05a7e7b365aebfa5f8eeec7128236"

# ソース展開先ディレクトリ
S = "${WORKDIR}/pydantic-${PV}"

inherit pypi \
       python_pep517 \
       python3native \
       python3-dir \
       python3targetconfig \
       setuptools3-base

# 同一レシピから native レシピも生成
BBCLASSEXTEND = "native"

# ビルド時に必要なバックエンド（pyproject.toml の build-backend を poetry-core が提供）
DEPENDS = " \
    python3-hatchling-native \
    python3-hatch-fancy-pypi-readme-native \
"
# 実行時に必要な依存（typing_extensions は Python <3.10 用のバックポート）
RDEPENDS_${PN} = "\
    python3-typing-extensions \
    python3-pydantic-core \
    python3-annotated-types \
    python3-typing-extensions \
"
