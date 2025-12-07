from utils import extract
G = open("./input/07-ex.txt").read().split()
splitters = extract(G, lambda u: u == '^')
print(splitters)
