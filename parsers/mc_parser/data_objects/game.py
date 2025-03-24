import json

from aux.data_object_base import DataObjectBase

class GameDTO:
    def __init__(self, game_id, game_name, game_description):
        self.game_id = game_id
        self.game_name = game_name
        self.game_description = game_description
        

class GameEditionDTO(DataObjectBase):
    def __init__(self, edition_id, edition_name, game_id):
        super().__init__()
        self.edition_id = edition_id
        self.edition_name = edition_name
        self.game_id = game_id

    def __str__(self):
       return super().__str__()
   
    def __repr__(self):
       return super().__repr__()
