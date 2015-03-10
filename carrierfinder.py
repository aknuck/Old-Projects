from urllib2 import urlopen

def find(before,after,page):
    text = ""
    index = page.index(before)+len(before)
    while True:
        if page[index:index+len(after)] != after:
            text += page[index]
            index += 1
        else:
            break
    return text

number = raw_input("Number: ")
numbers = []
numbers.append(number[0:3])
numbers.append(number[3:6])
numbers.append(number[6:10])
if len(numbers) == 3 and len(numbers[0]) == 3 and len(numbers[1]) == 3 and len(numbers[2]) == 4:
    page = urlopen("http://www.fonefinder.net/findome.php?npa="+numbers[0]+"&nxx="+numbers[1]+"&thoublock="+numbers[2]).read()
    print "*------------*"
    print "Number - ("+numbers[0]+")-"+numbers[1]+"-"+numbers[2]
    print "Carrier - "+find("<A HREF='http://fonefinder.net/",".php",page)
    print "State - "+find("&state=","'>",page)
    print "City - "+find("<A HREF='findcity.php?cityname=","&state=",page)

#<A HREF='http://fonefinder.net/
