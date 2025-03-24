from marshmallow import fields
from app.ext import ma

class GameSchema(ma.Schema):
    game_id = fields.String()
    game_name = fields.String()
    game_description = fields.String()
    game_editions = fields.Nested(lambda: GameEditionSchema(only=("game_edition_id", "game_edition_name")), many=True)
    
    
class GameEditionSchema(ma.Schema):
    game_edition_id = fields.String()
    game_edition_game_id = fields.String()
    game_edition_name = fields.String() 
    game = fields.Nested(GameSchema(exclude=("game_editions",)))
    factions = fields.Nested(lambda: FactionSchema(only=("faction_id", "faction_name")), many=True)
    
class FactionSchema(ma.Schema):
    faction_id = fields.String()
    faction_name = fields.String()
    faction_game_edition_id = fields.String()
    faction_alliance = fields.String()
    faction_rules = fields.Dict()
    game_edition = fields.Nested(lambda: GameEditionSchema(exclude=("factions",)))  