import requests
from JsonHelpers import jprint
import csv


def get_locations():
    # create blank list where location data will be saved
    locations = [["latitude", "longitude"]]

    # hone in on just vehicle data
    vehicles = vehicle.json()["data"]

    # iterate through each piece of vehicle data and pull the lat and long, adding it as a nested list
    for v in vehicles:
        data = v["attributes"]
        print(data)
        location_list = [data["latitude"], data["longitude"]]
        locations.append(location_list)

    # return nested list of locations
    return locations


def save_locations(file_name, location_data):
    # add .csv to filename and open file
    file = file_name + ".csv"
    with open(file, "w") as csv_file:
        # create writer object
        location_writer = csv.writer(csv_file)
        location_writer.writerows(location_data)


api_request_filter = {"filter[route_type]": "0,1", "filter[route]": "Red",
                      "api_key": "0e2ef1b620f74c8e99aa627d2f28316e"}

vehicle = requests.get("https://api-v3.mbta.com/vehicles", api_request_filter)

print(vehicle.status_code)
print(vehicle.headers)
jprint(vehicle.json())
locations = get_locations()
print(locations)

file_name = input("Enter file name:")

save_locations(file_name, locations)
