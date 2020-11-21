import polyline
from Settings import *
import folium
import requests

MAP_FILENAME = "take_two.html"
ALL_LINES = "all_lines.html"


def plot_geojson_lines():
    """
    Retrieve's GeoJSON data for MBTA's subway and light rail routes and plots them on a map using folium. Map is saved
    using the global variable at the top.

    :return: nothing
    """
    # use routes GeoJSON API url to retrieve routes GeoJSON data for rail and light rail routes
    raw_data = requests.get(ROUTES_0_1_GEOJSON_URL, BASE_FILTER)

    t_map = create_map()

    # iterate through each line of data in .json file
    for i in range(len(raw_data.json()["features"])):

        # get nested list of route points and switch long/lat of geoJSON to lat/long
        route_points = (raw_data.json()["features"][i]["geometry"]["coordinates"])
        tuple_points = switch_lat_long(route_points)

        color = "#" + raw_data.json()["features"][i]["properties"]["route_color"]

        folium.PolyLine(tuple_points, color=color, edge_width=3).add_to(t_map)

    t_map.save(MAP_FILENAME)


def switch_lat_long(route_points):
    """
    Takes in a nested list of coordinates [[long, lat], [long, lat]] and switches them to [[lat, long], [lat, long]].

    :param route_points: a nested list of coordinates [[long, lat], [long, lat]].
    :return: nested list of coordinates as [[lat, long], [lat, long]].
    """
    lat_long = []
    for i in route_points:
        lat_long.append((i[1], i[0]))
    return lat_long


def plot_each_line():
    """
    Helper function that was used to plot polyline data for the MBTA's rail and light rail routes. The polyline
    for each route is saved as a separate .html file.

    :return: nothing
    """

    '''
    because a route filter must be passed to the shapes api, this function works by iterating through a dictionary
    # of routes
    '''
    for route in ROUTES_COMPLETE.keys():

        # add "route" to parameter dictionary and get raw shape data for the route
        BASE_FILTER["route"] = route
        raw_data = requests.get(SHAPES_URL, BASE_FILTER)

        # iterate through each polyline associated with the route
        for i in range(len(raw_data.json()["data"])):

            # parse encoded polyline data and then use the polyline to decode to nested list of coordinates
            encoded_polyline = (raw_data.json()["data"][i]["attributes"]["polyline"])
            route_points = polyline.decode(encoded_polyline)

            # get polyline ID and color
            id = (raw_data.json()["data"][i]["id"])
            color = ROUTES_COMPLETE[route]

            # create map and plot line
            t_map = create_map()
            folium.PolyLine(route_points, color=color, edge_width=3).add_to(t_map)

            # save map
            file_name = route + "-" + id + ".html"
            t_map.save(file_name)


def plot_all_lines():
    """
    Plots all polyline data for MBTA's rail and light rail lines on a map
    :return:
    """
    t_map = create_map()

    '''
    because a route filter must be passed to the shapes api, this function works by iterating through a dictionary
    # of routes
    '''
    for route in ROUTES_COMPLETE.keys():

        # add "route" to parameter dictionary and get raw shape data for the route
        BASE_FILTER["route"] = route
        raw_data = requests.get(SHAPES_URL, BASE_FILTER)

        # iterate through each polyline associated with the route
        for i in range(len(raw_data.json()["data"])):

            # parse encoded polyline data and then use the polyline to decode to nested list of coordinates
            encoded_polyline = (raw_data.json()["data"][i]["attributes"]["polyline"])
            route_points = polyline.decode(encoded_polyline)

            color = ROUTES_COMPLETE[route]
            folium.PolyLine(route_points, color=color, edge_width=3).add_to(t_map)

    t_map.save(ALL_LINES)


def create_map():
    """
    Creates base map with default settings.

    :return: nothing
    """
    # creates map with default settings
    return folium.Map(location=CENTER_LOCATION, zoom_start=ZOOM_LEVEL, tiles=MAP_TILES)
