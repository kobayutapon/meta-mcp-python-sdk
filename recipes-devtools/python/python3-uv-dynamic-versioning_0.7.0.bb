
SUMMARY = "Dynamic versioning based on VCS tags for uv/hatch projects"
HOMEPAGE = "https://github.com/ninoseki/uv-dynamic-versioning"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=14d953809f6381e54162e13c2c846fbc"

PV = "0.7.0"

SRC_URI = "https://files.pythonhosted.org/packages/6d/dc/a920917a3029c5316a4b35cfd6896d38ad8680193e74f9a4af1d56a45912/uv_dynamic_versioning-${PV}.tar.gz"
SRC_URI[sha256sum] = "7aec8977a2fd8865b205b63fb39b69288455e0027802bcb2b6203026d957bb25"

S = "${WORKDIR}/uv_dynamic_versioning-${PV}"

inherit \
       python_pep517 \
       python3-dir \
       python3native \
       python3targetconfig \
       python_pep517 \
       setuptools3-base
 
BBCLASSEXTEND = "native"


# build-backend
# dunamai~=1.23
# pydantic~=2.10
# returns~=0.23
# tomlkit~=0.13
DEPENDS = "\
    python3-hatchling-native \
    python3-uv-dynamic-versioning-native \
    python3-dunamai-native \
    python3-pydantic-native \
    python3-returns-native \
    python3-tomlkit-native \
    python3-pydantic-core-native \
"