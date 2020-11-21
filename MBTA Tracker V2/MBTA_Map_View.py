import csv
import folium

COLORS = {"R": "red", "B": "blue", "O": "orange", "G": "green", "M": "red"}
COLORS_HEX = {"R": "#C83B2B", "B": "#163C9F", "O": "#DF9033", "G": "#398245", "M": "#C83B2B"}

ROUTE_DATA = ["blue_line.csv", "green_line_b.csv", "green_line_c.csv", "green_line_d.csv", "green_line_e.csv",
              "green_line_main.csv", "orange_line.csv", "red_line_ashmont.csv", "red_line_braintree.csv",
              "red_line_mattapan.csv"]

MAP_TILES = "https://api.mapbox.com/styles/v1/nziegler87/ckfvnoo0d11gv1apc9szyzpve/tiles/256/{z}/{x}/{y}@2x?" \
            "access_token=pk.eyJ1IjoibnppZWdsZXI4NyIsImEiOiJja2Z2bmh2dDEwdmFuMnpwOXp4Nmozb3pjIn0.meDquxkUvXCwGz8xA29Xfw"

MAP_FILE_NAME = "mbta_map.html"


def plot_vehicles(vehicles):
    """
    Plots vehicles on map using folium / jleaf and saves the map to an html file.

    :param vehicles: a list of vehicle objects
    :return: nothing
    """
    # create map using custom map tiles
    gmap = folium.Map(location=(42.332293, -71.083188), zoom_start=12, tiles=MAP_TILES, attr="XXX Mapbox Attribution")

    # plot vehicles on map
    for vehicle in vehicles:
        popup = "<p><b>Status:</b> " + vehicle.current_status + "<br><b>Stop:</b> " + vehicle.stop_id + \
                "<br><b>Destination:</b> " + vehicle.direction_id + "</p>"
        color = COLORS[vehicle.route[0].upper()]
        icon_path = "./map_icons/" + color + "/" + color + "_" + str(vehicle.bearing) + ".png"
        # icon_path = "./map_icons/green/base_copy.eps"
        icon = folium.features.CustomIcon(icon_path, icon_size=(16, 16))
        folium.Marker((vehicle.latitude, vehicle.longitude), icon=icon, popup=popup).add_to(gmap)

    # save map
    gmap.save(MAP_FILE_NAME)


def save_train_data_to_csv(vehicles):
    """
    Saves location of train vehicles as a csv file.

    :param vehicles: a list of vehicle objects
    :return: nothing
    """
    try:
        with open("Train Locations.csv", "w") as csv_file:
            csv_writer = csv.writer(csv_file)
            csv_writer.writerow(["Latitude", "Longitude", "Label", "Status", "Stop", "Route", "Direction"])
            for vehicle in vehicles:
                csv_writer.writerow([vehicle.latitude, vehicle.longitude, vehicle.label, vehicle.current_status,
                                     vehicle.stop_id, vehicle.route, vehicle.direction_id])
    except IOError:
        print("Unable to save csv file.")


def plot_routes(map_object):
    """
    Plot route/polyline data on map.

    :param map_object: a folium map object
    :return: nothing
    """
    for route in ROUTE_DATA:
        location = []
        letter = route[0].upper()
        color = COLORS_HEX[letter]
        with open(route, "r") as in_file:
            csv_reader = csv.reader(in_file)
            for row in csv_reader:
                location.append((float(row[0]), float(row[1])))
        folium.PolyLine(location, color=color, edge_width=3).add_to(map_object)

