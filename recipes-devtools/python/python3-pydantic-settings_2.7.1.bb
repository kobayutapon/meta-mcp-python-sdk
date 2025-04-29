# recipes-devtools/python/python3-pydantic-settings/python3-pydantic-settings_2.7.1.bb

SUMMARY = "Settings management using Pydantic BaseSettings"
DESCRIPTION = "Official Pydantic BaseSettings library for loading settings from environment variables or secret files"
HOMEPAGE = "https://github.com/pydantic/pydantic-settings"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9adde1a30a7e74a03e57e456551c19ae"

PYPI_PACKAGE = "pydantic-settings"
PV = "2.7.1"

SRC_URI = "https://files.pythonhosted.org/packages/source/p/pydantic-settings/pydantic_settings-${PV}.tar.gz"
SRC_URI[sha256sum] = "10c9caad35e64bfb3c2fbf70a078c0e25cc92499782e5200747f942a065dec93"

inherit setuptools3

# ソース展開ディレクトリ名は pydantic_settings-2.7.1
S = "${WORKDIR}/pydantic_settings-${PV}"

# PEP 517 無効化 → 自前の setup.py を生成
do_configure:prepend() {
    # PEP 517 用の設定を除去
    rm -f ${S}/pyproject.toml

    # minimal setup.py を書き出し
    cat << 'EOF' > ${S}/setup.py
from setuptools import setup, find_packages

setup(
    name="pydantic-settings",
    version="2.7.1",
    description="Settings management using Pydantic",
    author="Samuel Colvin",
    author_email="s@muelcolvin.com",
    url="https://github.com/pydantic/pydantic-settings",
    license="MIT",
    packages=find_packages(),
    package_dir={"": "."},
    python_requires=">=3.9",
    install_requires=[
        "pydantic>=2.7.0",
        "python-dotenv>=0.21.0",
        "typing-inspection>=0.4.0",
    ],
    extras_require={
        "yaml": ["pyyaml>=6.0.1"],
        "toml": ["tomli>=2.0.1"],
        "azure-key-vault": ["azure-keyvault-secrets>=4.8.0", "azure-identity>=1.16.0"],
        "aws-secrets-manager": ["boto3>=1.35.0", "boto3-stubs[secretsmanager]"],
        "gcp-secret-manager": ["google-cloud-secret-manager>=2.23.1"],
    },
)
EOF
}

# ビルド時依存
DEPENDS = "\
    python3-setuptools-native \
    python3-wheel-native \
"

# ランタイム依存
RDEPENDS:${PN} = "\
    python3-pydantic \
    python3-python-dotenv \
    python3-typing-inspection \
"

# site-packages 以下のモジュールを確実にパッケージに含める
FILES:${PN} += "${PYTHON_SITEPACKAGES_DIR}/pydantic_settings*"
