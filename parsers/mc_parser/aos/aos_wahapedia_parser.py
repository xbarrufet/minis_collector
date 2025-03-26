import json
import re
import requests
from bs4 import BeautifulSoup
from mc_parser.aux import tools
from mc_parser.aux.tools import build_id, get_first_digit, build_id
from mc_parser.aos.aos_4th_wahapedia_unit_factory import build_unit
from mc_parser.data_objects.faction import FactionDTO

AOS4_WAHAPEDIA_URL = "https://wahapedia.ru"


set_img=[]
battle_profle_groups=["Notes:","Regiment Options:","Can be reinforced:","Base size:","Points:","Unit Size:"]
weapon_attrs = ["name","range","attacks","to_hit","to_wound","rend","damage"]


factions_dict = {"cities_of_sigmar":{"faction_name": "Cities of Sigmar", "faction_url": "/aos4/factions/cities-of-sigmar", "alliance": "Order"}
,"daughters_of_khaine":{"faction_name": "Daughters of Khaine", "faction_url": "/aos4/factions/daughters-of-khaine", "alliance": "Order"}
,"fyreslayers":{"faction_name": "Fyreslayers", "faction_url": "/aos4/factions/fyreslayers", "alliance": "Order"}
,"idoneth_deepkin":{"faction_name": "Idoneth Deepkin", "faction_url": "/aos4/factions/idoneth-deepkin", "alliance": "Order"}
,"kharadron_overlords":{"faction_name": "Kharadron Overlords", "faction_url": "/aos4/factions/kharadron-overlords", "alliance": "Order"}
,"lumineth_realm-lords":{"faction_name": "Lumineth Realm-lords", "faction_url": "/aos4/factions/lumineth-realm-lords", "alliance": "Order"}
,"seraphon":{"faction_name": "Seraphon", "faction_url": "/aos4/factions/seraphon", "alliance": "Order"}
,"stormcast_eternals":{"faction_name": "Stormcast Eternals", "faction_url": "/aos4/factions/stormcast-eternals", "alliance": "Order"}
,"sylvaneth":{"faction_name": "Sylvaneth", "faction_url": "/aos4/factions/sylvaneth", "alliance": "Order"}
,"beasts_of_chaos":{"faction_name": "Beasts of Chaos", "faction_url": "/aos4/factions/beasts-of-chaos", "alliance": "Chaos"}
,"blades_of_khorne":{"faction_name": "Blades of Khorne", "faction_url": "/aos4/factions/blades-of-khorne", "alliance": "Chaos"}
,"disciples_of_tzeentch":{"faction_name": "Disciples of Tzeentch", "faction_url": "/aos4/factions/disciples-of-tzeentch", "alliance": "Chaos"}
,"hedonites_of_slaanesh":{"faction_name": "Hedonites of Slaanesh", "faction_url": "/aos4/factions/hedonites-of-slaanesh", "alliance": "Chaos"}
,"maggotkin_of_nurgle":{"faction_name": "Maggotkin of Nurgle", "faction_url": "/aos4/factions/maggotkin-of-nurgle", "alliance": "Chaos"}
,"skaven":{"faction_name": "Skaven", "faction_url": "/aos4/factions/skaven", "alliance": "Chaos"}
,"slaves_to_darkness":{"faction_name": "Slaves to Darkness", "faction_url": "/aos4/factions/slaves-to-darkness", "alliance": "Chaos"}
,"flesh-eater_courts":{"faction_name": "Flesh-eater Courts", "faction_url": "/aos4/factions/flesh-eater-courts", "alliance": "Death"}
,"nighthaunt":{"faction_name": "Nighthaunt", "faction_url": "/aos4/factions/nighthaunt", "alliance": "Death"}
,"ossiarch_bonereapers":{"faction_name": "Ossiarch Bonereapers", "faction_url": "/aos4/factions/ossiarch-bonereapers", "alliance": "Death"}
,"soulblight_gravelords":{"faction_name": "Soulblight Gravelords", "faction_url": "/aos4/factions/soulblight-gravelords", "alliance": "Death"}
,"bonesplitterz":{"faction_name": "Bonesplitterz", "faction_url": "/aos4/factions/bonesplitterz", "alliance": "Destruction"}
,"gloomspite_gitz":{"faction_name": "Gloomspite Gitz", "faction_url": "/aos4/factions/gloomspite-gitz", "alliance": "Destruction"}
,"ironjawz":{"faction_name": "Ironjawz", "faction_url": "/aos4/factions/ironjawz", "alliance": "Destruction"}
,"kruleboyz":{"faction_name": "Kruleboyz", "faction_url": "/aos4/factions/kruleboyz", "alliance": "Destruction"}
,"ogor_mawtribes":{"faction_name": "Ogor Mawtribes", "faction_url": "/aos4/factions/ogor-mawtribes", "alliance": "Destruction"}
,"sons_of_behemat":{"faction_name": "Sons of Behemat", "faction_url": "/aos4/factions/sons-of-behemat", "alliance": "Destruction"}
,"endless_spells":{"faction_name": "Endless Spells", "faction_url": "/aos4/factions/endless-spells", "alliance": "Other"}}



class FactionInfo(object):
    def __init__(self, faction_name, faction_url, alliance):
        self.faction_name = faction_name
        self.faction_url = faction_url
        self.alliance = alliance

    
    def __repr__(self):
        json.dumps(self.__dict__)


class AOSWahapediaParser(object):
        
    def __init__(self):
        pass
    
    
    
    def parse_all_factions_info(self):
        res = []
        url =AOS4_WAHAPEDIA_URL + "/aos4/the-rules/quick-start-guide/"
        page = requests.get(url)
        data = BeautifulSoup(page.content, "html.parser")
        alliances = data.find("div",class_="NavColumns3").find_all("div",class_="FactionHeader")
        for alliance in alliances:
            alliance_name = alliance.text.strip()
            for faction_data in alliance.parent.find_all("a"):
                res.append(FactionInfo(faction_data.text.strip(), faction_data['href'], alliance_name).__dict__)
        return res
    
    def _parse_faction_data(self,faction_url,edition_id, alliance)->FactionDTO:
        url = AOS4_WAHAPEDIA_URL + faction_url
        page = requests.get(url)
        data = BeautifulSoup(page.content.decode('utf-8', 'ignore'), "html.parser")
        faction_name = data.find("span",class_="page_header_span").text.strip()
        rules={}
        table_titles = data.find_all("td", class_="abHeader")
        table_titles = [t for t in table_titles if t.text.strip().lower()!="keywords"]
        for t in table_titles: 
            rule_family = t.find_previous("h2", class_="outline_header3")
            rule_text = t.find_next("div", class_="abBody")
            if rule_family.text.strip() not in rules:
                rules[rule_family.text.strip()] = []
            ability = self.__parse_rule_text(rule_text.text.strip())
            ability["when"]=t.text.strip()
            rules[rule_family.text.strip()].append(ability)
        res = {}
        res["faction_id"] = tools.build_id(faction_name)+"_"+edition_id
        res["edition_id"] = edition_id
        res["faction_name"] = faction_name
        res["alliance"] = alliance
        res["rules"] = rules
        return res
        
    def __parse_rule_text(self, rule_text):
        rule={}
        rule["name"] = ""
        rule["description"] = ""
        rule["declare"] = ""
        rule["effect"] = ""
        rule_effect_parts = rule_text.split("Effect:") 
        if len(rule_effect_parts)>1:
            rule["effect"] = rule_effect_parts[1].strip()
        rule_declare_parts = rule_effect_parts[0].split("Declare:")
        if len(rule_declare_parts)>1:
            rule["declare"] = rule_declare_parts[1].strip()
        rule_name_parts = rule_declare_parts[0].split(":")
        if len(rule_name_parts)>1:
            rule["name"] = rule_name_parts[0].strip()
            rule["description"] = "".join(rule_name_parts[1:]).strip()
        else:
            rule["name"] = rule_name_parts[0].strip()
        return rule
    
    
    def parse_all_warscrolls(self, faction_url, faction):
        url = AOS4_WAHAPEDIA_URL + faction_url + "/warscrolls"
        page = requests.get(url)
        data = BeautifulSoup(page.content, "html.parser")
        warscrolls = data.find_all("div", class_="datasheet")
        res = []
        for warscroll in warscrolls:
            res.append(self.__parse_warscroll(warscroll, faction))
        return res  
    
    
    
    def parse_single_warscroll_url(self, warscroll_url, faction):
        url = AOS4_WAHAPEDIA_URL + warscroll_url
        page = requests.get(url)
        data = BeautifulSoup(page.content, "html.parser")
        warscroll = data.find("div", class_="datasheet")
        return self.__parse_warscroll(warscroll, faction) 
      
        
    def __parse_warscroll(self, warscroll,faction):
        unit_name = warscroll.find("div", class_="wsHeaderIn").text.strip().replace("\n"," ")
        description, attributes = self.__parse_legend(warscroll)
        unit_size,points,base_size,reinforced,regiment_options,notes =self.__parse_battle_profile(warscroll)
        weapons=self.__parse_weapons(warscroll)
        abilities = self.__parse_warscroll_abilities(warscroll)
        keywords = self.__parse_keywords(warscroll)
        return build_unit(unit_name=unit_name,
                          unit_description=description, 
                          faction=faction,
                          attributes=attributes,
                          cost=points, 
                          size=unit_size, 
                          reinforced=reinforced,
                          weapons=weapons,
                          abilities=abilities,
                          keywords=keywords,
                          regiment_options=regiment_options,
                          notes=notes)
        #return (unit_name,description,attributes,unit_size,points,base_size,reinforced,regiment_options,notes,weapons,abilities,keywords)
    
    def __parse_legend(self, warscroll):
        attributes = {}
        legend = warscroll.find("div", class_="wsCharLegend")
        description=""
        if legend.find("div", class_="AoS_profile"):
            attributes["move"] =  legend.find("div", class_="wsMove").text.strip()
            attributes["health"] =  legend.find("div", class_="wsWounds").text.strip()
            attributes["save"] =  legend.find("div", class_="wsSave").text.strip()
            attributes["control"] =  legend.find("div", class_="wsBravery").text.strip()
        if legend.find("div", class_="wsLegend"):
            description=legend.find("div", class_="wsLegend").text.strip()
        return (description, attributes)
        
    def __parse_battle_profile(self, warscroll):
        if warscroll.find("div", class_="PitchedBattleProfile"):
            content = warscroll.find("div", class_="PitchedBattleProfile").text.strip()
            res={}
            positions=[]
            prev_pos =0
            for bp in battle_profle_groups:
                split = content.split(bp)
                if len(split)>1:
                    res[bp[:-1].lower()] = split[1].strip()
                else:
                    res[bp[:-1].lower()] = ""
                content=split[0]
            res['base size'] =tools.get_first_digit(res['base size'])
            res['points'] =tools.get_first_digit(res['points'])
            res['unit size'] =tools.get_first_digit(res['unit size'])
            return (res["unit size"],res["points"],res["base size"],res["can be reinforced"],res["regiment options"],res["notes"])   
        return ("","","","","","")
    
    def __parse_weapons(self,warscroll):
        weapons = []
        ranged =warscroll.find("div", class_="wsHeaderCellName_RangedWeapons")
        if warscroll.find("div",class_="wsBodyTop").find("table", class_="wTable"):
            table =warscroll.find("div",class_="wsBodyTop").find("table", class_="wTable")
            tbodies = table.find_all("tbody", class_="bkg")
            for tbody in tbodies:
                trs = tbody.find_all("tr")[1::2]
                for tr in trs:
                    damage,rend,to_wound,to_hit,attacks,range,name,abilities="","","","","","","",""
                    tds = tr.find_all("td")
                    if len(tds)>6:
                        damage = tds.pop().text.strip()
                        rend = tds.pop().text.strip() 
                        to_wound = tds.pop().text.strip()
                        to_hit = tds.pop().text.strip()
                        attacks= tds.pop().text.strip()
                        if ranged:
                            range= tds.pop().text.strip()
                        else:
                            range = ""
                        list_name = tds.pop().find_all(string=True)
                        name= list_name[0].strip()
                        if len(list_name)>1:
                            abilities="".join(list_name[1:]).split(",")
                        else:
                            abilities
                    weapons.append({"name":name,"abilities":abilities,"range":range,"attacks":attacks,"to_hit":to_hit,"to_wound":to_wound,"rend":rend,"damage":damage})
        return weapons
    
    def __parse_warscroll_abilities(self,warscroll):
        abilities = []
        table_titles = warscroll.find_all("td", class_="abHeader")
        table_titles = [t for t in table_titles if t.text.strip().lower()!="keywords"]
        for t in table_titles: 
            rule_text = t.find_next("div", class_="abBody")
            ability = self.__parse_rule_text(rule_text.text.strip())
            ability["when"]=t.text.strip()
            abilities.append(ability)
        return abilities
    
    def __parse_keywords(self,warscroll):
        keywords = []
        if warscroll.find("td", class_="wsKeywordLine1"):
            keywords.append([x.strip() for x in warscroll.find("td", class_="wsKeywordLine1").text.strip().split(",")])
        else:
            keywords.append([])
        if warscroll.find("td", class_="wsKeywordLine2"):
            keywords.append([x.strip() for x in warscroll.find("td", class_="wsKeywordLine2").text.strip().split(",")])
        else:
            keywords.append([])
        return keywords
    
    
    def build_unit(self, unit_name,description,attributes,unit_size,points,base_size,reinforced,regiment_options,notes,weapons,abilities,keywords):
        pass
    
    
    def generate_faction_file(self,faction_id, edition_id):
        faction_url = factions_dict[faction_id]["faction_url"]
        faction_data = self._parse_faction_data(faction_url, edition_id, factions_dict[faction_id]["alliance"])
        faction_data["warscrolls"]=self.parse_all_warscrolls(faction_url, faction_data)
        tools.save_file(faction_data, edition_id, faction_id)
        
    
    
if __name__=="__main__":
    parser = AOSWahapediaParser()
    res = parser.parse_all_factions_info()
    print(json.dumps(res, indent=3))
    #parser.generate_faction_file("ironjawz", "AoS_4")
        
