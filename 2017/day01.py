from sys import argv
input = open("./input/" + argv[0] + ".txt").read()

def I(input):
    res = 0
    for i in range(len(input)):
        n = int(input[i])
        m = int(input[i + 1]) if i + 1 < len(input) else int(input[0])
        if n == m: res += m
    return res

def II(input):
    res = 0
    L = len(input)
    for i in range(L):
        n, m = int(input[i]), int(input[(i + L // 2) % L])
        if n == m: res += n
    return res

xs = ["1122", "1111", "1234", "91212129", input]
for x in xs: print(I(x))
ys = ["1212", "1221", "123425", "123123", "12131415", input]
for y in ys: print(II(y))
