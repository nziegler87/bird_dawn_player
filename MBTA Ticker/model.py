import requests
import json

MBTA_API_URL = "https://api-v3.mbta.com/"
API_KEY = "0e2ef1b620f74c8e99aa627d2f28316e"



def retrieve_routes():
        output_data = {}
        custom_url = "routes"
        request_url = MBTA_API_URL + custom_url
        # 0 = light rail (green line) & 1 = heavy rail (red line)
        route_type = "0,1"
        request_filter = {}
        request_filter["api_key"] = API_KEY
        request_filter["type"] = route_type
        raw_data = requests.get(request_url, request_filter)
        if ( raw_data.status_code != 200 ):    
                return {"ERROR": str(raw_data.status_code)}

        return raw_data.json()

def print_routes(json_data):
        for route in json_data["data"]:
                print(route["id"],end=" | ")
                print(route["attributes"]["direction_destinations"])


def retrieve_routes_patterns(route_id):
        custom_url = "route_patterns"
        request_url = MBTA_API_URL + custom_url
        request_filter = {}
        request_filter["api_key"] = API_KEY
        request_filter["route"] = route_id
        raw_data = requests.get(request_url, request_filter)
        if ( raw_data.status_code != 200 ):    
                return ["ERROR", str(raw_data.status_code)]
        
        return raw_data.json()


def retrieve_stops(route_id):
        output_data = []
        route_url = "stops"
        request_url = MBTA_API_URL + route_url
        request_filter = {}
        request_filter["api_key"] = API_KEY
        request_filter["route"] = route_id
        raw_data = requests.get(request_url, request_filter)
        if ( raw_data.status_code != 200 ):    
                return ["ERROR", str(raw_data.status_code)]
        
        return raw_data.json()



def main():
        print("==========ROUTES==========")
        # print(json.dumps(retrieve_routes(), indent=4))
        print_routes(retrieve_routes())
        print("\n==========ROUTE PATTERNS==========")
        print(json.dumps(retrieve_routes_patterns("Red"), indent=4))
        print("\n==========STOPS==========")
        print(json.dumps(retrieve_stops("Red"), indent=4))

main()