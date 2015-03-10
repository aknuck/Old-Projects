#converts plain text map to class tiles

from tile_classes import *
from load_tile_table import *


def tile_converter(board):
    for x in range(50):
        for y in range(50):
            if board[x][y] == ' ':
                board[x][y] = Terrain('plain')
            elif board[x][y] == '^':
                board[x][y] = Terrain('forest')
            elif board[x][y] == '8':
                board[x][y] = Terrain('mountain')
    for x in range(50):
        for y in range(50):
            try:
                board[x][y].n = board[x][y-1].typ
            except:
                board[x][y].n = 'forest'
            try:
                board[x][y].e = board[x+1][y].typ
            except:
                board[x][y].e = 'forest'
            try:
                board[x][y].s = board[x][y+1].typ
            except:
                board[x][y].s = 'forest'
            try:
                board[x][y].w = board[x-1][y].typ
            except:
                board[x][y].w = 'forest'

    images = load_tile_table('graphics/Tileset_2.png',32,32)
    mountain_top = pygame.image.load('graphics/mountain_top.png')
    road_base = pygame.image.load('graphics/road_base.png')
    #in tiles n_forest means it is a foest tile with another forest tile to the north
    #if its n_mountainL that means its the left mountain side tile with a forest tile to the north
    TILES = {'mountain_top':mountain_top,'plain':images[3][0],'nesw_forest':images[1][1],'s_mountainM':images[7][0],'mountainM':images[7][1],
             'n_forest':images[4][2],'e_forest':images[3][1],'s_forest':images[4][0],'w_forest':images[5][1],
             'ne_forest':images[0][2],'ns_forest':images[5][0],'nw_forest':images[2][2],'es_forest':images[0][0],'ew_forest':images[5][2],'sw_forest':images[2][0],
             'nes_forest':images[0][1],'nsw_forest':images[2][1],'new_forest':images[1][2],'esw_forest':images[1][0],'forest':images[3][2],
             'nsw_mountainL':images[6][0],'mountainL':images[6][1],'n_mountainL':images[6][2],'w_mountainL':images[7][2],'s_mountainL':images[8][2],'nw_mountainL':images[6][3],'sw_mountainL':images[7][3],'ns_mountainL':images[8][3],
             'nes_mountainR':images[8][0],'mountainR':images[8][1],'n_mountainR':images[6][4],'e_mountainR':images[7][4],'s_mountainR':images[8][4],'ne_mountainR':images[6][5],'es_mountainR':images[7][5],'ns_mountainR':images[8][5],
             'road':images[3][3],'ne_road':images[3][6],'nw_road':images[5][6],'es_road':images[3][4],'sw_road':images[5][4],'nes_road':images[3][5],'esw_road':images[4][4],'nsw_road':images[5][5],'new_road':images[4][6],'nesw_road':images[4][5],'ew_road':images[4][3],'ns_road':images[5][3],
             'farm':images[0][4],'logger':images[1][4],'mineL':images[0][5],'mineM':images[1][5],'mineR':images[2][5],
             'town':images[0][3]}
    

    #assigns an image just loaded to each tile
    #other idea to later try to add -  have a if tile north is forest, append 'n' to a string, if not , dont, and at the end append '_forest' to the string and put it in a dict to get the img
    for x in range(50):
        for y in range(50):
            if board[x][y].typ == 'plain':
                board[x][y].img = TILES['plain']
            elif board[x][y].typ == 'forest':
                if board[x][y].n == 'forest' or board[x][y].n == 'mountain':
                    if board[x][y].e == 'forest' or board[x][y].e == 'mountain':
                        if board[x][y].s == 'forest' or board[x][y].s == 'mountain':
                            if board[x][y].w == 'forest' or board[x][y].w == 'mountain':
                                board[x][y].img = TILES['nesw_forest']
                            else:
                                board[x][y].img = TILES['nes_forest']
                        else:
                            if board[x][y].w == 'forest' or board[x][y].w == 'mountain':
                                board[x][y].img = TILES['new_forest']
                            else:
                                board[x][y].img = TILES['ne_forest']
                    else:
                        if board[x][y].s == 'forest' or board[x][y].s == 'mountain':
                            if board[x][y].w == 'forest' or board[x][y].w == 'mountain':
                                board[x][y].img = TILES['nsw_forest']
                            else:
                                board[x][y].img = TILES['ns_forest']
                        else:
                            if board[x][y].w == 'forest' or board[x][y].w == 'mountain':
                                board[x][y].img = TILES['nw_forest']
                            else:
                                board[x][y].img = TILES['n_forest']
                else:
                    if board[x][y].e == 'forest' or board[x][y].e == 'mountain':
                        if board[x][y].s == 'forest' or board[x][y].s == 'mountain':
                            if board[x][y].w == 'forest' or board[x][y].w == 'mountain':
                                board[x][y].img = TILES['esw_forest']
                            else:
                                board[x][y].img = TILES['es_forest']
                        else:
                            if board[x][y].w == 'forest' or board[x][y].w == 'mountain':
                                board[x][y].img = TILES['ew_forest']
                            else:
                                board[x][y].img = TILES['e_forest']
                    else:
                        if board[x][y].s == 'forest' or board[x][y].s == 'mosssuntain':
                            if board[x][y].w == 'forest' or board[x][y].w == 'mountain':
                                board[x][y].img = TILES['sw_forest']
                            else:
                                board[x][y].img = TILES['s_forest']
                                
                        else:
                            if board[x][y].w == 'forest' or board[x][y].w == 'mountain':
                                board[x][y].img = TILES['w_forest']
                            else:
                                board[x][y].img = TILES['forest']
                #board[x][y].img = TILES['forest']
            elif board[x][y].typ == 'mountain':
                if board[x][y].w == 'mountain':
                    if board[x][y].e == 'mountain':
                        board[x][y].img = TILES['s_mountainM']
                    else:
                        board[x][y].img = TILES['nes_mountainR']
                    
                elif board[x][y].e == 'mountain':
                    board[x][y].img = TILES['nsw_mountainL']
                else:
                    board[x][y].img = TILES['mountain']


    
    return board,TILES
        
