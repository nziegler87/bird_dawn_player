import requests
import csv
from datetime import date

from JsonHelpers import check_json_error
from Settings import *

LOG_FILE = "log.txt"


def save_routes():
    """
    Uses MBTA API to pull a list of all rail and light rail routes and save them to a CSV file with three columns:
    ID, Route Direction 0, Route Direction 1.
    
    :return: nothing
    """
    raw_data = requests.get(ROUTES_URL, STANDARD_ROUTES_FILTER)

    # confirm that pull is correct
    if check_json_error(raw_data):
        return

    with open(ROUTES_FILENAME, "w") as outfile:
        csv_writer = csv.writer(outfile)

        for i in range(len(raw_data.json()["data"])):
            route_id = raw_data.json()["data"][i]["id"]
            route_name = raw_data.json()["data"][i]["attributes"]["direction_destinations"]
            csv_writer.writerow([route_id, route_name[0], route_name[1]])

    write_log(ROUTES_FILENAME)


def save_stops():
    """
    Uses MBTA API to pull a list of all rail and light rail stops and saves them to a CSV file with three columns:
    stop id, stop name.

    :return: nothing
    """
    raw_data = requests.get(STOPS_URL, STANDARD_STOPS_FILTER)

    # confirm that pull is correct
    if check_json_error(raw_data):
        return

    with open(STOPS_FILENAME, "w") as outfile:
        csv_writer = csv.writer(outfile)

        for i in range(len(raw_data.json()["data"])):
            station_id = raw_data.json()["data"][i]["id"]
            station_name = raw_data.json()["data"][i]["attributes"]["name"]
            csv_writer.writerow([station_id, station_name])

    write_log(STOPS_FILENAME)


def write_log(file_name):
    """
    Appends a txt log file, a record of when routes and stops have been last updated.

    :param file_name: log file name and location, a txt file
    :return: nothing
    """
    with open(LOG_FILE, "a") as outfile:
        outfile.write(file_name + " updated on " + str(date.today()) + "\n")
