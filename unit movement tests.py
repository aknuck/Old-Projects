import sys,pygame,random,math
from pygame.locals import *

pygame.init()
fpsClock = pygame.time.Clock()

window = pygame.display.set_mode((640,640))
pygame.display.set_caption('unit move tests')

red = pygame.Color(255,0,0)
blue = pygame.Color(0,0,255)
green = pygame.Color(0,255,0)
white = pygame.Color(255,255,255)
black = pygame.Color(0,0,0)
mx,my = 0,0
units = [[100,100,100,100,0,0,100,32,0,5],[350,100,350,100,0,0,100,32,0,5],[600,100,600,100,0,0,100,32,0,5]]
points = []
mouse_down = False
square = [0,0,0,0]
radius = 5
radius_selected = 12
speed = 2
shmum = 0
checker_lister_x = []
checker_lister_y = []
attack_range = 2
fontObj = pygame.font.Font('freesansbold.ttf',10)

while True:
    window.fill(white)
    for unit in units:
        if unit[7] < 32:
            unit[7] += 1
        if unit[4] == 1:
            pygame.draw.circle(window,green,(int(unit[0]),int(unit[1])),radius_selected,0)
        pygame.draw.circle(window,red,(int(unit[0]),int(unit[1])),radius,0)

        msg = str(unit[6])
        msgSurfaceObj = fontObj.render(msg, False, blue)
        msgRectobj = msgSurfaceObj.get_rect()
        msgRectobj.topleft = (unit[0],unit[1])
        window.blit(msgSurfaceObj,msgRectobj)
        
        if unit[0] != unit[2] or unit[1] != unit[3]:
            x = unit[0]-unit[2]
            y = unit[1]-unit[3]
            b = math.sqrt((x*x)+(y*y))
            if b != 0:
                checker_lister_x = []
                checker_lister_y = []
                for unit2 in units:
                    if unit != unit2:
                        if math.sqrt(((((unit[0]-((speed*x)/b))-unit2[0])**2) + ((unit[1]-unit2[1])**2))) >= (radius+radius):
                            checker_lister_x.append(0)
                        else:
                            checker_lister_x.append(1)
                            
                        if math.sqrt(((((unit[1]-((speed*y)/b))-unit2[1])**2) + ((unit[0]-unit2[0])**2))) >= (radius+radius):
                            checker_lister_y.append(0)
                        else:
                            checker_lister_y.append(1)
                if 1 in checker_lister_x:
                    speed = speed
                else:
                    unit[0] = unit[0]-(speed*x)/b
                    if 1 in checker_lister_y:
                        speed = speed
                    else:
                        unit[1] = unit[1]-(speed*y)/b
            if (unit[0] + speed + 0.1) > unit[2] and unit[0] < unit[2]:
                unit[2] = unit[0]
            if (unit[1] - speed - 0.1) < unit[3] and unit[1] > unit[3]:
                unit[3] = unit[1]
    for point in points:
        if point[2] > 0:
            pygame.draw.circle(window,blue,(point[0],point[1]),4,0)
            point[2] -= 1
        else:
            points.remove(point)
    for unit in units:
            for unit2 in units:
                if unit != unit2:
                    if math.sqrt(((((unit[0]-unit2[0])**2) + ((unit[1]-unit2[1])**2)))) <= (radius+radius+2):
                        if unit[7] == 32:
                            unit2[6] -= unit[9]
                            unit[8] += unit[9]
                            unit[7] = 0
                            if unit[8] >= 150 and unit[9] != 8:
                                 unit[6] = 150
                                 unit[9] = 8
                            pygame.draw.line(window,black,(unit[0],unit[1]),(unit2[0],unit2[1]),3)
    for unit in units:
        if unit[6] <= 0:
            units.remove(unit)
    
    if mouse_down == True:
        if square[0] != square[2] and square[1] != square[3]:
            pygame.draw.line(window,blue,(square[0],square[1]),(square[2],square[1]),3)
            pygame.draw.line(window,blue,(square[0],square[1]),(square[0],square[3]),3)
            pygame.draw.line(window,blue,(square[2],square[1]),(square[2],square[3]),3)
            pygame.draw.line(window,blue,(square[2],square[3]),(square[0],square[3]),3)
     
    for event in pygame.event.get():
        if event.type == QUIT:
            pygame.quit()
            sys.exit()
        elif event.type == MOUSEMOTION:
            mx,my = event.pos
            if mouse_down == True:
                square[2],square[3] = mx,my
        elif event.type == MOUSEBUTTONDOWN:
            mx,my = event.pos
            if event.button == 3:
                for unit in units:
                    if unit[4] == 1:
                        unit[2],unit[3] = mx,my
                points.append([mx,my,32])
            if event.button == 2:
                mx,my = event.pos
                units.append([mx,my,mx,my,0,0,100,32,0,5])
            elif event.button == 1:
                if mouse_down == False:
                    for unit in units:
                        unit[4] = 0
                    mouse_down = True
                    square[0],square[1],square[2],square[3] = mx,my,mx,my
                for unit in units:
                    if mx+(radius-1) > unit[0] and unit[0] > mx-(radius-1) and my+(radius-1) > unit[1] and unit[1] > my-(radius-1):
                        unit[4] = 1
            
        elif event.type == MOUSEBUTTONUP:
            mx,my = event.pos
            if mouse_down == True:
                mouse_down = False
                for unit in units:
                    if square[0] > square[2]:
                        if square[1] > square[3]:
                            if int(unit[0]) in range(square[2],square[0]) and int(unit[1]) in range(square[3],square[1]):
                                unit[4] = 1
                        else:
                            if int(unit[0]) in range(square[2],square[0]) and int(unit[1]) in range(square[1],square[3]):
                                unit[4] = 1
                    else:
                        if square[1] > square[3]:
                            if int(unit[0]) in range(square[0],square[2]) and int(unit[1]) in range(square[3],square[1]):
                                unit[4] = 1
                        else:
                            if int(unit[0]) in range(square[0],square[2]) and int(unit[1]) in range(square[1],square[3]):
                                unit[4] = 1

        
    pygame.display.update()
    fpsClock.tick(32)
