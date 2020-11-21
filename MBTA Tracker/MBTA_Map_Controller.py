from MBTA_Map_Model import *
from MBTA_Map_View_2 import *
import time
from datetime import datetime


def go(refresh_rate):
    while True:
        now = datetime.now()
        current_time = now.strftime("%H:%M:%S")
        print("Update Occurred: " + current_time)
        update_vehicle_list(STANDARD_VEHICLE_FILTER)
        plot_vehicles(VEHICLES)
        save_train_data_to_csv(VEHICLES)
        time.sleep(refresh_rate)
