#<2 dies
#2or3 lives
#>3 dies
#dead cell w/ 3 live neighbors lives

import pygame,math
from pygame.locals import *

pygame.init()
fpsClock = pygame.time.Clock()

screen = pygame.display.set_mode((1200,1200))
pygame.display.set_caption('Conways Game of Life')

red = pygame.Color(255,0,0)
green = pygame.Color(0,255,0)
blue = pygame.Color(0,0,255)
white = pygame.Color(255,255,255)
black = pygame.Color(0,0,0)

board = []
#each cell will be [0,0] where the first is current state and the latter is next state
for x in range(240):
    board.append([])
    for y in range(240):
        board[x].append([0,0])
def write(x,y,color,size,msg):
    fontObj = pygame.font.SysFont('minecraft.ttf',size)
    msgSurfaceObj = fontObj.render(msg, False, color)
    msgRectobj = msgSurfaceObj.get_rect()
    msgRectobj.topleft = (x,y)
    screen.blit(msgSurfaceObj,msgRectobj)
def run(board):
    red = pygame.Color(255,0,0)
    green = pygame.Color(0,255,0)
    blue = pygame.Color(0,0,255)
    white = pygame.Color(255,255,255)
    black = pygame.Color(0,0,0)
    running = True #program running
    run = False #cycling
    count = 0
    while running == True:
        screen.fill(white)
        if run == True:
                for x in range(len(board)):
                    for y in range(len(board[x])):
                        life = 0
                        if x-1 >= 0:
                            if board[x-1][y][0] == 1:
                                life += 1
                        if y-1 >= 0:
                            if board[x][y-1][0] == 1:
                                life += 1
                        if y-1 >= 0 and x-1 >= 0:
                            if board[x-1][y-1][0] == 1:
                                life += 1
                        if x+1 <= 239 and y-1 >= 0:
                            if board[x+1][y-1][0] == 1:
                                life += 1
                        if x+1 <= 239:
                            if board[x+1][y][0] == 1:
                                life += 1
                        if x+1 <= 239 and y+1 <= 239:
                            if board[x+1][y+1][0] == 1:
                                life += 1
                        if y+1 <= 239:
                            if board[x][y+1][0] == 1:
                                life += 1
                        if x-1 >= 0 and y+1 <= 239:
                            if board[x-1][y+1][0] == 1:
                                life += 1
                        if life < 2:
                            board[x][y][1] = 0
                        if board[x][y][0] == 1:
                            if life == 2 or life == 3:
                                board[x][y][1] = 1
                        if board[x][y][0] == 0:
                            if life == 3:
                                board[x][y][1] = 1
                        if life > 3:
                            board[x][y][1] = 0
        if run == False:
            write(500,800,black,36,'Press Space')
        for x in range(len(board)):
            for y in range(len(board[x])):
                if board[x][y][0] == 1:
                    color = black
                    if run == True:
                        board[x][y][0] = board[x][y][1]
                else:
                    color = white
                    if run == True:
                        board[x][y][0] = board[x][y][1]
                pygame.draw.rect(screen,color,(x*5,y*5,5,5))
            
        for event in pygame.event.get():
            if event.type == QUIT:
                pygame.quit()
                running = False
            elif event.type == MOUSEBUTTONUP:
                x,y = event.pos
                if run == False:
                    if board[(x/5)][(y/5)][0] == 1:
                        board[(x/5)][(y/5)][0] = 0
                    else:
                        board[(x/5)][(y/5)][0] = 1
                
            elif event.type == KEYDOWN:
                if event.key == K_SPACE:
                    if run == False:
                        run = True
                    else:
                        run = False
        pygame.display.update()
        fpsClock.tick(32)
run(board)
                
