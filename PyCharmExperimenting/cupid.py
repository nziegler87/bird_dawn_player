class Profile:

    def __init__(self):
        self.first_name = ""
        self.last_name = ""
        self.age = 0
        self.income = 0
        self.dogs = 0
        self.twilight = ""

    def get_information(self):
        self.first_name = input("What is your first name?\n")
        self.last_name = input("What is your last name?\n")
        self.age = int(input("What is your age?\n"))
        self.income = int(input("What is your annual income?\n"))
        self.dogs = int(input("How many dogs do you have?\n"))
        self.twilight = input("Are you team Jacob or team Edward?\n")

    def display_information(self):
        print("Thanks for entering your information. Here is what we have stored for you.")
        print("Name: {} {}\nAge: {}\nIncome: ${:.2f}\nYou have {} dogs\nYou are on team {}".format(self.first_name,
                                                                                                   self.last_name,
                                                                                                   self.age,
                                                                                                   self.income,
                                                                                                   self.dogs,
                                                                                                   self.twilight))


def main():
    profile = Profile()
    profile.get_information()
    profile.display_information()


if __name__ == '__main__':
    main()
