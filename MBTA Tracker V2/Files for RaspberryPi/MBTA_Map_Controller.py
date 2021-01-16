from MBTA_Map_Model import *
from MBTA_Map_View import *
import time
from datetime import datetime
from selenium import webdriver






def go(refresh_rate):
    driver = webdriver.Chrome()
    driver.get("file:///media/pi/USB%20DISK/MBTA%20Tracker%20V2/mbta_map.html")

    while True:
        now = datetime.now()
        current_time = now.strftime("%H:%M:%S")
        print("Update Occurred: " + current_time)
        update_vehicle_list(STANDARD_VEHICLE_FILTER)
        plot_vehicles(VEHICLES)

        driver.refresh()
        save_train_data_to_csv(VEHICLES)
        time.sleep(refresh_rate)
