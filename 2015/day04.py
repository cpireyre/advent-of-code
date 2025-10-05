import hashlib
import itertools
import sys
input = open("input/" + sys.argv[0] + ".txt").read()

def I(input):
    for k in itertools.count(0):
        S = input + str(k)
        H = hashlib.md5(bytes(S, "ascii")).hexdigest()
        if H[0:5] == "00000": return k

def II(input):
    for k in itertools.count(0):
        S = input + str(k)
        H = hashlib.md5(bytes(S, "ascii")).hexdigest()
        if H[0:6] == "000000": return k

print(I(input))
print(II(input))
