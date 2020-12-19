def speedcheck(speed,limit):
    a = round(((speed - limit)/5)*2)
    if a < 12:
        print('Points: ' + str(dempoints))
    else:
        print('License suspended')

def showNumbers(limit):
    for i in range(0,(limit+1)):
        if i % 2 == 0:
            print(str(i) + " EVEN")
        else:
            print(str(i) + " ODD")

def showstars(rows):
    for i in range(1,(rows + 1)):
        print('*' * i)
