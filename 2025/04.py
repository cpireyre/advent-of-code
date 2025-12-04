from itertools import product

grid = open("./input/04-in.txt").read().splitlines()
R, C = len(grid), len(grid[0])

inside = lambda r,c: 0 <= r < R and 0 <= c < C
neighbors = lambda r,c: {(r+1,c),(r-1,c),(r,c+1),(r,c-1),
                          (r+1,c+1),(r-1,c-1),(r+1,c-1),(r-1,c+1)}
adjacent = lambda u: sum(inside(*v) and v in paper for v in neighbors(*u))
p1 = p2 = 0
paper = set()
for r,c in product(range(R),range(C)):
    if grid[r][c] == '@': paper.add((r,c))
while True:
    keep = set(u for u in paper if adjacent(u) >= 4)
    if not p2: p1 += len(paper) - len(keep)
    p2 += len(paper) - len(keep)
    if paper == keep: break
    paper = keep

print(p1, p2)
