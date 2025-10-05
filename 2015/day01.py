def I(xs):
    res = 0
    for c in xs:
        res += c == '('
        res -= c == ')'
    return res

def II(xs):
    res = 0
    for i, c in enumerate(xs):
        res += c == '('
        res -= c == ')'
        if (res == -1): return i + 1

input = open("input/01.txt").read()
print(I(input))
print(II(input))
