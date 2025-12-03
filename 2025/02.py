from math import pow, log10
def valid(n):
    if not n: return False
    if int(log10(n)) % 2 == 0: return False
    rhs = n // pow(10, log10(n)//2 + 1)
    return rhs + rhs * pow(10, log10(n)//2 + 1) == n

def vvalid(n):
    xs = str(n)
    for window_len in range(1, len(xs)):
        res = []
        for i in range(1, len(xs)):
            if i % window_len == 0:
                res.append(xs[i - window_len: i])
        total = sum(len(s) for s in res)
        if len(xs) > total: res.append(xs[total:])
        if all(x == res[0] for x in res): return True
    return False

input = open("./input/02-in.txt").read().split(",")
p1 = p2 = 0
for id_range in input:
    nums = id_range.split("-")
    l, r = int(nums[0]), int(nums[1])
    for n in range(l, r + 1):
        p1 += n * valid(n)
        p2 += n * vvalid(n)
print(p1, p2)
