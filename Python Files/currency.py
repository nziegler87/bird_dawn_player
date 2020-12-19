'''
    CS5001
    HW1 - Programming #2
    Nathanial Ziegler

    Test case #1:
    Input: $10
    Total fee: $4.80

    Test case #2:
    Input: $629
    Total fee: $23.37

    Test case #3:
    Input: $389
    Total fee: $16.17
'''


def main():
    conversion_fee = 4.50
    amount = float(input("Please enter an amount of money to convert.\n"))
    total_fee = amount * .03 + conversion_fee
    print("Cheesy Joe's charges you ${:.2f}".format(total_fee))

main()
