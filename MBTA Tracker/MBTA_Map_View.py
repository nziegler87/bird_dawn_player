import gmplot
import csv

COLORS = {"R": "red", "B": "blue", "O": "orange", "G": "green", "M": "red"}
ROUTE_DATA = ["blue_line.csv", "green_line_b.csv", "green_line_c.csv", "green_line_d.csv", "green_line_e.csv",
              "green_line_main.csv", "orange_line.csv", "red_line_ashmont.csv", "red_line_braintree.csv",
              "red_line_mattapan.csv"]
DESTINATION_LABELS = {"Ashmont/Braintree": "AB", "Alewife": "AL", "Forest Hills": "FH", "Oak Grove": "OG",
                      "Wonderland": "WL", "Bowdoin": "BO", "North Station": "NS", "Lechmere": "LE", "Park Street": "PS",
                      "Heath Street": "HS", "Cleavland Circle": "CC", "Riverside": "RD", "Cleveland Circle": "CC",
                      "Boston College": "BC", "Government Center": "GC", "Ashmont": "AT", "Braintree": "BT"}


def plot_vehicles(vehicles):
    gmap = gmplot.GoogleMapPlotter(42.332293, -71.083188, 12)
    plot_routes(gmap)
    for vehicle in vehicles:
        gmap.marker(lat=vehicle.latitude, lng=vehicle.longitude, color=COLORS[vehicle.route[0]],
                    title="Vehicle: " + vehicle.label, label=DESTINATION_LABELS[vehicle.direction_id],
                    info_window="<p><b>Status:</b> " + vehicle.current_status + "<br><b>Stop:</b> " + vehicle.stop_id +
                                "<br><b>Destination:</b> " + vehicle.direction_id + "</p>")
    gmap.draw('test_map.html')


def save_train_data_to_csv(vehicles):
    with open("Train Locations.csv", "w") as csv_file:
        csv_writer = csv.writer(csv_file)
        csv_writer.writerow(["Latitude", "Longitude", "Label", "Status", "Stop", "Route", "Direction"])
        for vehicle in vehicles:
            csv_writer.writerow([vehicle.latitude, vehicle.longitude, vehicle.label, vehicle.current_status,
                                 vehicle.stop_id, vehicle.route, vehicle.direction_id])


def plot_routes(gmap_object):
    for route in ROUTE_DATA:
        lat = []
        long = []
        letter = route[0].upper()
        color = COLORS[letter]
        with open(route, "r") as in_file:
            csv_reader = csv.reader(in_file)
            for row in csv_reader:
                lat.append(float(row[0]))
                long.append(float(row[1]))
        gmap_object.plot(lat, long, color=color, edge_width=3 )
