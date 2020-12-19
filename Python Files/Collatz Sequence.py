def collatz(number):
    if number % 2 == 0:
        print(int(number // 2))
    else:
        print(int(number * 3 + 1))

# Start of program
print('Enter integer other than 1')
number = input()
while int(number) !=1:
    collatz(number)
    print(number)
    



