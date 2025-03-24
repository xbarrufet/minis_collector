from app.db import db, BaseModelMixin

class Game(db.Model, BaseModelMixin):
    game_id = db.Column(db.String, primary_key=True)
    game_name= db.Column(db.String)
    game_description = db.Column(db.Integer)
    game_editions=db.relationship('GameEdition', back_populates='game', cascade='all, delete-orphan')
    
    def __init__(self,game_id, game_name, game_description):
        self.game_id = game_id
        self.game_name = game_name
        self.game_description = game_description
        
    def __repr__(self):
        return f'Game({self.game_id}:{self.game_name})'
    def __str__(self):
        return f'Game({self.game_id}:{self.game_name})'

class GameEdition(db.Model, BaseModelMixin):
    game_edition_id = db.Column(db.String, primary_key=True)
    game_edition_name = db.Column(db.String)
    game_edition_game_id = db.Column(db.String, db.ForeignKey('game.game_id'))
    game = db.relationship('Game', back_populates='game_editions')
    factions = db.relationship('Faction', back_populates='game_edition', cascade='all, delete-orphan')
   
    
    def __init__(self,game_edition_id, game_edition_game_id, game_edition_name):
        self.game_edition_id = game_edition_id
        self.game_edition_game_id = game_edition_game_id
        self.game_edition_name = game_edition_name
        
    def __repr__(self):
        return f'GameEdition({self.game_edition_id}:{self.game_edition_name})'
    def __str__(self):
        return f'GameEdition({self.game_edition_id}:{self.game_edition_name})'
    
class Faction(db.Model, BaseModelMixin):
    faction_id = db.Column(db.String, primary_key=True)
    faction_name = db.Column(db.String)
    faction_game_edition_id = db.Column(db.String, db.ForeignKey('game_edition.game_edition_id'))
    faction_alliance = db.Column(db.String)
    faction_rules = db.Column(db.JSON)
    
    game_edition = db.relationship('GameEdition', back_populates='factions')
    
    def __init__(self,faction_id, faction_game_edition_id, faction_name, faction_alliance, faction_rules):
        self.faction_id = faction_id
        self.faction_name = faction_name
        self.faction_game_edition_id = faction_game_edition_id
        self.faction_alliance = faction_alliance
        self.faction_rules = faction_rules
        
    def __repr__(self):
        return f'Faction({self.faction_id}:{self.faction_name})'
    def __str__(self):
        return f'Faction({self.faction_id}:{self.faction_name})'