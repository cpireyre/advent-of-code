from itertools import combinations
import heapq
data = open("./input/09-in.txt").read().splitlines()
S = set()
R = C = 0
for l in data:
    c,r = map(int, l.split(','))
    S.add((r,c))
    R = max(R, r); C = max(C, c)
E = []
for (r,c),(x,y) in combinations(S, 2):
    H, W = abs(r - x) + 1, abs(c - y) + 1
    heapq.heappush(E, (-H*W, (r,c),(x,y)))
print(-E[0][0])
