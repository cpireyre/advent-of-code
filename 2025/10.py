import scipy
data = open("./input/10-in.txt").read().splitlines()
p2 = 0
for line in data:
    _, *buttons, b = line.split()
    c = [1] * len(buttons)
    b = eval(b[1:-1])
    A = [[0 for _ in range(len(c))] for _ in range(len(b))]
    for i, button in enumerate(buttons):
        button = eval(button.replace(")", ",)"))
        for x in button:
            A[x][i] = 1
    res = scipy.optimize.linprog(
            c,
            A_eq=A,
            b_eq=b,
            integrality=1
            )
    p2 += sum(res.x)
print(int(p2))
