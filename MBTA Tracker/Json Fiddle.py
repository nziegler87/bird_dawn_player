import requests
import json

astronauts = requests.get("http://api.open-notify.org/astros.json")

print(astronauts.status_code)

print(astronauts.json())


def jprint(object):
    text = json.dumps(object, sort_keys=True, indent=4)
    print(text)


jprint(astronauts.json())

parameters = {
    "lat": 40.71,
    "lon": -74
}

passover = requests.get("http://api.open-notify.org/iss-pass.json", parameters)

jprint(passover.json())

