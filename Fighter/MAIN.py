#FILE GOAL
#setup screen
#send to menu

import pygame,sys
from pygame.locals import *

pygame.init()

gametype = 'none'

while gametype != 'quit':
    gametype = menu()

    if gametype == 'battlevCPU':
        CPU_Battle()
    elif gametype == 'battlevPlayer':
        Player_Battle()
    elif gametype == 'story':
        story

pygame.quit()
sys.exit()
