from functools import cache
@cache
def f(xs, n):
    if not n:        return 0
    if len(xs) == n: return int(xs)
    x = int(xs[0])
    a = 10**(n-1) * x + f(xs[1:], n-1)
    return max(a, f(xs[1:], n))
p1 = p2 = 0
for xs in open(0).read().splitlines():
    p1 += f(xs, 2); p2 += f(xs, 12)
print(p1,p2)
