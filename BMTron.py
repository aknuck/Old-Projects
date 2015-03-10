import pygame, random, math, sys
from pygame.locals import *

pygame.init()
fpsClock = pygame.time.Clock()

screen = pygame.display.set_mode((640,640))
pygame.display.set_caption('BMTron')

red = pygame.Color(255,0,0)
green = pygame.Color(0,255,0)
blue = pygame.Color(0,0,255)
white = pygame.Color(255,255,255)
black = pygame.Color(0,0,0)
fps = 30


fontObj = pygame.font.SysFont('minecraft.ttf',24)
msg = 'BMTRON'

direction1 = 'u'
direction2 = 'd'
x1,y1 = 35,35
x2,y2 = 45,45
score1 = 0
score2 = 0


def getBoard():
    board = []
    for x in range(80):
        board.append([])
        for y in range(80):
            board[x].append(0)
    return board
def resetBoard(board):
    for x in range(80):
        for y in range(80):
            board[x][y] = 0
    return board
def write(x,y,size,color,msg):
    msgSurfaceObj = fontObj.render(msg, False, color)
    msgRectobj = msgSurfaceObj.get_rect()
    msgRectobj.topleft = (x,y)
    screen.blit(msgSurfaceObj,msgRectobj)
def draw_tile(color,x,y):
    pygame.draw.rect(screen,color,((x*8),(y*8),8,8))

def move(direction,x,y,color,alive):
    if direction == 'u':
        if y > 0 and board[x][y-1] == 0:
            y -= 1
            draw_tile(color,x,y)
            board[x][y] = 1
            
        else:
            alive = False
    if direction == 'r':
        if x < 79:
            if board[x+1][y] == 0:
                x += 1
                draw_tile(color,x,y)
                board[x][y] = 1
            else:
                alive = False
        else:
            alive = False
    if direction == 'd':
        if y < 79:
            if board[x][y+1] == 0:
                y += 1
                draw_tile(color,x,y)
                board[x][y] = 1
            else:
                alive = False
        else:
            alive = False
    if direction == 'l':
        if x > 0 and board[x-1][y] == 0:
            x -= 1
            draw_tile(color,x,y)
            board[x][y] = 1
        else:
            alive = False
    return x,y,alive

class menu():
    def __init__(self):
        self.menu = ['yes','no']
        self.cur = 0
    def move(self,direction):
        if direction == 'u':
            if self.cur > 0:
                self.cur -= 1
        if direction == 'd':
            if self.cur < (len(self.menu)-1):
                self.cur += 1
    def display(self):
            if self.cur == 0:
                pygame.draw.rect(screen,white,(270,225,90,40))
                msg = 'Yes'
                msgSurfaceObj = fontObj.render(msg,False,black)
                msgRectObj = msgSurfaceObj.get_rect()
                msgRectObj.topleft = (300,230)
                screen.blit(msgSurfaceObj,msgRectObj)
            else:
                pygame.draw.rect(screen,black,(270,225,90,40))
                msg = 'Yes'
                msgSurfaceObj = fontObj.render(msg,False,white)
                msgRectObj = msgSurfaceObj.get_rect()
                msgRectObj.topleft = (300,230)
                screen.blit(msgSurfaceObj,msgRectObj)
            if self.cur == 1:
                pygame.draw.rect(screen,white,(270,275,90,40))
                msg = 'No'
                msgSurfaceObj = fontObj.render(msg,False,black)
                msgRectObj = msgSurfaceObj.get_rect()
                msgRectObj.topleft = (300,280)
                screen.blit(msgSurfaceObj,msgRectObj)
            else:
                pygame.draw.rect(screen,black,(270,275,90,40))
                msg = 'No'
                msgSurfaceObj = fontObj.render(msg,False,white)
                msgRectObj = msgSurfaceObj.get_rect()
                msgRectObj.topleft = (300,280)
                screen.blit(msgSurfaceObj,msgRectObj)

def play(board,direction1,direction2,alive,alive2,x1,x2,y1,y2,start,score1,score2):
    board = resetBoard(board)
    screen.fill(black)
    draw_tile(blue,35,35)
    draw_tile(red,45,45)
    board[x1][y1] = 1 
    board[x2][y2] = 1
    while alive == True and alive2 == True:
        for event in pygame.event.get():
            if event.type == QUIT:
                pygame.quit()
                sys.exit()
            elif event.type == KEYDOWN:
                if event.key == K_UP:
                    direction1 = 'u'
                    start = 'y'
                if event.key == K_RIGHT:
                    direction1 = 'r'
                    start = 'y'
                if event.key == K_DOWN:
                    direction1 = 'd'
                    start = 'y'
                if event.key == K_LEFT:
                    direction1 = 'l'
                    start = 'y'
                if event.key == K_w:
                    direction2 = 'u'
                    start = 'y'
                if event.key == K_d:
                    direction2 = 'r'
                    start = 'y'
                if event.key == K_s:
                    direction2 = 'd'
                    start = 'y'
                if event.key == K_a:
                    direction2 = 'l'
                    start = 'y'

        if start == 'y':
            x1,y1,alive = move(direction1,x1,y1,blue,alive)
            if alive == True:
                x2,y2,alive2 = move(direction2,x2,y2,red,alive)


        pygame.display.update()

        fpsClock.tick(fps)

        if alive == False or alive2 == False:
            retry = ' '
            if alive == False:
                score2 += 1
            else:
                score1 += 1
            loop = True
            while loop == True:
                for event in pygame.event.get():
                    if event.type == QUIT:
                        pygame.quit()
                        sys.exit()
                    elif event.type == KEYDOWN:
                        if event.key == K_RETURN:
                            loop = False
                write(220,420,24,white,'Press Enter to Continue')
                pygame.display.update()
            while retry == ' ':
                screen.fill(black)
                if alive == False:
                    msg = 'Blue Lost'
                    msgSurfaceObj = fontObj.render(msg,False,blue)
                    msgRectObj = msgSurfaceObj.get_rect()
                    msgRectObj.topleft = (100,100)
                    screen.blit(msgSurfaceObj,msgRectObj)
                if alive2 == False:
                    msg = 'Red Lost'
                    msgSurfaceObj = fontObj.render(msg,False,red)
                    msgRectObj = msgSurfaceObj.get_rect()
                    msgRectObj.topleft = (100,100)
                    screen.blit(msgSurfaceObj,msgRectObj)
                write(100,50,24,blue,str(score1))
                write(250,50,24,red,str(score2))
                msg = 'Retry?'
                msgSurfaceObj = fontObj.render(msg,False,white)
                msgRectObj = msgSurfaceObj.get_rect()
                msgRectObj.topleft = (290,200)
                screen.blit(msgSurfaceObj,msgRectObj)
                

                for event in pygame.event.get():
                    if event.type == QUIT:
                        pygame.quit()
                        sys.exit()
                    elif event.type == KEYDOWN:
                        if event.key == K_UP:
                            menu.move('u')
                        if event.key == K_DOWN:
                            menu.move('d')
                        if event.key == K_RETURN:
                            if menu.cur == 0:
                                retry = 'y'
                            else:
                                retry = 'n'
                menu.display()
                pygame.display.update()
            if retry == 'y':
                alive = True
                alive2 = True
                start = 'n'
                direction1 = 'u'
                direction2 = 'd'
                x1,y1 = 35,35
                x2,y2 = 45,45
                play(board,direction1,direction2,alive,alive2,x1,x2,y1,y2,start,score1,score2)
            else:
                pygame.quit()
                sys.exit()
        




                
board = getBoard()
screen.fill(black)
alive = True
alive2 = True
menu = menu()
start = 'n'
play(board,direction1,direction2,alive,alive2,x1,x2,y1,y2,start,score1,score2)

