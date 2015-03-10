# -*- coding: utf-8 -*-
import urllib,urllib2,threading,datetime,pygame
from time import sleep


class Headline:

    def __init__(self,headline):
        self.headline = headline
        self.xPos = 1200


class Stock:

    def __init__(self,symbol):
        self.opener = urllib2.build_opener()
        self.opener.addheaders = [('User-agent', 'Mozilla/5.0')]
        self.symbol = symbol
        self.price = self.getPrice()
        self.direction = self.getDirection()
        self.xPos = 1200

    def getPrice(self):
        html = self.opener.open('http://www.nasdaq.com/symbol/'+self.symbol.lower()+'/real-time').read()
        return float(html[html.index('<div id="qwidget_lastsale" class="qwidget-dollar">')+51:html.index('<div class="qwidget-dollar">')-25])

    def getDirection(self):
        html = self.opener.open('http://www.nasdaq.com/symbol/'+self.symbol.lower()+'/real-time').read()
        dirColor = html[html.index('<div id="qwidget_netchange" class="qwidget-cents qwidget-')+57:html.index('<div id="qwidget-arrow">')-27]
        if dirColor == 'Green':
            return '▲'
        else:
            return '▼'
        
    
npr_News_feed = (urllib.urlopen("http://www.npr.org/rss/rss.php?id=1001"),'News')
npr_Stories_feed = (urllib.urlopen("http://www.npr.org/rss/rss.php?id=9999"),'Stories from NPR')
npr_Politics_feed = (urllib.urlopen("http://www.npr.org/rss/rss.php?id=1014"),'Politics')
npr_feeds = [npr_Stories_feed,npr_Politics_feed,npr_News_feed]

stocks = ['GOOG','AAPL','AMZN','YHOO','V','WMT'] #Add any stock symbol you would like to see

stock_classes = []
newsBits = []


print 'Starting at: '+str(datetime.datetime.time(datetime.datetime.now()))
links = 'null'

while links == 'null':
    links = raw_input('Do you want links displayed?(y/n): ')
    if links.lower() == 'y':
        links = True
    elif links.lower() == 'n':
        links = False
    else:
        links = 'null'
descriptions = 'null'

while descriptions == 'null':
    descriptions = raw_input('Do you want descriptions displayed?(y/n): ')
    if descriptions.lower() == 'y':
        descriptions = True
    elif descriptions.lower() == 'n':
        descriptions = False
    else:
        descriptions == 'null'
        
print 'Loading News...'
for stock in stocks:
    stock_classes.append(Stock(stock))
    
print '--------------------------------------'
print '>> Stocks'
print '--------------------------------------'
for stock in stock_classes:
    print stock.symbol+' '+str(stock.price)+' '+stock.direction

for feed in npr_feeds:
    skip = False
    for line in feed[0]:
        #print line
        if line[6:13] == '<title>':
            if line[13:len(line)-9] == feed[1]:
                print '--------------------------------------'
                print '>> '+line[13:len(line)-9]
                print '--------------------------------------'
            else:
                print '\n> '+line[13:len(line)-9]
            newsBits.append(Headline(line[13:len(line)-9]))
        if descriptions and feed[1] != 'Stories from NPR':
            if line[6:19] == '<description>':
                leng = len(line[19:len(line)-15])
                cur = 75
                print '\t[DESCRIPTION] '+line[19:cur+19]
                while cur+75 < leng:
                    print '\t             '+line[19+cur:19+cur+75]
                    cur += 75
                print '\t             '+line[cur+19:len(line)-15]
        if links and feed[1] != 'Stories from NPR':
            if line[12:16] == 'http' and not skip:
                skip = True
                print '\t[LINK] '+line[12:len(line)-8]
            elif line[12:16] and skip:
                skip = False
