#displays the map

import pygame
from pygame.locals import *

def display_map(board,screen,mx,my):
    for x in range(50):
        for y in range(50):
            screen.blit(board[x][y].img,((x*32)-mx,(y*32)-my))
