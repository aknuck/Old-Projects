#FILE GOAL
#basic structure of battle vs computer

import pygame,math,random,sys
from pygame.locals import *
from variables import *
from Balanced_Fighter import *
from check_hit import *
from movement import *
from write import *

fpsClock = pygame.time.Clock()

p1 = Balanced_Fighter()
p2 = Balanced_Fighter()
players = [p1,p2]

def P2_Battle(p1,p2,players):
    battling = True
    while battling == True:
        if p1.hp <= 0:
            write(100,100,green,'player 1 lose',fontObj,screen)
            pygame.display.update()
            pygame.time.wait(100)
            battling = False
        elif p2.hp <= 0:
            write(100,100,green,'player 2 lose',fontObj,screen)
            pygame.display.update()
            pygame.time.wait(500)
            battling = False
        #update counters
        if p2.x > p1.x:
            p2.facing = -1
            p1.facing = 1
        else:
            p2.facing = 1
            p1.facing = -1
        
        screen.fill(white)
        pygame.draw.rect(screen,black,(0,600,1200,100))#Replace with ground later, for now just marking the ground
        for plyr in players:
            if plyr.fallCnt > 0:
                print plyr.fallCnt
                plyr.fallCnt -= 1 #this is unrelated but being put here because it goes for both players
                plyr.pos = 'fall'
            else:
                if plyr.pos == 'fall':
                    plyr.pos = 'upright'
            if plyr.y > 620:
                plyr.pos = 'jump'
                #use jump image
            if plyr.pos == 'upright':
                pygame.draw.rect(screen,blue,(plyr.x,plyr.y-250,60,250))#replace with image of standing/animation
            elif plyr.pos == 'crouch':
                pygame.draw.rect(screen,blue,(plyr.x,plyr.y-150,60,150))#replace with crouch image/animation
            elif plyr.pos == 'fall':
                pygame.draw.rect(screen,blue,(plyr.x,plyr.y-60,250,60))
        for player in players:
            if player.combCnt != 0:
                player.combCnt -= 1
            else:
                player.keyComb = []
            if player.keyComb in player.combos:
                player.move = 'combo'
                print player.keyComb
                player.combCnt = 0
                index =  player.combos.index(player.keyComb)
                player.hitCnt = player.combosData[index][1]
            

            #hit moves
            if player.hitCnt == 0:
                player.move = 'null'
                player.moveHit = False
                player.moveDone = False
            else:
                if player.y == 620:
                    player.moveHor = 'none'
            if player.move == 'combo':
                player.hitCnt -= 1
                if check_hit(pygame.Rect(player.x+(player.facing*100),player.y-200,30,30),p2) == True and player.moveHit == False:
                    p2.hp -= player.combosData[index][0]
                    player.moveHit = True
                if player.moveDone == False:
                    player.x = player.x + (player.facing*player.combosData[index][7])
                    player.moveDone = True
                if player.hitCnt == 0:
                    player.keyComb = []
                pygame.draw.rect(screen,red,(player.x + (player.facing*player.combosData[index][3]),player.y-player.combosData[index][4],player.combosData[index][5],player.combosData[index][6]),3)#replace with check for hit
            elif player.move == 'punch' and player.pos == 'upright':
                player.hitCnt -= 1
                if check_hit(pygame.Rect(player.x+(player.facing*100),player.y-200,30,30),p2) == True and player.moveHit == False:
                    p2.hp -= 3
                    player.moveHit = True
                if player.moveDone == False:
                    player.x = player.x + (player.facing*6)
                    player.moveDone = True
                pygame.draw.rect(screen,red,(player.x+(player.facing*100),player.y-200,30,30),2)
            elif player.move == 'punch' and player.pos =='crouch':
                player.hitCnt -= 1
                if check_hit(pygame.Rect(player.x+(player.facing*100),player.y-200,30,30),p2) == True and player.moveHit == False:
                    p2.hp -= 3
                    player.moveHit = True
                if player.moveDone == False:
                    player.x = player.x + (player.facing*4)
                    player.moveDone = True
                pygame.draw.rect(screen,red,(player.x+(player.facing*80),player.y-220,30,30),2)
            elif player.move == 'kick' and player.pos == 'upright':
                player.hitCnt -= 1
                if check_hit(pygame.Rect(player.x+(player.facing*100),player.y-200,30,30),p2) == True and player.moveHit == False:
                    p2.hp -= 5
                    player.moveHit = True
                if player.moveDone == False:
                    player.x = player.x + (player.facing*7)
                    player.moveDone = True
                pygame.draw.rect(screen,red,(player.x+(player.facing*100),player.y-100,80,35),2)
            elif player.move == 'kick' and player.pos == 'crouch':
                player.hitCnt -= 1
                if check_hit(pygame.Rect(player.x+(player.facing*100),player.y-200,30,30),p2) == True and player.moveHit == False:
                    p2.hp -= 2
                    player.moveHit = True
                    p2.pos = 'fall'
                    p2.fallCnt = 20
                if player.moveDone == False:
                    player.x = player.x + (player.facing*2)
                    player.moveDone = True
                pygame.draw.rect(screen,red,(player.x+(player.facing*100),player.y-50,80,35),2)
        write(50,50,green,'p2 HP:'+str(p2.hp),fontObj,screen)

        #player movement--------------------------------------
        player = movement(player)
        p2 = movement(p2)
        
        #user input -------------------------------
        for event in pygame.event.get():
            if event.type == QUIT:
                pygame.quit()
                sys.exit()
            elif event.type == KEYDOWN:
                #P1 Controls
                #WASD = movement
                #H = atk MAIN
                #U = atk SCND

                #P2 Controls
                #Arrow keys move
                #m = MAIN
                #n = SCND
                if event.key == K_w:
                    if p1.y == 620 and p1.hitCnt == 0:
                        p1.acc = 40
                        p1.accDir = 'up'
                    p1.keyComb.append('^')
                    p1.combCnt = 15
                if event.key == K_a:
                    if p1.hitCnt == 0:
                        p1.moveHor = 'left'
                    p1.keyComb.append('<')
                    p1.combCnt = 15
                if event.key == K_d:
                    if p1.hitCnt == 0:
                        p1.moveHor = 'right'
                    p1.keyComb.append('>')
                    p1.combCnt = 15
                if event.key == K_s:
                    p1.pos = 'crouch'
                    p1.keyComb.append('V')
                    p1.combCnt = 15
                if event.key == K_h:
                    p1.keyComb.append('H')
                    p1.combCnt = 15
                    if p1.hitCnt == 0:
                        p1.move = 'punch'
                        if p1.pos == 'upright':
                            p1.hitCnt = 12
                        else:
                            p1.hitCnt = 10
                if event.key == K_u:
                    p1.keyComb.append('U')
                    p1.move = 'kick'
                    p1.hitCnt = 15
                    p1.combCnt = 15
                #P2
                if event.key == K_UP:
                    if p2.y == 620 and p2.hitCnt == 0:
                        p2.acc = 40
                        p2.accDir = 'up'
                    p2.keyComb.append('^')
                    p2.combCnt = 15
                if event.key == K_LEFT:
                    if p2.hitCnt == 0:
                        p2.moveHor = 'left'
                    p2.keyComb.append('<')
                    p2.combCnt = 15
                if event.key == K_RIGHT:
                    if p2.hitCnt == 0:
                        p2.moveHor = 'right'
                    p2.keyComb.append('>')
                    p2.combCnt = 15
                if event.key == K_DOWN:
                    p2.pos = 'crouch'
                    p2.keyComb.append('V')
                    p2.combCnt = 15
                if event.key == K_m:
                    p2.keyComb.append('H')
                    p2.combCnt = 15
                    if p2.hitCnt == 0:
                        p2.move = 'punch'
                        if p2.pos == 'upright':
                            p2.hitCnt = 12
                        else:
                            p2.hitCnt = 10
                if event.key == K_n:
                    p2.keyComb.append('U')
                    p2.move = 'kick'
                    p2.hitCnt = 15
                    p2.combCnt = 15

            elif event.type == KEYUP:
                if event.key == K_a:
                    if p1.moveHor == 'left':
                        p1.moveHor = 'none'
                        p1.howAcc = 0
                if event.key == K_d:
                    if p1.moveHor == 'right':
                        p1.moveHor = 'none'
                        p1.howAcc = 0
                if event.key == K_s:
                    p1.pos = 'upright'
                if event.key == K_LEFT:
                    if p2.moveHor == 'left':
                        p2.moveHor = 'none'
                        p2.howAcc = 0
                if event.key == K_RIGHT:
                    if p2.moveHor == 'right':
                        p2.moveHor = 'none'
                        p2.howAcc = 0
                if event.key == K_DOWN:
                    p2.pos = 'upright'

        pygame.display.update()
        fpsClock.tick(30)

P2_Battle(p1,p2,players)
pygame.quit()
sys.exit()
