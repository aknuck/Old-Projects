#FILE GOAL
#check if a target was hit int he range of an attack

import pygame
from pygame.locals import *

pygame.init()

def check_hit(hitRect,target):
    targetRect = pygame.Rect(target.x,target.y-250,60,250)
    if targetRect.colliderect(hitRect):
        return True
    else:
        return False
