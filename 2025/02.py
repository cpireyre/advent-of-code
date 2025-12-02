from math import pow, log10

shift = lambda n,r: n // int(pow(10, r))
maskoff = lambda n,r: n % int(pow(10, r))
n_digits = lambda n: int(log10(n)) + 1
def chunked(n, i):
    res = []
    while n:
        m = maskoff(n, i)
        if (m and n_digits(m) != i):
            return None
        res.append(m)
        n = shift(n, i)
    return res

input = open("./input/02-ex.txt").read().split(",")
p1 = p2 = 0
for id_range in input:
    nums = id_range.split("-")
    l, r = int(nums[0]), int(nums[1])
    for n in range(l, r+1):
        for i in reversed(range(1, n_digits(n))):
            xs = chunked(n, i)
            if not xs: continue
            if all(x == xs[0] for x in xs):
                p2 += n
                break
print(p1, p2)
