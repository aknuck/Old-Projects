import sys
sys.path.append('bin')
from list_checker import *
def edge_finder(board,x,y,building):
    add = [str(building)]
    x = int(x)
    y = int(y)
    if board[x][(y-1)] == '^':
        add.append('f')
    elif list_checker('mountain',board[x][(y-1)]) == True:
        add.append('f')
    elif board[x][(y-1)] == '8':
        add.append('m')
    else:
        add.append('n')
    if board[(x+1)][y] == '^':
        add.append('f')
    elif list_checker('mountain',board[(x+1)][y]) == True:
        add.append('f')
    elif board[(x+1)][y] == '8':
        add.append('m')
    else:
        add.append('n')
    if board[x][(y+1)] == '^':
        add.append('f')
    elif list_checker('mountain',board[x][(y+1)]) == True:
        add.append('f')
    elif board[x][(y+1)] == '8':
        add.append('m')
    else:
        add.append('n')
    if board[(x-1)][y] == '^':
        add.append('f')
    elif list_checker('mountain',board[(x-1)][y]) == True:
        add.append('f')
    elif board[(x-1)][y] == '8':
        add.append('m')
    else:
        add.append('n')
    return add
