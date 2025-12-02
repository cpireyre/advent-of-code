# >>> rhs = ys // pow(10, log10(ys) // 2 + 1)
# >>> rhs
# 4848.0
# >>> ys
# 48484848
# >>> rhs + rhs * pow(10, log10(ys)//2 + 1)
# 48484848.0
# >>> rhs + rhs * pow(10, log10(ys)//2 + 1) == ys

from math import pow, log10
def valid(n):
    if not n: return False
    if int(log10(n)) % 2 == 0: return False
    rhs = n // pow(10, log10(n)//2 + 1)
    return rhs + rhs * pow(10, log10(n)//2 + 1) == n

input = open("./input/02-in.txt").read().split(",")
res = 0
for id_range in input:
    nums = id_range.split("-")
    l, r = int(nums[0]), int(nums[1])
    for n in range(l, r + 1):
        res += n * valid(n)
print(res)
