#updates the tiles around after a tile is placed/changed
from direction_changer import *

def tile_changer(board,TILES,bx,by,newtile):
    if newtile == 'logger':
        board[bx][by].typ = 'logger'
        if board[bx-1][by-1].typ == 'forest':
            board[bx-1][by-1].typ = 'plain'
            board[bx][by].val += 1
        if board[bx][by-1].typ == 'forest':
            board[bx][by-1].typ = 'plain'
            board[bx][by].val += 1
        if board[bx+1][by-1].typ == 'forest':
            board[bx+1][by-1].typ = 'plain'
            board[bx][by].val += 1
        if board[bx-1][by].typ == 'forest':
            board[bx-1][by].typ = 'plain'
            board[bx][by].val += 1
        if board[bx+1][by].typ == 'forest':
            board[bx+1][by].typ = 'plain'
            board[bx][by].val += 1
        if board[bx-1][by+1].typ == 'forest':
            board[bx-1][by+1].typ = 'plain'
            board[bx][by].val += 1
        if board[bx][by+1].typ == 'forest':
            board[bx][by+1].typ = 'plain'
            board[bx][by].val += 1
        if board[bx+1][by+1].typ == 'forest':
            board[bx+1][by+1].typ = 'plain'
            board[bx][by].val += 1
        board = direction_changer(board)
        print board[bx+2][by].n
        print board[bx+2][by].e
        print board[bx+2][by].s
        print board[bx+2][by].w
    else:
        board[bx-1][by].e = newtile
        board[bx+1][by].w = newtile
        board[bx][by-1].s = newtile
        board[bx][by+1].n = newtile
    
    for x in range(bx-3,bx+3):
        for y in range(by-3,by+3):
            if x >= 0 and y >= 0:
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
                    if board[x][y].w == 'mountain' or board[x][y].w == 'mine':
                        if board[x][y].e == 'mountain' or board[x][y].e == 'mine':
                            if board[x][y].s == 'forest' or board[x][y].s == 'mountain' or board[x][y].s == 'mine':
                                board[x][y].img = TILES['s_mountainM']
                            else:
                                board[x][y].img = TILES['mountainM']
                        else:
                            if board[x][y].n == 'forest' or board[x][y].n == 'mountain' or board[x][y].n == 'mine':
                                if board[x][y].e == 'forest':
                                    if board[x][y].s == 'forest' or board[x][y].s == 'mountain' or board[x][y].s == 'mine':
                                        board[x][y].img = TILES['nes_mountainR']
                                    else:
                                        board[x][y].img = TILES['ne_mountainR']
                                else:
                                    if board[x][y].s == 'forest'or board[x][y].s == 'mountain' or board[x][y].w == 'mine':
                                        board[x][y].img = TILES['ns_mountainR']
                                    else:
                                        board[x][y].img = TILES['n_mountainR']
                            else:
                                if board[x][y].e == 'forest':
                                    if board[x][y].s == 'forest' or board[x][y].s == 'mountain' or board[x][y].s == 'mine':
                                        board[x][y].img = TILES['es_mountainR']
                                    else:
                                        board[x][y].img = TILES['e_mountainR']
                                else:
                                    if board[x][y].s == 'forest' or board[x][y].s == 'mountain' or board[x][y].s == 'mine':
                                        board[x][y].img = TILES['s_mountainR']
                                    else:
                                        board[x][y].img = TILES['mountainR']
                        
                    elif board[x][y].e == 'mountain' or board[x][y].e == 'mine':
                        if board[x][y].n == 'forest' or board[x][y].n == 'mountain' or board[x][y].n == 'mine':
                            if board[x][y].w == 'forest':
                                if board[x][y].s == 'forest' or board[x][y].s == 'mountain' or board[x][y].s == 'mine':
                                    board[x][y].img = TILES['nsw_mountainL']
                                else:
                                    board[x][y].img = TILES['nw_mountainL']
                            else:
                                if board[x][y].s == 'forest'or board[x][y].s == 'mountain' or board[x][y].s == 'mine':
                                    board[x][y].img = TILES['ns_mountainL']
                                else:
                                    board[x][y].img = TILES['n_mountainL']
                        else:
                            if board[x][y].w == 'forest':
                                if board[x][y].s == 'forest' or board[x][y].s == 'mountain' or board[x][y].s == 'mine':
                                    board[x][y].img = TILES['sw_mountainL']
                                else:
                                    board[x][y].img = TILES['w_mountainL']
                            else:
                                if board[x][y].s == 'forest' or board[x][y].s == 'mountain' or board[x][y].s == 'mine':
                                    board[x][y].img = TILES['s_mountainL']
                                else:
                                    board[x][y].img = TILES['mountainL']
                    else:
                        board[x][y].img = TILES['mountainM']
                if board[x][y].typ == 'road':
                    stri = ''
                    if board[x][y].n == 'road':
                        stri += 'n'
                    if board[x][y].e == 'road':
                        stri += 'e'
                    if board[x][y].s == 'road':
                        stri += 's'
                    if board[x][y].w == 'road':
                        stri += 'w'
                    if stri == 's' or stri == 'n':
                        stri = 'ns'
                    if stri == 'e' or stri == 'w':
                        stri = 'ew'
                    if stri != '':
                        stri += '_'
                    stri += 'road'
                    stri.join('')
                    board[x][y].img = TILES[stri]
                if board[x][y].typ == 'farm':
                    board[x][y].img = TILES['farm']
                elif board[x][y].typ == 'logger':
                    board[x][y].img = TILES['logger']
                elif board[x][y].typ == 'mine':
                    if board[x][y].w == 'mountain' or board[x][y].w == 'mine':
                        if board[x][y].e == 'mountain' or board[x][y].e == 'mine':
                            board[x][y].img = TILES['mineM']
                        else:
                            board[x][y].img = TILES['mineR']
                    else:
                        board[x][y].img = TILES['mineL']
                elif board[x][y].typ == 'town':
                    board[x][y].img = TILES['town']
                
                        
                        
    return board


