from collections import defaultdict
from functools import cache
data = open("./input/11-in.txt").read().splitlines()
G = defaultdict(set)
for source, *sinks in map(str.split, data):
    G[source[:-1]].update(sinks)

@cache
def paths(s,e):
    if s == e: return 1
    return sum(paths(v,e) for v in G[s])

print(paths("you", "out"))
print(paths("svr", "fft")
    * paths("fft", "dac")
    * paths("dac", "out"))
