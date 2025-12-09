from itertools import combinations
from functools import cache
import heapq
data = open("./input/09-ex.txt").read().splitlines()
S = set()
R = C = 0
for l in data:
    c,r = map(int, l.split(','))
    S.add((r,c))
    R = max(R, r); C = max(C, c)
E, X, Y = [], [], []
for (r,c),(x,y) in combinations(S, 2):
    H, W = abs(r - x) + 1, abs(c - y) + 1
    heapq.heappush(E, (-H*W, (r,c),(x,y)))
    X.append((r,x)); Y.append((c,y))
print(-E[0][0])
print(X);print(Y)

L = len(X)
assert L == len(Y)
@cache
def inside(px,py):
    crossings = 0
    for i in range(L):
        (x1,x2),(y1,y2) = X[i],Y[i]
        x1 -= px; x2 -= px; y1 -= py; y2 -= py
        if y1 * y2 < 0:
            if x1 >= 0 and x2 >= 0: crossings += 1
            else:
                ???
    return crossings % 2 == 1
