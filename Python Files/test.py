def main():
    while True:
        user_a_number = int(input("Enter a number for friend to guess: "))
        print("\n" * 40)
        while True:
            number = int(input("What number did you friend guess?\n"))
            if number == user_a_number:
                print("That is correct!\n\n")
                break
            else:
                print("That is not the number I'm thinking. Guess again.\n\n")

main()
