from collections import defaultdict, deque
from functools import cache
data = open("./input/11-ex.txt").read().splitlines()
G = defaultdict(set)
for line in data:
    source, *sinks = line.split()
    source = source[:-1]
    G[source].update(sinks)

@cache
def paths2(source, sink):
    if source == sink: return 1
    return sum(paths2(v, sink) for v in G[source])

def paths(sources, sink):
    Q = deque(sources)
    res = 0
    while Q:
        u = Q.popleft()
        for v in G[u]:
            if v != sink: Q.append(v)
            else: res += 1
    return res

# print(paths(G["you"], "out"))
print(paths2("you", "out"))
print(paths2("svr", "fft"))
print(paths2("fft", "dac"))
print(paths2("dac", "out"))
