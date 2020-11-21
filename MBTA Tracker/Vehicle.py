class Vehicle:

    def __init__(self, bearing, current_status, current_stop_sequence, direction_id, label, latitude, longitude,
                 occupancy_status, speed, updated_at, route, stop_id):
        self.bearing = bearing
        self.current_status = current_status
        self.current_stop_sequence = current_stop_sequence
        self.direction_id = direction_id
        self.label = label
        self.latitude = latitude
        self.longitude = longitude
        self.occupancy_status = occupancy_status
        self.speed = speed
        self.updated_at = updated_at
        self.route = route
        self.stop_id = stop_id

    def __str__(self):
        return "ID: {} | Route: {} | Direction: {}\nStatus: {} | Stop: {}\nLatitude: {} | Longitude: {} | Bearing: {}\n" \
               "Speed: {}\n".format(self.label, self.route, self.direction_id, self.current_status, self.stop_id,
                                    self.latitude, self.longitude, self.bearing, self.speed)
