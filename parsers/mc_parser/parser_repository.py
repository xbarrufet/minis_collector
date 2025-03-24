import sqlite3
import json

from aux.singleton_meta import SingletonMeta
from app.data_objects.faction import FactionDTO
from app.data_objects.game import GameDTO,GameEditionDTO
from app.data_objects.unit import UnitDTO

DB_FILE = 'listmaster.db'

class ParserRepository(metaclass=SingletonMeta):
    
    
    def __init__(self, db_file=DB_FILE):
        self.conn = sqlite3.connect(db_file)
        self.cursor = self.conn.cursor()
        
        
    def create_db(self):
        script = open('listmaster_db.sql', 'r').read()
        self.cursor.executescript(script)
        self.conn.commit()
    
    
    
    def persist_game(self,game:GameDTO, self_commit=False):
        try:
            self.cursor.execute('INSERT INTO games (game_id,game_name,game_description) VALUES (?,?,?)', (game.game_id, game.game_name, game.game_description))
            if self_commit:
                self.conn.commit()
        except Exception as err:
            print('Insert Game Failed: %s\nError: %s' %  str(err, game))
    
    def pesist_game_edition(self, gameEdition:GameEditionDTO, self_commit=False):
        try:
            self.cursor.execute('INSERT OR REPLACE  INTO game_editions (edition_id, edition_name, game_id) VALUES (?,?,?)', (gameEdition.edition_id, gameEdition.edition_name, gameEdition.game_id))
            if self_commit:
                self.conn.commit()
        except Exception as err:
            print('Insert GameEdition Failed: %s\nError: %s' %  str(err, gameEdition))
   
    def persist_faction(self,faction:FactionDTO, self_commit=False):
        try:
            self.cursor.execute('INSERT OR REPLACE  INTO factions (faction_id,edition_id,faction_name,alliance,rules) VALUES (?,?,?,?,?)', (faction.faction_id, faction.edition_id, faction.faction_name, faction.alliance, faction.rules))
            if self_commit:
                self.conn.commit()
        except Exception as err:
            print('Insert Faction Failed: %s\nError: %s' %  str(err, faction))
    
    def persist_unit(self, unit: UnitDTO, self_commit=False):
        try:
            self.cursor.execute('INSERT OR REPLACE INTO units (unit_id, unit_name, unit_description, faction_id, abilities, composition, keywords, allowed_sizes, costs) VALUES (?,?,?,?,?,?,?,?,?)', 
                                (unit.unit_id, unit.unit_name, unit.unit_description, unit.faction_id, json.dumps(unit.abilities), json.dumps(unit.composition), json.dumps(unit.keywords), json.dumps(unit.allowed_sizes), json.dumps(unit.costs)))
            for profile in unit.profiles:
                 profile_sql = 'INSERT OR REPLACE  INTO unit_profiles (profile_id,profile_name,unit_id,is_default,base_cost,attributes,loadout_configuration) VALUES (?,?,?,?,?,?,?)'
                 self.cursor.execute(profile_sql,
                                    (profile.profile_id, 
                                     profile.profile_name, 
                                     unit.unit_id,
                                     profile.is_default, 
                                     profile.base_cost, 
                                     json.dumps(profile.attributes), 
                                     json.dumps(profile.loadout_configuration)
                                     ))
                 for loadout in profile.loadouts:
                     loadout_sql = "INSERT OR REPLACE  INTO unit_profile_loadouts (loadout_id,profile_id,base_cost,attributes,abilities,loadout_name,is_default) VALUES (?,?,?,?,?,?,?)"
                     self.cursor.execute(loadout_sql, 
                                        (loadout.loadout_id, 
                                         profile.profile_id, 
                                         loadout.base_cost, 
                                         json.dumps(loadout.attributes), 
                                         json.dumps(loadout.abilities), 
                                         loadout.loadout_name, 
                                         loadout.is_default))
            if self_commit:
                self.conn.commit()
        except Exception as err:
            print('Insert Unit Failed: %s\nError: %s' % (str(err), unit))
    
    def commit(self):
        self.conn.commit()
    
    def close(self):
        self.cursor.close()
        self.conn.close()
        
    def __del__(self):
        self.close()
        
