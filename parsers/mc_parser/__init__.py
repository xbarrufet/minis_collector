"""Top-level package for minis collector parser"""
# mc_parser/__init__.py

__app_name__ = "mc_parser"
__version__ = "0.1.0"

(
    SUCCESS,
    URL_ERROR,
    DIR_ERROR,
    FILE_ERROR,
    REST_API_ERROR,
    JSON_ERROR,
    ID_ERROR,
) = range(7)

ERRORS = {
    URL_ERROR: "error accessing remote URL",
    DIR_ERROR:"Access directory error",
    FILE_ERROR: "Access file error",
    REST_API_ERROR: "mc rest api error",
    JSON_ERROR: "json creation error",
    ID_ERROR: "to-do id error"
}