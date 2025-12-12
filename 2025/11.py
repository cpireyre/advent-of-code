from collections import defaultdict
from functools import cache
data = open(0).read().splitlines()
G = defaultdict(set)
for source, *sinks in map(str.split, data):
    G[source[:-1]] = sinks

@cache
def paths(s,e):
    if s == e: return 1
    return sum(paths(v,e) for v in G[s])

# paths("dac", "fft") was 0 in my input
print(paths("you", "out"),
      paths("svr", "fft")
    * paths("fft", "dac")
    * paths("dac", "out"))
