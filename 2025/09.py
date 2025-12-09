data = open("./input/09-in.txt").read().splitlines()
S = [tuple(map(int, l.split(','))) for l in data]
print(S)
N, p1 = len(S), 0
for r in range(N):
    for c in range(r+1,N):
        H = abs(S[r][0] - S[c][0]) + 1
        W = abs(S[r][1] - S[c][1]) + 1
        if H*W > p1: p1 = H*W
print(p1)
