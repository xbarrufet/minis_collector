from json import JSONEncoder


class DataObjectEncoder(JSONEncoder):
        def default(self, o):
            return o.__dict__