
class Terrain():
    def __init__(self,typ):
        self.n = ''
        self.e = ''
        self.s = ''
        self.w = ''
        self.typ = typ
        self.gen = 'terrain'
        self.val = 0
    def update():
        pass

class unit():
    def __init__(self):
        name = choice([list,of,names])
        self.hp = 10
        self.atk = 1
        self.dfn = 1
        #stats for certain labor activities
        self.farm = 1
        self.log = 1
        self.wood = 1
        #still considering, should specialty skills be T/F or on a level?
        self.merchant = False #still undecided on these things
        self.wizard = False
        self.warrior = False

        #specializations based on if theyrea certain thing? idk ill come back to it later
