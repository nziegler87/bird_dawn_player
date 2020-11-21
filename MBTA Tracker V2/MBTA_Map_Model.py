from JsonHelpers import check_json_error
from Vehicle import Vehicle
from Settings import *
import requests
import csv

VEHICLES = []
STOPS = {}
ROUTES = {}


def update_vehicle_list(request_filters=None):
    """
    Uses the global API key and provided filters (a dictionary) to generate a list of vehicle objects.

    :param request_filters: a dictionary of filters, optional
    :return: a list of vehicle objects
    """

    VEHICLES.clear()

    # if routes and stops dictionaries have not been updated, do so now
    update_routes_stops()

    # add api_key to filters and pull raw data
    if request_filters is None:
        request_filters = {}

    request_filters["api_key"] = API_KEY
    raw_data = requests.get(VEHICLES_URL, request_filters)

    # confirm that pull is correct
    if check_json_error(raw_data):
        return

    # iterate through each dictionary in the json data list and make vehicle objects
    vehicles_json = raw_data.json()["data"]
    for vehicle in vehicles_json:
        temp_vehicle = create_vehicle_object(vehicle)
        VEHICLES.append(temp_vehicle)

    humanize_vehicles()


def get_vehicle_list():
    """
    Returns a copy of the vehicle list.

    :return: a copy of the list of vehicles
    """
    return VEHICLES.copy()


def create_vehicle_object(vehicle_data):
    """
    Creates a vehicle object using JSON data and returns the object.

    :param vehicle_data:
    :return: the vehicle object
    """
    bearing = vehicle_data["attributes"]["bearing"]
    current_status = vehicle_data["attributes"]["current_status"]
    current_stop_sequence = vehicle_data["attributes"]["current_stop_sequence"]
    direction_id = vehicle_data["attributes"]["direction_id"]
    label = vehicle_data["attributes"]["label"]
    latitude = vehicle_data["attributes"]["latitude"]
    longitude = vehicle_data["attributes"]["longitude"]
    occupancy_status = vehicle_data["attributes"]["occupancy_status"]
    speed = vehicle_data["attributes"]["speed"]
    updated_at = vehicle_data["attributes"]["updated_at"]
    route = vehicle_data["relationships"]["route"]["data"]["id"]
    try :
        stop_id = vehicle_data["relationships"]["stop"]["data"]["id"]
    except TypeError:
        stop_id = "Error"

    # create and add temp_vehicle to list
    temp_vehicle = Vehicle(bearing, current_status, current_stop_sequence, direction_id, label, latitude, longitude,
                           occupancy_status, speed, updated_at, route, stop_id)

    return temp_vehicle


def humanize_vehicles():
    """
    Iterates through each item in the vehicle list and uses stop and routes info to convert what could be considered as
    arbitrary data to something understood (for example direction being 0 or 1 to Oak Grove or Forrest Hills).

    :return: nothing
    """
    if len(STOPS) != 0 and len(ROUTES) != 0:
        for vehicle in VEHICLES:

            # update stop_id with stop name
            if vehicle.stop_id in STOPS.keys():
                vehicle.stop_id = STOPS[vehicle.stop_id]

            # update the direction id with the terminus name
            if vehicle.route in ROUTES.keys():
                vehicle.direction_id = ROUTES[vehicle.route][vehicle.direction_id]

            # update the current status with easier-to-read info
            if vehicle.current_status in CURRENT_STATUS_DICT.keys():
                vehicle.current_status = CURRENT_STATUS_DICT[vehicle.current_status]


def update_routes_stops():
    """
    Loads stop and route information from corresponding csv files

    :return: nothing
    """
    if not STOPS:
        with open(STOPS_FILENAME, "r") as csv_file:
            csv_reader = csv.reader(csv_file)
            for row in csv_reader:
                STOPS[row[0]] = row[1]

    if not ROUTES:
        with open(ROUTES_FILENAME, "r") as csv_file:
            csv_reader = csv.reader(csv_file)
            for row in csv_reader:
                ROUTES[row[0]] = [row[1], row[2]]
