from selenium import webdriver
import time
from selenium.webdriver.common.keys import Keys

driver = webdriver.Safari()
driver.get("http://www.reserveamerica.com")
time.sleep(1)
elem = driver.find_element_by_class_name("form-control")
elem.clear()
elem.send_keys("Cowans Gap State park")
time.sleep(1)
elem = driver.find_element_by_class_name("searchbar__button")
elem.click()
