#this file takes mouse click input for the minimap and adjusts the camera to the correct poisiton

def click_minimap(x,y,mx,my):
    x -= 22
    y -= 578
    print x
    print y

    if x <= 35:
        mx = 0
    elif x >= 65:
        mx = 500
    else:
        mx = ((x)*16)-500
    if y <= 22:
        my = 0
    elif y >= 78:
        my = 890
    else:
        my = ((y)*16)-318

    return mx,my
