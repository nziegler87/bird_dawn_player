# API Settings
API_KEY = "0e2ef1b620f74c8e99aa627d2f28316e"
BASE_FILTER = {"api_key": "0e2ef1b620f74c8e99aa627d2f28316e"}

# API URLs
ROUTES_URL = "https://api-v3.mbta.com/routes"
STOPS_URL = "https://api-v3.mbta.com/stops"
VEHICLES_URL = "https://api-v3.mbta.com/vehicles"
SHAPES_URL = "https://api-v3.mbta.com/shapes"
ROUTES_0_1_GEOJSON_URL = "https://gis.massdot.state.ma.us/arcgis/rest/services/Multimodal/GTFS_Parent/FeatureServer/2" \
                         "/query?outFields=*&f=geojson&where=%20(route_type%20%3D%20%270%27%20OR%20route_type%" \
                         "20%3D%20%271%27)%20"

# For this project, only looking at the location of light rail and subway cars
STANDARD_VEHICLE_FILTER = {"filter[route_type]": "0,1"}
STANDARD_STOPS_FILTER = {"filter[route_type]": "0,1"}
STANDARD_ROUTES_FILTER = {"filter[type]": "0,1"}

ROUTES_FILENAME = "MBTA_routes.csv"
STOPS_FILENAME = "MBTA_stops.csv"
CSV_HEADER = ["id", "name"]

CURRENT_STATUS_DICT = {"IN_TRANSIT_TO": "In transit to", "STOPPED_AT": "Stopped at", "INCOMING_AT": "Incoming at"}

# Map Settings
CENTER_LOCATION = [42.332293, -71.083188]
ZOOM_LEVEL = 12
MAP_TILES = "OpenStreetMap"

# Route Details
ROUTES_COMPLETE = {"Red": "#C83B2B", "Blue": "#163C9F", "Orange": "#DF9033", "Green-B": "#398245", "Green-C": "#398245",
                   "Green-D": "#398245", "Green-E": "#398245", "Mattapan": "#C83B2B"}
ROUTES = {"Green-B": "#398245", "Green-C": "#398245", "Green-D": "#398245", "Green-E": "#398245"}

# Map
map = "http://umap.openstreetmap.fr/en/map/anonymous-edit/506616:LPfbEb4fvfx819cvKcc1ZSfT-30"