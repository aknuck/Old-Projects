#edger takes the basic board and corrects the edges so no plains touch mountain or single mountain tiles exist
def edger(board):
    for x in range(50):
        for y in range(50):
            if board[x][y] == '8':
                try:
                    if board[x-1][y] != '8' and board[x+1][y] != '8':
                        try:
                            board[x+1][y] = '8'
                        except:
                            board[x-1][y] = '8'
                except:
                    try:
                        board[x+1][y] = '8'
                    except:
                        board[x-1][y] = '8'
                        
    for x in range(50):
        for y in range(50):
            if board[x][y] == ' ':
                try:
                    if board[x-1][y] == '8' or board[x+1][y] == '8' or board[x][y-1] == '8' or board[x][y+1] == '8':
                        board[x][y] = '^'
                except:
                    pass
    return board
