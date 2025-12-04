from itertools import product
grid = list(map(list, open("./input/04-in.txt").read().split()))
R, C = len(grid), len(grid[0])
inside = lambda r,c: 0 <= r < R and 0 <= c < C
paper = lambda r,c: grid[r][c] == '@'
neighbors = lambda r,c: [(r+1,c),(r-1,c),(r,c+1),(r,c-1),
                          (r+1,c+1),(r-1,c-1),(r+1,c-1),(r-1,c+1)]
p1 = p2 = 0
prev = 0
while True:
    to_remove = set()
    for u in product(range(R),range(C)):
        if paper(*u):
            remove = sum(inside(*v) and paper(*v) for v in neighbors(*u)) < 4
            if remove: to_remove.add(u)
    for r,c in to_remove: grid[r][c] = '.'
    if not p2: p1 += len(to_remove)
    p2 += len(to_remove)
    if prev == len(to_remove): break
print(p1, p2)
