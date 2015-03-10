import random, math, sys, pygame
from tile_classes import *
from mapGen import *
from edger import *
from board_print import *
from tile_converter import *
from main import *
from pygame.locals import *

#should add menu here to see if laod game or new
#for now keeping it basic, jsut want to create new map and display it

pygame.init()
fpsClock = pygame.time.Clock()

screen = pygame.display.set_mode((1100,700))
pygame.display.set_caption('Kingdoms Remake')

TILES = {}


board = getNewBoard()
board = edger(board)
#board_print(board)
board,TILES = tile_converter(board)

main(board,screen,TILES)

#NOTE considering game element. think i will add. logger comes in, all forest tiles around it are converted to plains, and the logger takes a variable equal to the number of forest tiles destroyed. That is how long it will produce wood 



