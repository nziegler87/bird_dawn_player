import datetime


class Person:
    def __init__(self, name, birth_month, birth_date, birth_year):
        self._name = name
        self._month = birth_month
        self._date = birth_date
        self._year = birth_year

    def get_name(self):
        return self._name

    '''
    Name: get_age
    Parameters: self
    Returns: the age of the person in years, an int
    '''
    def get_age(self):
        # calculate rough age
        current_date = datetime.datetime.now()
        age = current_date.year - self._year

        if not self.birthday_occurred():
            return age - 1

        return age

    '''
    Name: get_birthday_string
    Parameters: self
    Returns: a string version of the person's birthday formatted as DAY/MONTH/YEAR
    '''
    def get_birthday_string(self):
        return str(self._date) + "/" + str(self._month) + "/" + str(self._year)

    '''
    Name: get_birthday_string
    Parameters: self
    Returns: a list version of the person's birthday: [day, month, year]
    '''
    def get_birthday_list(self):
        return [self._date, self._month, self._year]

    '''
    Name: birthday_occurred
    Parameters: self
    Returns: False if the person's birthday has not yet occurred this year, otherwise True
    '''

    def birthday_occurred(self):
        current_date = datetime.datetime.now()

        if current_date.month > self._month:
            return True

        if current_date.month == self._month:
            if current_date.day >= self._date:
                return True

        return False
