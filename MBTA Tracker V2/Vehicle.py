class Vehicle:
    """
    Class that represents a vehicle object.
    """

    def __init__(self, bearing, current_status, current_stop_sequence, direction_id, label, latitude, longitude,
                 occupancy_status, speed, updated_at, route, stop_id):
        """
        Constructs a vehicle object using the following parameters.

        :param bearing: vehicle bearing, an int
        :param current_status: vehicle status, a string
        :param current_stop_sequence: vehicle stop sequence, an int
        :param direction_id: direction id, either 0 or 1
        :param label: vehicle label, a string
        :param latitude: latitude of the vehicle, a double
        :param longitude: longitude of the vehicle, a double
        :param occupancy_status: occupancy status of the vehicle, a string
        :param speed: speed of the vehicle, a double
        :param updated_at: last updated
        :param route: route associated with the vehicle, a string
        :param stop_id: stop id associated with the vehicle, an int
        """
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
        return "ID: {} | Route: {} | Direction: {}\nStatus: {} | Stop: {}\nLatitude: {} | Longitude: {} | " \
               "Bearing: {} | Speed: {}\n".format(self.label, self.route, self.direction_id, self.current_status,
                                                  self.stop_id, self.latitude, self.longitude, self.bearing, self.speed)
