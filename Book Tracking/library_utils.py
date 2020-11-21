def check_valid_isbn(isbn):
    """
    Takes in a 10 or 13-digit ISBN number, either as an int or a string, and check to see if it is a valid number.

    :param isbn: a 10 or 13-digit ISBN number, either a string or an int
    :return: True if it is a valid number, otherwise false`
    """

    # check that number is either 10 or 13 digits
    if len(isbn) != 10 and len(isbn) != 13:
        return False

    # perform check on 13 digit ISBN
    check_sum = 0
    if len(isbn) == 13:
        # step 1

        multiplier = 1
        for i in range(len(isbn[:-1])):
            number_calc = int(isbn[i]) * multiplier
            check_sum += number_calc
            if multiplier == 1:
                multiplier = 3
            else:
                multiplier = 1

        # step 2
        modulo_calc = check_sum % 10

        # step 3
        if modulo_calc != 0:
            modulo_calc = 10 - modulo_calc

        # step 4
        return modulo_calc == int(str(isbn)[-1])

    # perform check on 10 digit ISBN
    else:
        multiplier = 10
        for i in range(len(isbn)):
            number_calc = int(isbn[i]) * multiplier
            check_sum += number_calc
            multiplier -= 1

        return check_sum % 11 == 0


def remove_comma(string):
    """
    Removes commas from a string.
    :param string: a string that may or may not have commas
    :return: a string with the commas removed
    """
    return "".join(string.split(","))