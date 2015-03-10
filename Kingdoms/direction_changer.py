#this functon looks throught the whole map and checks every tile redoing the .n and .e and so on

def direction_changer(board):
    for x in range(50):
        for y in range(50):
            try:
                board[x][y].n = board[x][y-1].typ
            except:
                pass
            try:
                board[x][y].e = board[x+1][y].typ
            except:
                pass
            try:
                board[x][y].s = board[x][y+1].typ
            except:
                pass
            try:
                board[x][y].w = board[x-1][y].typ
            except:
                pass
    return board
                
