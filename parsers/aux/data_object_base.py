import json



class DataObjectBase(object):
    
    def __init__(self):
        pass
    
    def __repr__(self):
        return json.dumps(self.__dict__, indent=3)
        
    def __str__(self):
        return json.dumps( self,
            default=lambda o: o.__dict__, 
            sort_keys=True,
            indent=3)
    
        
        