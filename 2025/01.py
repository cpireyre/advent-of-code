example = open("./input/01-ex.txt").read().split()
input   = open("./input/01.txt")   .read().split()

def f(lines):
    p1 = p2 = 0
    dial = 50
    sign = {"L": -1, "R": 1}
    for turn in lines:
        inc = int(turn[1:])
        for _ in range(inc):
            dial += sign[turn[0]]
            dial %= 100
            p2 += dial == 0
        p1 += dial == 0
    return (p1, p2)

assert f(example)[0] == 3 # 3
assert f(example)[1] == 6 # 6
assert f(input)[0] == 1064 # 1064
assert f(input)[1] == 6122 # 6122
