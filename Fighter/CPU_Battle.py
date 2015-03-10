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

player = Balanced_Fighter()
cpu = Balanced_Fighter()
players = [player,cpu]

def CPU_Battle(cpu,player,players):
    battling = True
    while battling == True:
        if player.hp <= 0:
            write(100,100,green,'player lose',fontObj,screen)
            pygame.display.update()
            pygame.time.wait(100)
            battling = False
        elif cpu.hp <= 0:
            write(100,100,green,'cpu lose',fontObj,screen)
            pygame.display.update()
            pygame.time.wait(500)
            battling = False
        #update counters
        if cpu.x > player.x:
            cpu.facing = -1
            player.facing = 1
        else:
            cpu.facing = 1
            player.facing = -1
        
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
            if check_hit(pygame.Rect(player.x+(player.facing*100),player.y-200,30,30),cpu) == True and player.moveHit == False:
                cpu.hp -= player.combosData[index][0]
                player.moveHit = True
            if player.moveDone == False:
                player.x = player.x + (player.facing*player.combosData[index][7])
                player.moveDone = True
            if player.hitCnt == 0:
                player.keyComb = []
            pygame.draw.rect(screen,red,(player.x + (player.facing*player.combosData[index][3]),player.y-player.combosData[index][4],player.combosData[index][5],player.combosData[index][6]),3)#replace with check for hit
        elif player.move == 'punch' and player.pos == 'upright':
            player.hitCnt -= 1
            if check_hit(pygame.Rect(player.x+(player.facing*100),player.y-200,30,30),cpu) == True and player.moveHit == False:
                cpu.hp -= 3
                player.moveHit = True
            if player.moveDone == False:
                player.x = player.x + (player.facing*6)
                player.moveDone = True
            pygame.draw.rect(screen,red,(player.x+(player.facing*100),player.y-200,30,30),2)
        elif player.move == 'punch' and player.pos =='crouch':
            player.hitCnt -= 1
            if check_hit(pygame.Rect(player.x+(player.facing*100),player.y-200,30,30),cpu) == True and player.moveHit == False:
                cpu.hp -= 3
                player.moveHit = True
            if player.moveDone == False:
                player.x = player.x + (player.facing*4)
                player.moveDone = True
            pygame.draw.rect(screen,red,(player.x+(player.facing*80),player.y-220,30,30),2)
        elif player.move == 'kick' and player.pos == 'upright':
            player.hitCnt -= 1
            if check_hit(pygame.Rect(player.x+(player.facing*100),player.y-200,30,30),cpu) == True and player.moveHit == False:
                cpu.hp -= 5
                player.moveHit = True
            if player.moveDone == False:
                player.x = player.x + (player.facing*7)
                player.moveDone = True
            pygame.draw.rect(screen,red,(player.x+(player.facing*100),player.y-100,80,35),2)
        elif player.move == 'kick' and player.pos == 'crouch':
            player.hitCnt -= 1
            if check_hit(pygame.Rect(player.x+(player.facing*100),player.y-200,30,30),cpu) == True and player.moveHit == False:
                cpu.hp -= 2
                player.moveHit = True
                cpu.pos = 'fall'
                cpu.fallCnt = 20
            if player.moveDone == False:
                player.x = player.x + (player.facing*2)
                player.moveDone = True
            pygame.draw.rect(screen,red,(player.x+(player.facing*100),player.y-50,80,35),2)
        write(50,50,green,'CPU HP:'+str(cpu.hp),fontObj,screen)

        #player movement--------------------------------------
        player = movement(player)
        cpu = movement(cpu)
        
        #user input -------------------------------
        for event in pygame.event.get():
            if event.type == QUIT:
                pygame.quit()
                sys.exit()
            elif event.type == KEYDOWN:
                #Arrow keys = movement
                #H = atk MAIN
                #U = atk SCND
                if event.key == K_w:
                    if player.y == 620 and player.hitCnt == 0:
                        player.acc = 40
                        player.accDir = 'up'
                    player.keyComb.append('^')
                    player.combCnt = 15
                if event.key == K_a:
                    if player.hitCnt == 0:
                        player.moveHor = 'left'
                    player.keyComb.append('<')
                    player.combCnt = 15
                if event.key == K_d:
                    if player.hitCnt == 0:
                        player.moveHor = 'right'
                    player.keyComb.append('>')
                    player.combCnt = 15
                if event.key == K_s:
                    player.pos = 'crouch'
                    player.keyComb.append('V')
                    player.combCnt = 15
                if event.key == K_h:
                    player.keyComb.append('H')
                    player.combCnt = 15
                    if player.hitCnt == 0:
                        player.move = 'punch'
                        if player.pos == 'upright':
                            player.hitCnt = 12
                        else:
                            player.hitCnt = 10
                if event.key == K_u:
                    player.keyComb.append('U')
                    player.move = 'kick'
                    player.hitCnt = 15
                    player.combCnt = 15
                if event.key == K_ESCAPE:
                    pygame.event.post(pygame.event.Event(QUIT))
            elif event.type == KEYUP:
                if event.key == K_a:
                    if player.moveHor == 'left':
                        player.moveHor = 'none'
                        player.howAcc = 0
                if event.key == K_d:
                    if player.moveHor == 'right':
                        player.moveHor = 'none'
                        player.howAcc = 0
                if event.key == K_s:
                    player.pos = 'upright'

        pygame.display.update()
        fpsClock.tick(40)

CPU_Battle(cpu,player,players)
pygame.quit()
sys.exit()
