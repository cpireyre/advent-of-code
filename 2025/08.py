import pprint
from itertools import combinations
class DSU:
    def __init__(self):
        self.parent = {}
        self.size = {}
    def find(self,u):
        if u not in self.parent:
            self.parent[u] = u
            self.size[u] = 1
            return u
        if u != self.parent[u]:
            self.parent[u] = self.find(self.parent[u])
        return self.parent[u]
    def unite(self,u,v):
        u,v = self.find(u),self.find(v)
        if u == v: return False
        if self.size[u] < self.size[v]: u,v = v,u
        self.size[u] += self.size[v]
        self.parent[v] = u
        return True

data = open("./input/08-in.txt").read().splitlines()
dsu = DSU()
J = []
for line in data:
    u = tuple(int(c) for c in line.split(','))
    dsu.find(u); J.append(u)
pairs = list(combinations(J, 2))
dist = lambda u,v: sum((u[i]-v[i])**2 for i in range(3))
pairs.sort(key=lambda p: dist(*p))
limit = 1000
components = len(J)
for u,v in pairs:
    components -= dsu.unite(u,v)
    if (limit := limit - 1) == 0:
        circuits = [n for n in dsu.size.values()]
        circuits.sort(reverse=True)
        print(circuits[0] * circuits[1] * circuits[2])
    if components == 1: print(u[0]*v[0]); break
