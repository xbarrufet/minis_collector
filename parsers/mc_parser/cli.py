"""This module provides the RP To-Do CLI."""
# rptodo/cli.py

from typing import Optional
import typer
import configparser

from mc_parser import __app_name__, __version__,config

app = typer.Typer()

def _version_callback(value: bool) -> None:
    if value:
        typer.echo(f"{__app_name__} v{__version__}")
        raise typer.Exit()

def _backend_url_callback(value: str) -> None:
    if value:
        config_file = configparser.ConfigParser()
        typer.echo(config_file[config.CONFIG_PARAM_BACKEND_URL])
        raise typer.Exit()

@app.callback()
def main(
    version: Optional[bool] = typer.Option(
        None,
        "--version",
        "-v",
        help="Show the application's version and exit.",
        callback=_version_callback,
        is_eager=True,
    ),
    backend_url:Optional[bool] = typer.Option(
        None,
        "--urlbackend",
        "-u",
        help="Show the application's version and exit.",
        callback=_backend_url_callback,
        is_eager=True,
    )
) -> None:
    return

@app.command()
def init( mc_backend_url: str = typer.Option(
            str(config.DEFAULT_MC_BACKEND_URL),
            "--backend-url",
            "-be",
            prompt="backend url?",
    ),
) -> None:
    """Initialize the application."""
    config_code = config.init_app(mc_backend_url)
    if config_code != config.SUCCESS:
        raise typer.Abort()
    

@app.command()
def generate(game_edition: str = typer.Option(
            "AoS_4",
            "--edition",
            "-e",
            prompt="Game edition?"),
            faction_id: str = typer.Option(
                "All",
                "--faction",
                "-f",
                prompt="Faction id?"),
        ) -> None:
    """Generate the RP To-Do."""
    typer.echo(f"Generating RP To-Do for edition {game_edition}. and faction {faction_id}")






    
