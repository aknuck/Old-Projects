import random

def getNewBoard():
        # Create a new 20x10 board data structure.
    board = []
    count = 0
    for x in range(50): # the main list is a list of 20 lists
        board.append([])
        for y in range(50): # each list in the main list has 10 single-character strings
            # use different characters for the terrain to make it more readable.
            if count < 1:
                board[x].append(' ')
            if board[(x-1)][y] == (' '):
                if random.randint(1,7) == 6:
                    board[x].append('^')
                else:
                    board[x].append(' ')
            elif board[(x-1)][y] == ('^'):
                if random.randint(1,25) == 20:
                    board[x].append('8')
                elif random.randint(1,12) == 5:
                    board[x].append(' ')
                else:
                    board[x].append('^')
            if board[(x-1)][y] == ('8'):
                if random.randint(1,4) == 3:
                    board[x].append('^')
                else:
                    board[x].append('8')
            count = count + 1
    return board
