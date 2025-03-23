from aux.data_object_base import DataObjectBase


class MinisPackModelGroupUnitDTO(DataObjectBase):
    def __init__(self, model_group_id, unit_id, is_primary):
        self.model_group_id = model_group_id
        self.unit_id = unit_id
        self.is_primary = is_primary


class MinisPackModelGroupDTO(DataObjectBase):
    def __init__(self, model_group_id, pack_id, model_group_name, quantity, image_url):
        self.model_group_id = model_group_id
        self.pack_id = pack_id
        self.model_group_name = model_group_name
        self.quantity = quantity
        self.image_url = image_url

class MinisPackModelSetProfileDTO(DataObjectBase):
    def __init__(self, model_group_id, unit_id, profile_id, is_default, quantity, loadout_configuration, image_url):
        self.model_group_id = model_group_id
        self.unit_id = unit_id
        self.profile_id = profile_id
        self.is_default = is_default
        self.quantity = quantity
        self.loadout_configuration = loadout_configuration
        self.image_url = image_url

class MinisPackDTO(DataObjectBase):
    def __init__(self, pack_id, pack_name, pack_description, company, original, cost, buying_date):
        self.pack_id = pack_id
        self.pack_name = pack_name
        self.pack_description = pack_description
        self.company = company
        self.original = original
        self.cost = cost
        self.buying_date = buying_date