"""This module provides the RP To-Do config functionality."""
# mc_parser/config.py

import configparser
from pathlib import Path

import typer

from mc_parser import (
      FILE_ERROR, SUCCESS, DIR_ERROR,__app_name__
)

CONFIG_DIR_PATH = Path(typer.get_app_dir(__app_name__))
CONFIG_FILE_PATH = CONFIG_DIR_PATH / "config.ini"

DEFAULT_MC_BACKEND_URL = "http://localhost:5000"

CONFIG_PARAM_BACKEND_URL="mc_backend_url"

def init_app(mc_backend_url: str) -> int:
    """Initialize the application."""
    config_code = _init_config_file()
    if config_code != SUCCESS:
        return config_code
    config_code = _create_config_file(mc_backend_url)
    if config_code != SUCCESS:
        return config_code
    return SUCCESS

def _init_config_file() -> int:
    try:
        CONFIG_DIR_PATH.mkdir(exist_ok=True)
    except OSError:
        return DIR_ERROR
    try:
        CONFIG_FILE_PATH.touch(exist_ok=True)
    except OSError:
        return FILE_ERROR
    return SUCCESS

def _create_config_file(mc_backend_url: str) -> int:
    config_parser = configparser.ConfigParser()
    config_parser["General"] = {CONFIG_PARAM_BACKEND_URL: mc_backend_url}
    try:
        with CONFIG_FILE_PATH.open("w") as file:
            config_parser.write(file)
        print("Config file created at following location: ", CONFIG_FILE_PATH)
    except OSError:
        return FILE_ERROR
    return SUCCESS

def _get_config() -> configparser.ConfigParser:
    config_parser = configparser.ConfigParser()
    try:
        config_parser.read(CONFIG_FILE_PATH)
    except OSError:
        return None
    return config_parser