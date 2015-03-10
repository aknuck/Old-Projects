#FILE GOAL
#this is the class for the balanced figher, the basic one until others are unlockd through story and achievements

class Balanced_Fighter:
    #moving information
    x,y = 50,50
    acc = 0
    accDir = 'down'
    horAcc = 0
    moveHor = 'none'
    #positioning
    pos = 'upright' #upright,crouch,jump,fall
    facing = 1 #wither 1 (facing right) or -1 (facing left)
    #fighting
    hp = 100
    keyComb = []
    combCnt = 0
    combos = [['H','H','U'],['H','H','H'],['V','V','^','H'],['^','^','V','V','<','>','<','>','U','H']]
    combosData = {0:[10,20,30,160,180,75,20,5],1:[6,15,10,100,200,30,30,10],2:[12,20,40,120,150,90,45,5],3:[25,35,40,150,350,350,300,0]}#damage, delay increase,knockback,x,y,width,height,slide
    Hhit = [100,200,30,30,4]#xpos,ypos,width,height,damage
    Uhit = [100,200,30,30,5]
    move = 'null'
    hitCnt = 0
    moveHit = False
    moveDone = False
    fallCnt = 0 #fall counter for being down
