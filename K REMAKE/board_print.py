#prints text version of board

def board_print(board):
    lines = []

    for x in range(50):
        lines.append('')
        for y in range(50):
            lines[x] += board[x][y]

    for x in range(50):
        print lines[x]
