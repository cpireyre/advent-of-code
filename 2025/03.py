from itertools import combinations
from functools import reduce
input = open("./input/03-ex.txt").read().splitlines()
S = lambda xs: reduce(lambda a,b: 10*a+b, xs, 0)
p1 = p2 = 0
for line in input:
    L = combinations(map(int, line), 2)
    M = combinations(map(int, line), 12)
    t = max(L, key=S)
    u = max(M, key=S)
    p1 += S(t)
    p2 += S(u)
print(p1, p2)
