from utils import extract, mooren
p1, p2, G = 0, 0, open(0).read().split()
P = extract(G, lambda r,c: G[r][c]=='@')
adj = lambda u: sum(G[r][c]=='@'
            for r,c in mooren(*u) & P)
while True:
    K = set(u for u in P if adj(u) >= 4)
    if P == K: break
    p1 += len(P-K)*(not p2);p2+=len(P-K)
    P = K
print(p1, p2)
