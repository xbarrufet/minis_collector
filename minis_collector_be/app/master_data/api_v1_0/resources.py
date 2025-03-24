from flask import request, Blueprint
from flask_restful import Api, Resource
from .schemas import FactionSchema, GameEditionSchema, GameSchema
from ..models import Faction, Game, GameEdition

master_data_v1_0_bp = Blueprint('master_data_v1_0_bp', __name__)
game_schema = GameSchema()
game_edition_schema = GameEditionSchema()
faction_schema = FactionSchema()

api = Api(master_data_v1_0_bp)

class GameListResource(Resource):
    def get(self):
        games = Game.get_all()
        result = game_schema.dump(games, many=True)
        return result
    
    def post(self):
        data = request.get_json()
        game_dict = game_schema.load(data)
        game = Game(game_id=game_dict['game_id'],
                    game_name=game_dict['game_name'],
                    game_description=game_dict['game_description'],
                    
        )
    
        game.save()
        resp = game_schema.dump(game)
        return resp, 201

class GameResource(Resource):
    def get(self, game_id):
        game = Game.get_by_id(game_id)
        result = game_schema.dump(game)
        return result
    
    
class GameEditionListResource(Resource):
    def get(self):
        game_editions = GameEdition.get_all()
        result = game_edition_schema.dump(game_editions, many=True)
        return result
    
    def post(self):
        data = request.get_json()
        game_edition_dict = game_edition_schema.load(data)
        game_edition = GameEdition(game_edition_id=game_edition_dict['game_edition_id'],
                    game_edition_game_id=game_edition_dict['game_edition_game_id'],
                    game_edition_name=game_edition_dict['game_edition_name'],
                    
        )
    
        game_edition.save()
        resp = game_edition_schema.dump(game_edition)
        return resp, 201
    
class GameEditionResource(Resource):
    def get(self, game_edition_id):
        game_edition = GameEdition.get_by_id(game_edition_id)
        result = game_edition_schema.dump(game_edition)
        return result

class FactionListResource(Resource):
    def get(self):
        factions = Faction.get_all()
        result = faction_schema.dump(factions, many=True)
        return result 

    def post(self):
        data = request.get_json()
        faction_dict = faction_schema.load(data)
        faction = Faction(faction_id=faction_dict['faction_id'],
                    faction_game_edition_id=faction_dict['faction_game_edition_id'],
                    faction_name=faction_dict['faction_name'],
                    faction_alliance=faction_dict['faction_alliance'],
                    faction_rules=faction_dict['faction_rules']
        )
    
        faction.save()
        resp = faction_schema.dump(faction)
        return resp, 201

class FactionResource(Resource):
    def get(self, faction_id):
        faction = Faction.get_by_id(faction_id)
        result = faction_schema.dump(faction)
        return result
    
   

api.add_resource(GameListResource, '/api/v1.0/masterdata/games/', endpoint='game_list_resource')
api.add_resource(GameResource, '/api/v1.0/films/<string:game_id>', endpoint='game_resource')
api.add_resource(GameEditionListResource, '/api/v1.0/masterdata/game_editions/', endpoint='game_edition_list_resource')
api.add_resource(GameEditionResource, '/api/v1.0/masterdata/game_editions/<string:game_edition_id>', endpoint='game_edition_resource')
api.add_resource(FactionListResource, '/api/v1.0/masterdata/factions/', endpoint='faction_list_resource')
api.add_resource(FactionResource, '/api/v1.0/masterdata/factions/<string:faction_id>', endpoint='faction_resource')