# cf. r/adventofcode/comments/1phywvn/comment/nt2hps9/

from itertools import pairwise, combinations
data = open(0).read().splitlines()
vertices = list(map(eval, data))
edges = list(pairwise(vertices + [vertices[0]]))
p1 = p2 = 0
for (u,v),(x,y) in combinations(vertices, 2):
    area = (abs(x-u)+1) * (abs(y-v)+1)
    p1 = max(p1, area)
    if area > p2:
        for (p,q), (r,s) in edges:
            if not (min(u,x) >= max(p,r)   # right?
                 or max(u,x) <= min(p,r)   # left?
                 or max(v,y) <= min(q,s)   # above?
                 or min(v,y) >= max(q,s)): # below?
                break
        else: p2 = area

print(p1, p2)
