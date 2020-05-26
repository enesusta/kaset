from bs4 import BeautifulSoup
import requests
import sys
import argparse

parser = argparse.ArgumentParser()
parser.add_argument("echo")
args = parser.parse_args()


def getPlaylistLinks(url):
    sourceCode = requests.get(url).text
    soup = BeautifulSoup(sourceCode, 'html.parser')
    domain = 'https://www.youtube.com'
    for link in soup.find_all("a", {"dir": "ltr"}):
        href = link.get('href')
        if href.startswith('/watch?'):
            print(domain + href)


getPlaylistLinks(args.echo)
