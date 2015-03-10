import pygame, sys, math, random
from pygame.locals import *

pygame.init()
fpsClock = pygame.time.Clock()

screen = pygame.display.set_mode((1200,600))
pygame.display.set_caption('Battle World')

lvlImg = pygame.image.load('lvl.png')
town1Img = pygame.image.load('town1.png')
charLImg = pygame.image.load('char.png')
atkLImg = pygame.image.load('atk.png')
atkRImg = pygame.image.load('atk2.png')
charRImg = pygame.image.load('char2.png')
red = pygame.Color(255,0,0)
green = pygame.Color(80,255,80)
blue = pygame.Color(0,0,255)
white = pygame.Color(255,255,255)
black = pygame.Color(0,0,0)
mx,my = 0,0
fps = 28
#list number  [0,1,2 ,3 , 4 ,5 , 6 ,  7  ,   8   ,9 ,   10   ,      11     ]
#player stats [x,y,dx,dy,lvl,hp,atk,honor,atkcool,xp,lvlCount,selected(0=F)]
players = [[50,50,50,50,1,10,2,0,0,0,0,0]]
#town stats [x,y,size,countdown]
towns = [[100,100,1,36]]
prot = [150,50]
#beast stats [x,y,dx,dy,10]
beasts = []
beastCount = 60
charSpeed = 1
uniqueNum = 1

fontObj = pygame.font.SysFont('minecraft.ttf',11)
while True:
    screen.fill(green)
    for char in players:
        if char[8] != 0:
            char[8]-=1
        if char[10] > 0:
            char[10] -= 1
            screen.blit(lvlImg,(char[0]-10,char[1]-15))
        if char[5] <= 0:
            players.remove(char)
        if char[9] >= char[4]*9:
            char[4] += 1
            char[9] = 0
            char[5] = (char[4]*10) + 5
            char[6] += 2
            char[10] += 32
            
        if char[0] != char[2] or char[1] != char[3]:
            x = char[0] - char[2]
            y = char[1] - char[3]
            b = math.sqrt((x*x)+(y*y))
            if char[0] >= char[2]:
                screen.blit(charLImg,(char[0],char[1]-4))
            else:
                screen.blit(charRImg,(char[0],char[1]-4))
            if b >= 3:
               char[0] = char[0] - (charSpeed*x)/b
               char[1] = char[1] - (charSpeed*y)/b
            else:
                char[0] = char[2]
                char[1] = char[3]
            
            msg = str(char[4])
            msgSurfaceObj = fontObj.render(msg, False, red)
            msgRectobj = msgSurfaceObj.get_rect()
            msgRectobj.topleft = (char[0]-6,char[1]+5)
            screen.blit(msgSurfaceObj,msgRectobj)
            for char2 in players:
                if char2 != char:
                    if math.sqrt(((char[0]-char2[0])**2)+((char[1]-char2[1])**2)) <= 22:
                        if (char[0] < prot[0] and char[0] > prot[1]) or (char2[0] < prot[0] and char2[0] > prot[1]):
                            if (char[1] < prot[0] and char[1] > prot[1]) or (char2[1] < prot[0] and char2[1] > prot[1]):
                                macht = 'nichts'
                            else:
                                char[2] = char2[0]
                                char[3] = char2[1]
                        else:
                            char[2] = char2[0]
                            char[3] = char2[1]
                        if math.sqrt(((char[0]-char2[0])**2)+((char[1]-char2[1])**2)) <= 4:
                            if char[8] == 0:
                                char2[5] -= char[6]+char[7]
                                if char2[5] <= 0:
                                    char[9] += 5
                                    char[7] += 1
                                char[8] = 32
                            else:
                                char[8] -= 1
            for beast in beasts:
                if math.sqrt(((char[0]-beast[0])**2)+((char[1]-beast[1])**2)) <= 24:
                    if (char[0] < prot[0] and char[0] > prot[1]) or (beast[0] < prot[0] and beast[0] > prot[1]):
                        if (char[1] < prot[0] and char[1] > prot[1]) or (beast[1] < prot[0] and beast[1] > prot[1]):
                            macht = 'nichts'
                        else:
                            char[2] = beast[0]
                            char[3] = beast[1]
                    else:
                        char[2] = beast[0]
                        char[3] = beast[1]
                    if math.sqrt(((char[0]-beast[0])**2)+((char[1]-beast[1])**2)) <= 4:
                        beast[2] = beast[0]
                        beast[3] = beast[1]
                        if (char[8] <=32 and char[8] >= 28) or char[8] == 0:
                            #Display atk image above player
                            screen.blit(atkLImg,(char[0]+9,char[1]-9))
                        if char[8] == 0:
                            #Attack Beast
                            beast[4] -= char[6]+char[7]
                            if beast[4] <= 0:
                                beasts.remove(beast)
                                char[9] += 2
                            char[8] = 32

                        
        else:
            char[2] = random.randint(20,980)
            char[3] = random.randint(20,580)
            if char[0] >= char[2]:
                screen.blit(charLImg,(char[0],char[1]-4))
            else:
                screen.blit(charRImg,(char[0],char[1]-4))
    for town in towns:
        pygame.draw.circle(screen,blue,(town[0],town[1]),4,0)
        if town[3] == 0:
            players.append([town[0],town[1],town[0],town[1],1,10,2,0,0,0,0,0])
            town[3] = 200
        else:
            town[3] -= 1
    for beast in beasts:
        pygame.draw.circle(screen,black,(beast[0],beast[1]),4,0)
        if beast[0] != beast[2] or beast[1] != beast[3]:
            x = beast[0] - beast[2]
            y = beast[1] - beast[3]
            b = math.sqrt((x*x)+(y*y))
            if b >= 3:
               beast[0] = beast[0] - (0.5*x)/b
               beast[1] = beast[1] - (0.5*y)/b
            else:
                beast[0] = beast[2]
                beast[1] = beast[3]
        else:
            beast[2] = random.randint(20,980)
            beast[3] = random.randint(20,580)
        
    if beastCount == 0:
        beasts.append([random.randint(10,800),random.randint(10,480),random.randint(10,800),random.randint(10,480),10,uniqueNum])
        uniqueNum += 1
        beastCount = 60
    else:
        beastCount -= 1
    
    for event in pygame.event.get():
        if event.type == QUIT:
                pygame.quit()
                sys.exit()
        if event.type == MOUSEBUTTONUP:
            mx,my = event.pos
            for char in players:
                if ((mx-5 - char[0])**2)+((my-5-char[1])**2) <= 100:
                    char[11] = 1
                else:
                    char[11] = 0
    for char in players:
        if char[11] == 1:
            pygame.draw.circle(screen,white,(char[0]+6,char[1]+7),20,2)
            pygame.draw.rect(screen,blue,(char[0]+5,char[1]-50,33,45))
            msg = str(char[5])+'   H P'
            msgSurfaceObj = fontObj.render(msg, False, green)
            msgRectobj = msgSurfaceObj.get_rect()
            msgRectobj.topleft = (char[0]+7,char[1]-47)
            screen.blit(msgSurfaceObj,msgRectobj)
            msg = str(char[9])+' / '+str(char[4]*9) + ' XP'
            msgSurfaceObj = fontObj.render(msg, False, green)
            msgRectobj = msgSurfaceObj.get_rect()
            msgRectobj.topleft = (char[0]+8,char[1]-40)
            screen.blit(msgSurfaceObj,msgRectobj)
            msg = str(char[6])+'  ATK'
            msgSurfaceObj = fontObj.render(msg, False, green)
            msgRectobj = msgSurfaceObj.get_rect()
            msgRectobj.topleft = (char[0]+8,char[1]-33)
            screen.blit(msgSurfaceObj,msgRectobj)
            msg = str(char[7])+'  HNR'
            msgSurfaceObj = fontObj.render(msg, False, green)
            msgRectobj = msgSurfaceObj.get_rect()
            msgRectobj.topleft = (char[0]+9,char[1]-26)
            screen.blit(msgSurfaceObj,msgRectobj)
            pygame.draw.rect(screen,green,(char[0]+9,char[1]-19,char[8]*.7,7))
    pygame.display.update()
    fpsClock.tick(fps)
                    
    
                    
                
    
    
