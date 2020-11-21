API_KEY = "0e2ef1b620f74c8e99aa627d2f28316e"
BASE_FILTER = {"api_key": "0e2ef1b620f74c8e99aa627d2f28316e"}

ROUTES_URL = "https://api-v3.mbta.com/routes"
STOPS_URL = "https://api-v3.mbta.com/stops"
VEHICLES_URL = "https://api-v3.mbta.com/vehicles"

# For this project, only looking at the location of light rail and subway cars
STANDARD_VEHICLE_FILTER = {"filter[route_type]": "0,1"}
STANDARD_STOPS_FILTER = {"filter[route_type]": "0,1"}
STANDARD_ROUTES_FILTER = {"filter[type]": "0,1"}

ROUTES_FILENAME = "MBTA_routes.csv"
STOPS_FILENAME = "MBTA_stops.csv"
CSV_HEADER = ["id", "name"]

CURRENT_STATUS_DICT = {"IN_TRANSIT_TO": "In transit to", "STOPPED_AT": "Stopped at", "INCOMING_AT": "Incoming at"}
