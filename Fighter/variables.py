#FILE GOAL
#get all colors and images
#loader player vars from file
import pygame
from pygame.locals import *

pygame.init()

#import colors-----------------------
red = pygame.Color(255,0,0)
green = pygame.Color(0,255,0)
blue = pygame.Color(0,0,255)
white = pygame.Color(255,255,255)
black = pygame.Color(0,0,0)

# import images-----------------------
# NONE ATM <3

try:
    fontObj = pygame.font.Font('minecraft.ttf',36)
except:
    fontObj = pygame.font.Font('freesansbold.ttf',36)

screen = pygame.display.set_mode((1200,700))
