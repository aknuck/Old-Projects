#main loop with the pygame
import pygame
from pygame.locals import *
from display_map import *
from move_cam import *
from display_minimap import *
from click_minimap import *
from tile_changer import *
import sys

def main(board,screen,TILES):
    red = pygame.Color(255,0,0)
    green = pygame.Color(0,255,0)
    darkgreen = pygame.Color(0,150,0)
    blue = pygame.Color(0,0,255)
    black = pygame.Color(0,0,0)
    white = pygame.Color(255,255,255)
    lightgrey = pygame.Color(175,175,175)
    grey = pygame.Color(80,80,80)
    gold = pygame.Color(200,190,0)
    mx,my = 0,0
    x_move,y_move = 0,0
    move1,move2 = 'null','null'
    selected = 'road'

    while True:
        #display board----
        display_map(board,screen,x_move,y_move)

        for x in range(50):
            for y in range(50):
                if board[x][y].s == 'mountain' or board[x][y].s == 'mine':
                    if board[x][y+1].img == TILES['mountainM'] or board[x][y+1].img == TILES['s_mountainM'] or board[x][y+1].img == TILES['mineM']:
                        screen.blit(TILES['mountain_top'],((x*32)-x_move,(y*32)-y_move))
        #-----
        pygame.draw.rect(screen,grey,(0,560,1100,150))
        display_minimap(board,x_move,y_move,grey,darkgreen,green,lightgrey,screen,white)
                  
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                sys.exit()
            elif event.type == MOUSEMOTION:
                mx, my = event.pos
            elif event.type == MOUSEBUTTONDOWN:
                mx, my = event.pos
                if mx > 22 and mx < 122 and my > 578 and my < 678:
                    x_move, y_move = click_minimap(mx,my,x_move,y_move)
                else:
                    if selected != 'mine':
                        board[(mx+x_move)/32][(my+y_move)/32].typ = selected
                        board = tile_changer(board,TILES,((mx+x_move)/32),((my+y_move)/32),selected)
                    else:
                        if board[(mx+x_move)/32][(my+y_move)/32].typ == 'mountain':
                            board[(mx+x_move)/32][(my+y_move)/32].typ = selected
                            board = tile_changer(board,TILES,((mx+x_move)/32),((my+y_move)/32),selected)
                            
                        
                
            elif event.type == KEYDOWN:
                if event.key == K_LEFT:
                    move1 = 'left'
                elif event.key == K_RIGHT:
                    move1 = 'right'
                elif event.key == K_DOWN:
                    move2 = 'down'
                elif event.key == K_UP:
                    move2 = 'up'
                elif event.key == K_1:
                    selected = 'road'
                elif event.key == K_2:
                    selected = 'farm'
                elif event.key == K_3:
                    selected = 'logger'
                elif event.key == K_4:
                    selected = 'mine'
                elif event.key == K_5:
                    selected = 'town'
            elif event.type == KEYUP:
                if event.key in (K_RIGHT,K_LEFT):
                    move1 = 'null'
                elif event.key in (K_UP,K_DOWN):
                    move2 = 'null'
            elif event.type == KEYDOWN and event.key == K_ESCAPE:
                pygame.quit()
                sys.exit()

        x_move,y_move = move_cam(mx,my,x_move,y_move)

        if move1 == 'left':
            if x_move > 10:
                x_move -= 10
            else:
                x_move = 0
        elif move1 == 'right':
            if x_move < 490:
                x_move += 10
            else:
                x_move = 500
        if move2 == 'down':
            if y_move < 880:
                y_move += 10
            else:
                y_move = 890
        elif move2 == 'up':
            if y_move > 10:
                y_move -= 10
            else:
                y_move = 0


        

        pygame.display.update()
        

