def I(input):
    res = 0
    for line in input.split("\n"):
        l, w, h = map(int, line.split("x"))
        smallest_area = l * w * h // max(l, w, h)
        surface = 2*l*w + 2*w*h + 2*h*l
        res += surface + smallest_area
    return res

def II(input):
    res = 0
    for line in input.split("\n"):
        l, w, h = map(int, line.split("x"))
        perimeter = 2*l + 2*w + 2*h - 2*(max(l, w, h))
        volume = l*w*h
        res += perimeter + volume
    return res

input = open("input/day02.txt").read()
print(I(input))
print(II(input))
