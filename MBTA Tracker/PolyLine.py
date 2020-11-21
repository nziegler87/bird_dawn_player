import requests

SHAPES_URL = "https://api-v3.mbta.com/shapes"
API_KEY = "0e2ef1b620f74c8e99aa627d2f28316e"
ROUTE = "Red"
BASE_FILTER = {"api_key": API_KEY, "route": ROUTE}


raw_data = requests.get(SHAPES_URL, BASE_FILTER)

