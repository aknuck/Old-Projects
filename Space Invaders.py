##To add images later, look for the tag ADDIMAGE
##
##
##
##
##
##----------------------------------------

import pygame, random, math
from pygame.locals import *

pygame.init()
fpsClock = pygame.time.Clock()

screen = pygame.display.set_mode((480,640))
pygame.display.set_caption('Space Invaders')

red = pygame.Color(255,0,0)
green = pygame.Color(0,255,0)
blue = pygame.Color(0,0,255)
black = pygame.Color(0,0,0)
white = pygame.Color(255,255,255)
enemiesx,enemiesy = 11,6
spacex,spacey = 32,32

fontObj = pygame.font.SysFont('minecraft.ttf',24)

def write(x,y,size,color,msg):
    msgSurfaceObj = fontObj.render(msg, False, color)
    msgRectobj = msgSurfaceObj.get_rect()
    msgRectobj.topleft = (x,y)
    screen.blit(msgSurfaceObj,msgRectobj)

def quickMenu():
    #a quick menu that puts the text "press enter to begin" over the screen and does nothing until enter is hit or the game is closed
    loop = True
    while loop == True:
        write(160,240,24,green,"Press Enter To Begin")
        for event in pygame.event.get():
            if event.type == QUIT:
                pygame.quit()
                sys.exit()
            elif event.type == KEYDOWN:
                if event.key == K_RETURN:
                    loop = False
        pygame.display.update()
        fpsClock.tick(32)
        
class Player:
    #creates a simple player with lives count, position of his/her shot, and their position on the x axis(y doesnt change)
    lives = 3
    shotx = 0
    shoty = 0
    x = 160
    score = 0
    def printPlayer(self):
        pygame.draw.rect(screen,white,(self.x,600,32,32))#ADDIMAGE
    def printShot(self):
        if self.shoty > 100:
            pygame.draw.line(screen,white,(self.shotx,self.shoty),(self.shotx,self.shoty+10),3)

def newEnemyGrid():
    #creates a full enemey grid, 7 wide and 6 tall, 1 row of most valuable, 2 rows of middle value, bottom three rows of lesser value
    enemies = []
    for x in range(enemiesx):
        enemies.append([])
        for y in range(enemiesy):
            if y == 0:
                enemies[x].append('3')
            elif y > 0 and y < 3:
                enemies[x].append('2')
            elif y > 2:
                enemies[x].append('1')
    return enemies

class Enemies:
    #one class to control them all. position of the grid, movement direction, speed, shots
    direction = 'right'
    x = 50
    y = 150
    shotx,shoty = 0,0
    speed = 16 #how many ticks per move. 32 is 1 second
    moveCount = 16
    enemies = newEnemyGrid()
    def move(self):
        if self.direction == 'right':
            if (self.x + (len(self.enemies) * spacex)) >= 455:
                self.y += spacey
                if self.speed-4 > 0:
                    self.speed -= 3
                self.direction = 'left'
            else:
                self.x += 5
        else:
            if self.x <= 35:
                self.y += spacey
                if self.speed-4 > 0:
                    self.speed -= 3
                else:
                    self.speed = 1
                self.direction = 'right'
            else:
                self.x -= 5
    def displayShot(self):
        if self.shoty < 5:
            pygame.draw.line(screen,green,(self.shotx,self.shoty),(self.shotx,self.shoty+10),3)

            
                
    def printEnemies(self):
        #displays the enemies
        for xpos in range(len(self.enemies)):
            for ypos in range(len(self.enemies[xpos])):
                if self.enemies[xpos][ypos] == '3':
                    pygame.draw.rect(screen, green, (self.x + (xpos * spacex),self.y + (ypos * spacex),24,24))#ADDIMAGE
                elif self.enemies[xpos][ypos] == '2':
                    pygame.draw.rect(screen, red, (self.x + (xpos * spacex),self.y + (ypos * spacex),24,24))#ADDIMAGE
                elif self.enemies[xpos][ypos] == '1':
                    pygame.draw.rect(screen, blue, (self.x + (xpos * spacex),self.y + (ypos * spacex),24,24))#ADDIMAGE
                    
def enemyRowCheck(enemies,x):
    for xpos in range(len(enemies)):
        row = 0
        for ypos in enemies[xpos]:
            if int(enemies[xpos][int(ypos)-1]) > 0:
                row += 1
        if row == 0:
            if xpos == 0 or xpos == len(enemies)-1:
                enemies.remove(enemies[xpos])
                if xpos == 0:
                    x += 24
                break
    return enemies, x
def winCheck(enemies):
    total = 0
    for x in range(len(enemies)):
        for y in enemies[x]:
            if y == '1' or y == '2' or y == '3':
                total += 1
    if total == 0:
        return True
    else:
        return False
            
def checkCollide(enemies,shotx,shoty,x,y,score):
    brick = False
    for xpos in range(len(enemies)):
        for ypos in range(len(enemies[xpos])):
            tempx = xpos*spacex
            tempy = ypos*spacey
            temp = pygame.Rect(x+tempx,y+tempy,24,24)
            
            if temp.collidepoint(shotx,shoty) == 1 and enemies[xpos][ypos] != '0':
                shotx,shoty = 0,0
                enemies[xpos][ypos] = '0'
                enemies,x = enemyRowCheck(enemies,x)
                if ypos == 0:
                    score += 50
                elif ypos > 0 and ypos < 3:
                    score += 25
                elif ypos > 2:
                    score += 10
                brick = True
                if brick == True:
                    break
            if brick == True:
                break
        if brick == True:
            break
    return enemies,shotx,shoty,score,x
def fail():
    while True:
        write(200,200,36,white,"n00b fail")
        pygame.display.update()
def game():
    #plays the game
    player = Player()
    enemies = Enemies()
    win = False
    while win == False:
        #print all the things
        screen.fill(black)
        enemies.printEnemies()
        player.printPlayer()
        player.printShot()
        write(50,50,24,white,str(player.score))
        
        
        #get all the user input
        for event in pygame.event.get():
            if event.type == QUIT:
                pygame.quit()
                sys.exit()
            elif event.type == KEYDOWN:
                if event.key == K_SPACE:
                    if player.shoty <= 100:
                        player.shotx = player.x
                        player.shoty = 600
        keys = pygame.key.get_pressed()
        if (keys[K_LEFT]):
            if player.x >=30:
                player.x -= 4
        if (keys[K_RIGHT]):
            if player.x <= 450:
                player.x += 4

                
        #update all the movement   
        if enemies.moveCount == 0:
            enemies.moveCount = enemies.speed
            enemies.move()
        else:
            enemies.moveCount -= 1
        if player.shoty >= 100:
            player.shoty -= 28
            enemies.enemies,player.shotx,player.shoty,player.score,enemies.x = checkCollide(enemies.enemies,player.shotx,player.shoty,enemies.x,enemies.y,player.score)
            if winCheck(enemies.enemies) == True:
                win = True
        else:
            player.shotx, player.shoty = 0,0
        pygame.display.update()
        fpsClock.tick(32)

quickMenu()  
game()
fail()

pygame.quit()
sys.exit()
