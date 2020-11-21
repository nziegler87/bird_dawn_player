import json


def json_print(json_object, indent):
    """
    Uses json.dumps to print the json_object with the specified indent
    :param json_object: a json object
    :param indent: indentation level, an int
    :return: nothing
    """
    print(json.dumps(json_object, indent=indent))


def check_json_error(json_object):
    """
    Check to make sure that json pull is successful. If not successful, print status code and return true.

    :param json_object:
    :return: True if status_code does not equal 200
    """
    if json_object.status_code != 200:
        print("Error retrieving JSON file. Status code:" + str(json_object.status_code))
        return True
