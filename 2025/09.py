data = open("./input/09-ex.txt").read().splitlines()
S = set()
R = C = 0
for l in data:
    c,r = map(int, l.split(','))
    S.add((r,c))
    R = max(R, r); C = max(C, c)
R += 1; C += 1
G = [['.' for _ in range(C)] for _ in range(R)]
for r,c in S: G[r][c] = '#'
for r in range(R):
    for c in range(C):
        print(G[r][c], end=' ')
    print('')
