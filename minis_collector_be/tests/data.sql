insert into game (game_id, game_name, game_description) values 
    ('AoS'.'Age of Sigmar', 'Age of Sigmar is a tabletop wargame introduced by Games Workshop in 2015. It presents the fantasy world of the Mortal Realms, where gods, monsters, and magic reign supreme.', 'Age of Sigmar is a tabletop wargame introduced by Games Workshop in 2015. It presents the fantasy world of the Mortal Realms, where gods, monsters, and magic reign supreme.'),
    ('40k'.'Warhammer 40,000', 'Warhammer 40,000 is a tabletop wargame introduced by Games Workshop in 1987. It presents a dystopian future where humanity is at war with various enemies');

insert into game_edition(game_edition_id, game_edition_name, game_edition_game_id) values 
    ('AoS_4', 'AoS', 'Age of Sigmar 4th Edition'),
    ('40K_10', '40K', 'Warhammer 40.000 10th Edition');




