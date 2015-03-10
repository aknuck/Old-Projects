#FILE GOAL
#update the x and y position of and player passed to it

#import pygame
#from pygame.locals import *

def movement(player):
    if player.y < 620 and player.accDir == 'down':
            #fall at 4 pixels/sec/sec
            player.acc += 4
            if player.y + player.acc < 620:
                player.y += player.acc
            else:
                player.y = 620
                player.acc = 0
    else:
        player.acc -= 4
        if player.acc > 0:
            player.y -= player.acc
        else:
            player.accDir = 'down'
    if player.moveHor == 'left':
        if player.horAcc < 10:
            player.horAcc += 5
        if player.x - player.horAcc >= 0:
            if player.pos == 'upright':
                player.x -= player.horAcc
            elif player.pos == 'crouch':
                player.x -= (player.horAcc/2)
        else:
            player.x = 0
    elif player.moveHor == 'right':
        if player.horAcc < 10:
            player.horAcc += 5
        if player.x + player.horAcc <= 940:
            if player.pos == 'upright':
                player.x += player.horAcc
            elif player.pos == 'crouch':
                player.x += (player.horAcc/2)
        else:
            player.x = 940
    return player
