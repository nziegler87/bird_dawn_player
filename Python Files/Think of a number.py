while True:
    print('Think of a number between 1 and 10')
    print('Did you think of a number? Y or N?')
    Response = input()
    if Response == 'Y':
        print('Great! Keep that number in your head')
        break
    else:
        print('Okay. You need to think of a number. Try again.')

import random
computerguess = random.randint(1,10)

count = 0
while True:
    print ('Is it ' + str(computerguess) + ' ? Y or N?')
    yesno = input()
    count = count + 1
    if yesno == 'Y':
        print('I guessed your number in ' + str(count) + ' try(s)!')
        break
    else:
        print('Is your number higher or lower than this number? H or L?')
        higherlower = input()
        if higherlower == 'H':
            computerguess = computerguess + 1
            print(computerguess)
        else:
            computerguess = computerguess - 1
            print(computerguess)
        

        
