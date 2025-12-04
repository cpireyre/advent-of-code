from utils import extract, mooren
G = open(0).read().split()
roll = lambda r,c: G[r][c] == '@'
p1, p2, P = 0, 0, extract(G, roll)
adj = lambda u: sum(roll(*v) for v in mooren(*u)&P)
while True:
    K = set(u for u in P if adj(u) >= 4)
    if P == K: break
    p1 += len(P^K)*(not p2);p2+=len(P^K)
    P = K
print(p1, p2)
