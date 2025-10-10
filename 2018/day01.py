from sys import argv
input = open("input/" + argv[0] + ".txt").read().split("\n")

def I(lines):
    freq = 0
    for L in lines:
        if L[0] == "-": freq -= int(L[1:])
        if L[0] == "+": freq += int(L[1:])
    return freq

print(I(input))

def II(lines):
    freq = 0
    seen = set()
    while True:
        for L in lines:
            if L[0] == "-": freq -= int(L[1:])
            if L[0] == "+": freq += int(L[1:])
            if freq in seen: return freq
            seen.add(freq)

print(II(input))
