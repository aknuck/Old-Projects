#Random Game generator
#takes elements from three files and produces a random game!

import random

def get_list(file_name):
    r = open(file_name,'r')
    r = r.read()
    lst = r.split(';')
    return lst

def main():
    print("Welcome to the Random Game Generator!")
    print("             Let us begin\n\n")
    running = True
    while running == True:
        genres = get_list('game_genres.txt')
        types = get_list('game_types.txt')
        feels = get_list('game_feels.txt')
        genre = random.choice(genres).rstrip().lstrip()
        typ = random.choice(types).rstrip().lstrip()
        feel = random.choice(feels).rstrip().lstrip()

        print("\n>> How about a "+feel+" "+typ+" "+genre+" game?")
        dec = raw_input("\nDo you want a new one?(y/n):")
        if dec == 'n':
            running = False

if __name__ == "__main__":
    main()
