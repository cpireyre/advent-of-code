G = open("./input/07-in.txt").read().split()
start = G[0].index('S')
beams = {start}
p1 = 0
for line in G[::2]:
    splits = set()
    for beam in beams:
        if line[beam] == '^':
            splits.add(beam - 1)
            splits.add(beam + 1)
            p1 += 1
        else:
            splits.add(beam)
        beams = splits
print(p1)

from functools import cache
R = len(G)
@cache
def timelines(r,c):
    while G[r][c] != '^':
        r += 2
        if r >= R: return 1
    return timelines(r,c-1) + timelines(r,c+1)
print(timelines(0,start))
