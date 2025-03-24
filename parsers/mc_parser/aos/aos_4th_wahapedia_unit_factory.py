

import json
import re

from mc_parser.aux import tools
from mc_parser.data_objects.unit import UnitDTO, UnitProfileDTO, UnitProfileLoadoutDTO

pattern = re.compile(r'\((\d+)(?:/(\d+))?\)')

def find_profile_grouping(keyword_line):
    matches = pattern.findall( keyword_line)
    # Procesar las coincidencias
    for match in matches:
        x = match[0]
        y = match[1] if match[1] else None
        return (x,y)
    return (1,None)

def _build_unit_profile_loadout( profile_id, is_profile_default, weapons):
    res=[]
    for w in weapons:
        loadout_id = profile_id + "_" + tools.build_id(w["name"])
        loadout_name = w["name"]
        attributes=""
        loadout_abilities = w["abilities"]
        if is_profile_default or profile_id.endswith("champion"):
            attributes = {"range":w["range"],"attacks":w["attacks"],"to_hit":w["to_hit"],"to_wound":w["to_wound"],"rend":w["rend"],"damage":w["damage"]}   
            if profile_id.endswith("champion"):
                if attributes["attacks"].isdigit():
                    attributes["attacks"] = str(int(attributes["attacks"])+1)
                else:
                    attributes["attacks"] = attributes["attacks"] + "+1"
            profile_loadout={}
            profile_loadout["loadout_id"]=loadout_id
            profile_loadout["loadout_name"]=loadout_name
            profile_loadout["base_cost"]=0
            profile_loadout["abilities"]=loadout_abilities
            profile_loadout["is_default"]=True
            profile_loadout["attributes"]=attributes        
        res.append(profile_loadout)
    return res

def __build_unit_profle(unit_name,composition, attributes, weapons):
    res=[]
    for p in composition:
        profile_id= p["profile"]
        profile_name = tools.build_name(profile_id)
        is_default = p["default"]
        unit_attributes=attributes if not is_default else ""
        loadouts = _build_unit_profile_loadout(profile_id, is_default, weapons)
        unit_profile={}
        unit_profile["profile_id"]=profile_id
        unit_profile["profile_name"]=profile_name
        unit_profile["is_default"]=is_default
        unit_profile["base_cost"]=0
        unit_profile["loadout_configuration"]=""
        unit_profile["attributes"]=unit_attributes
        unit_profile["loadouts"]=loadouts
        res.append(unit_profile)
    return res
            

def build_unit(unit_name,unit_description, faction,cost, size, reinforced,attributes, weapons,abilities,keywords,regiment_options,notes):
    if unit_name.lower().startswith("kragnos"):
        a=3
    unit_id =tools.build_id(unit_name)
    composition = __get_composition_from_keywords(unit_id, keywords[0])
    sizes = __get_size(size, reinforced)
    costs= __get_costs(cost,reinforced)
    profiles = __build_unit_profle(unit_name,composition, attributes, weapons)
    res = {}
    res["unit_id"]=unit_id
    res["unit_name"]=unit_name
    res["unit_description"]=unit_description
    res["faction_id"]=faction["faction_id"]
    res["abilities"]=abilities
    res["composition"]=composition
    res["allowed_sizes"]=json.dumps(sizes)
    res["costs"]=json.dumps(costs)
    res["keywords"]=keywords
    res["profiles"]=profiles
    res["regiment_options"]=regiment_options
    res["notes"]=notes
    return res
    # return UnitDTO(unit_id=unit_id,
    #             unit_name=unit_name,
    #             unit_description=unit_description,
    #             faction_id=faction_id,
    #             abilities=abilities,
    #             composition=composition,
    #             allowed_sizes=json.dumps(sizes),
    #             costs=json.dumps(costs),
    #             keywords=keywords,
    #             profiles=profiles)
    
    
    
def __build_keyword_profile(unit_id,profile_type,keyword):
        x,y = find_profile_grouping(keyword)
        res={"profile":unit_id + "_" + profile_type, "default":"False","min":0}
        if x:
            res["max"]=x
        if y:
            res["every"]=y
        return res
    
def __get_composition_from_keywords(unit_id, keywords_line):
        composition=[{"profile":unit_id, "default":"True"}]
        bearer = [x  for x in keywords_line if x.lower().startswith("standard bearer")]
        champion = [x  for x in keywords_line if x.lower().startswith("champion")]
        music = [x  for x in keywords_line if x.lower().startswith("music")]
        if len(bearer)>0:
            composition.append(__build_keyword_profile(unit_id,"standard_bearer",bearer[0]))
        if len(champion)>0:
            composition.append(__build_keyword_profile(unit_id,"champion",champion[0]))
        if len(music)>0:
            composition.append(__build_keyword_profile(unit_id,"musician",music[0]))
        return composition

def __get_size(size, reinforced):
    if size=="":
        size="1"
    res=[int(size)]
    if reinforced=="Yes":
        res.append(int(size)*2)
    return res

def __get_costs(cost, reinforced):
    if cost=="":
        cost="0"
    res=[int(cost)]
    if reinforced=="Yes":
        res.append(int(cost)*2)
    return res