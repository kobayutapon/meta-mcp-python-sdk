# recipes-devtools/python/python3-python-dotenv/python3-python-dotenv_1.1.0.bb

SUMMARY = "Read key-value pairs from a .env file and set them as environment variables"
DESCRIPTION = "python-dotenv reads key-value pairs from a .env file and can set them as environment variables, following the 12-factor app principles."
HOMEPAGE = "https://github.com/theskumar/python-dotenv"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e914cdb773ae44a732b392532d88f072"

PYPI_PACKAGE = "python-dotenv"
PV = "1.1.0"

SRC_URI = "https://files.pythonhosted.org/packages/source/p/python-dotenv/python_dotenv-${PV}.tar.gz"
SRC_URI[sha256sum] = "41f90bc6f5f177fb41f53e87666db362025010eb28f60a01c9143bfa33a2b2d5"

inherit setuptools3

S = "${WORKDIR}/python_dotenv-${PV}"

# Build-time dependencies
DEPENDS = "\
    python3-setuptools-native \
    python3-wheel-native \
"

# No additional runtime deps beyond Python itself

# Ensure both the module and metadata get packaged
FILES:${PN} += "${PYTHON_SITEPACKAGES_DIR}/dotenv*"
