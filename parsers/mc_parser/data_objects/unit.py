
import json
from mc_parser.aux.data_object_base import DataObjectBase


class UnitDTO(DataObjectBase):
    def __init__(self, unit_id:str, unit_name:str, unit_description:str, faction_id:str, abilities:dict, composition:json, keywords:list,profiles:list,allowed_sizes:json,costs:json):    
        super().__init__()
        self.unit_id = unit_id
        self.unit_name = unit_name
        self.unit_description = unit_description
        self.faction_id = faction_id
        self.abilities = abilities
        self.allowed_sizes = allowed_sizes
        self.costs = costs
        self.composition = composition
        self.keywords = keywords
        self.profiles = profiles

    def to_dict(self):
        return self.__dict__



class UnitProfileDTO(DataObjectBase):
    def __init__(self, profile_id:str, profile_name:str,attributes:dict, base_cost:int, is_default:bool, loadout_configuration:json, loadouts:list):
        super().__init__()
        self.profile_id = profile_id
        self.profile_name = profile_name
        self.attributes = attributes
        self.is_default = False
        self.base_cost = base_cost
        self.loadout_configuration = loadout_configuration
        self.loadouts = loadouts
        
    def to_dict(self):
        return self.__dict__

class UnitProfileLoadoutDTO(DataObjectBase):
    def __init__(self, loadout_id:str,is_default:bool, base_cost:int,attributes:dict, abilities:str, loadout_name:str):
        super().__init__()
        self.loadout_id = loadout_id
        self.attributes = attributes
        self.base_cost = base_cost
        self.abilities = abilities
        self.is_default = False
        self.loadout_name = loadout_name
        
    def to_dict(self):
        return self.__dict__
    
    