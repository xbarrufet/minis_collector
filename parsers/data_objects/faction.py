

from aux.data_object_base import DataObjectBase


class FactionDTO(DataObjectBase):
    def __init__(self,  faction_id,edition_id, faction_name, alliance, rules):
        super().__init__()
        self.faction_id = faction_id
        self.edition_id = edition_id
        self.faction_name = faction_name
        self.alliance = alliance
        self.rules = rules
        
    def __repr__(self):
        return super().__repr__()

    def __str__(self):
        return super().__str__()