

import json
import os
import re

JSON_DATA_FOLDER = "json_data"

digits = re.compile(r'\d+')

def build_id(string:str)->str:
    return string.replace(" ", "_").lower()

def build_name(string:str)->str:
    return string.replace("_", " ").title()

def get_first_digit(string:str)->int:
    match = digits.search(string)
    if match:
        return int(match.group())
    return 0

def get_all_digits(string:str)->list:
    return [int(match.group()) for match in digits.finditer(string)]


     
def create_db(self):
        script = open('listmaster_db.sql', 'r').read()
        self.cursor.executescript(script)
        self.conn.commit()
        
        
def save_file(data,game_edition_id,faction_id):
    
    game_edttion_path = os.path.join(JSON_DATA_FOLDER,game_edition_id)
    if not os.path.exists(game_edttion_path):
        os.makedirs(game_edttion_path)
    json.dump(data, open(os.path.join(game_edttion_path,faction_id+".json"), 'w'), indent=3)