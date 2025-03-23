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
    