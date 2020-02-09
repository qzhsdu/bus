import requests
from bs4 import BeautifulSoup
import bs4
data = []
stopSet = set()
sp = open("stop.txt","w")
class BusLine:
    def __init__(self,number,name,line,price,speed,interval):
        self.number = number
        self.name = name
        self.line = line
        self.price = price
        self.speed = speed
        self.interval =  interval 


urlLines = 'http://www.jnbus.com.cn/xianlu/XianLuList.jsp'
urlStops = 'http://www.jnbus.com.cn/xianlu/GetLineInfo.jsp'
bus = {}
def getBusNumber(page):      
    r = requests.get(urlLines, params={'pageno':page})
    soup = BeautifulSoup(r.text, 'html.parser')
    xianlu = soup.find_all(attrs={'class':'xianlucon'})
    xianlu = xianlu[0]
    xianlu = xianlu.table
    for tr in xianlu.find_all('tr'):
        if tr.a is not None:
            busName = tr.td.text
            busNumber = tr.a['onclick'][10:-2]
            bus[busName] = busNumber


def getStop(number):
    stops = []
    r = requests.post(urlStops,params={"id":number})
    soup = BeautifulSoup(r.text,'html.parser')
    line = soup.ul
    if line is  None:
        return -1
    for stop in line.find_all('li'):
        stop = stop.text
        stop = stop[stop.find(' ')+1:]
        stops.append(stop)
        print(stop,file=sp)
    return stops

for i in range(11):
    getBusNumber(page=i)

f=open('data.csv','wt') 
for name, code in bus.items():
    temp = getStop(number=code)
    if temp == -1:
        continue
    _bus = BusLine(name=name,number=code,
            line=temp,price=1,speed=1,interval=10)
    if name[0] == 'k':
        _bus.price = 2
    print(_bus.name,_bus.number,_bus.price,"+".join(_bus.line),len(_bus.line),file=f,sep=",")
f.close()
with open('D:/study/data structure project/bus.csv','wt') as b:
    for name, code in bus.items():
        print(name,code,sep=',',file=b)
sp.close()

    



