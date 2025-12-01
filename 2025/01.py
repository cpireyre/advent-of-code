p1 = p2 = 0
dial = 50
sign = {"L": -1, "R": 1}
for turn in open(0).read().split():
    inc = int(turn[1:])
    for _ in range(inc):
        dial += sign[turn[0]]
        dial %= 100
        p2 += dial == 0
    p1 += dial == 0
print(p1, p2)
