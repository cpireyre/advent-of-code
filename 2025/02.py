from math import pow, log10
ilog10   = lambda n:   int(log10(n))
ipow     = lambda n,i: int(pow(n,i))
n_digits = lambda n:   ilog10(n) + 1
p1 = p2 = 0
for ids in open(0).read().split(","):
    l, r = ids.split("-")
    for n in range(int(l), int(r)+1):
        m = pow(10, log10(n)//2 + 1)
        p1 += ((n//m)*(m+1)==n)*n*(ilog10(n)%2)
        for i in reversed(range(1, n_digits(n))):
            q = n
            while q:
                m = q % ipow(10, i)
                if (m and n_digits(m) != i) or m != n % ipow(10, i):
                    break
                q = q // ipow(10, i)
            if not q: p2 += n; break
print(p1, p2)
