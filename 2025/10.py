import functools as f, itertools as it, operator as op
from scipy.optimize import linprog

data = open(0).read().splitlines()
p1 = p2 = 0

for goal, *buttons, b in map(str.split, data):

    b, c = eval(b[1:-1]), [1] * len(buttons)
    A = [[0] * len(c) for _ in range(len(b))]
    for i, button in enumerate(buttons):
        coefficients = map(int, button[1:-1].split(','))
        for x in coefficients: A[x][i] = 1
    res = linprog(c, A_eq=A, b_eq=b, integrality=1)
    p2 += sum(res.x)

    # Scary binary parsing
    B = lambda x,y: (x << 1) | y
    on = lambda c: c == '#'
    goal = f.reduce(B, map(on, goal[1:-1]))
    toggles = list(map(lambda xs: f.reduce(B, xs), zip(*A)))

    for i in range(1, len(buttons)):
        for switches in it.combinations(toggles, i):
            light = f.reduce(op.xor, switches)
            if light == goal: p1 += len(switches); break
        else: continue
        break

print(p1, int(p2))
