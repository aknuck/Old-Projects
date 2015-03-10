#will display the minimap, taking the board, as well as mx and my
import pygame
from pygame.locals import *

def display_minimap(board,mx,my,grey,darkgreen,green,lightgrey,screen,white):
    #first print the base board
    pygame.draw.rect(screen,lightgrey,(18,574,108,108))

    for x in range(50):
        for y in range(50):
            if board[x][y].typ == 'forest':
                pygame.draw.rect(screen,darkgreen,(22+(x*2),578+(y*2),2,2))
            elif board[x][y].typ == 'plain':
                pygame.draw.rect(screen,green,(22+(x*2),578+(y*2),2,2))
            elif board[x][y].typ == 'mountain':
                pygame.draw.rect(screen,grey,(22+(x*2),578+(y*2),2,2))

    #then display the users square of their screen

    pygame.draw.rect(screen,white,(22+(mx/16),578+(my/16),69,44),2)
