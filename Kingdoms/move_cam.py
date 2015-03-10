#will adjust the camera around the map based on the mouse motion (near the edge of the screen moves the camera, also arrow keys)

#x,y correspond to mouse position
#mx,my corrospond to the amount(pixels) already moved in either direction
def move_cam(x,y,mx,my):
    if x > 1000 and mx < 500 and y < 560:
        move = (x - 1000)/8
        mx += move
    elif x < 100 and mx > 0 and y < 560:
        move = (100-x)/8
        mx -= move
    if y > 460 and y < 560 and my < 890:
        move = (y - 460)/8
        my += move
    elif y < 100 and my > 20:
        move = (100-y)/8
        my -= move

    return mx,my
