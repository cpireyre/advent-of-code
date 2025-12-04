G = open(0).read().split()
p1, p2, R, C = 0, 0, len(G), len(G[0])
roll = lambda r,c: G[r][c] == '@'
P = set((r,c) for r in range(R) for c in range(C) if roll(r,c))
N = lambda r,c:{(r+i,c+j) for i in (-1,0,1) for j in(-1,0,1) if i|j}
adj = lambda u: len([v for v in N(*u) & P if roll(*v)])
while True:
    K = set(u for u in P if adj(u) >= 4)
    p1 += len(P^K)*(not p2);p2+=len(P^K)
    if P == K: break
    P = K
print(p1, p2)
