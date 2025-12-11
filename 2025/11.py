from collections import defaultdict, deque
data = open("./input/11-in.txt").read().splitlines()
G = defaultdict(set)
for line in data:
    source, *sinks = line.split()
    source = source[:-1]
    G[source].update(sinks)
Q = deque(G["you"])
p1 = 0
while Q:
    u = Q.popleft()
    for v in G[u]:
        if v != "out": Q.append(v)
        else: p1 += 1
print(p1)
