from itertools import combinations
from math import dist
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

dsu, J = DSU(), []
for u in map(eval, open(0).read().splitlines()):
    dsu.find(u); J.append(u)
pairs = sorted(combinations(J, 2), key=lambda p: dist(*p))
limit = 1000
components = len(J)
for u,v in pairs:
    components -= dsu.unite(u,v)
    if (limit := limit - 1) == 0:
        circuits = sorted(n for n in dsu.size.values())
        print(circuits[-1] * circuits[-2] * circuits[-3])
    if components == 1: print(u[0]*v[0]); break
