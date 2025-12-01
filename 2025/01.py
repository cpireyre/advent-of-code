example = open("./input/01-ex.txt").read().split("\n")
input   = open("./input/01.txt")   .read().split("\n")

def I(lines):
    dial, res = 50, 0
    sign = {"L": -1, "R": 1}
    for turn in lines:
        dial += sign[turn[0]] * int(turn[1:])
        dial %= 100
        res += dial == 0
    return res

def II(lines):
    dial, res = 50, 0
    sign = {"L": -1, "R": 1}
    for turn in lines:
        inc = int(turn[1:])
        for _ in range(inc):
            dial += sign[turn[0]]
            dial %= 100
            res += dial == 0
    return res

print(I(example)) # 3
print(II(example)) # 6
print(I(input)) # 1064
print(II(input)) # 6122
