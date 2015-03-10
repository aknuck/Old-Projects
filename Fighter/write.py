#FILE GOAL
#write onto screen

import pygame
from pygame.locals import *
pygame.init()

def write(x,y,color,msg,fontObj,screen):
    msgSurfaceObj = fontObj.render(msg, False, color)
    msgRectobj = msgSurfaceObj.get_rect()
    msgRectobj.topleft = (x,y)
    screen.blit(msgSurfaceObj,msgRectobj)
