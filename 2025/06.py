from operator import mul,add
G = [xs.split() for xs in open("./input/06-in.txt").read().splitlines()]
R,C = len(G),len(G[0])
ops = [{"+": add, "*": mul}[s] for s in G.pop()]
p1 = 0
for c in reversed(range(C)):
    op = ops[c]
    acc = 0 if op == add else 1
    for r in range(0, R-1):
        acc = op(acc, int(G[r][c]))
    p1 += acc

G = open("./input/06-in.txt").read().splitlines()
G.pop()
R,C = len(G), len(G[0])
op = ops.pop(); nums = []; p2 = 0
for c in reversed(range(C)):
    digits = []
    for r in range(R):
        if G[r][c].isdigit(): digits.append(G[r][c])
    if digits: nums.append(int("".join(digits)))
    else:
        acc = 0 if op == add else 1
        for n in nums: acc = op(acc, n)
        p2 += acc
        nums = []; op = ops.pop()
acc = 0 if op == add else 1
for n in nums: acc = op(acc, n)
p2 += acc
print(p2)
