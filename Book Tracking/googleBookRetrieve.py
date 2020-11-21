import requests
import json

book_data = requests.get("https://www.googleapis.com/books/v1/volumes?q=isbn:9780226750217")

print(json.dumps(book_data.json(), indent=4))

print("Title: " + book_data.json()["items"][0]["volumeInfo"]['title'])
# print("Subtitle: " + book_data.json()["items"][0]["volumeInfo"]['subtitle'])
print("Authors: ")
for i in book_data.json()["items"][0]["volumeInfo"]['authors']:
    print(i)
print("Published Data: " + book_data.json()["items"][0]["volumeInfo"]['publishedDate'])
print(len(book_data.json()["items"][0]["volumeInfo"]['industryIdentifiers']))
print("ISBN_10: " + book_data.json()["items"][0]["volumeInfo"]['industryIdentifiers'][0]["identifier"])
print("ISBN_13: " + book_data.json()["items"][0]["volumeInfo"]['industryIdentifiers'][1]["identifier"])



