*shapes, regions = open(0).read().split("\n\n")
S = []
for shape in shapes:
    id, *bits = shape.splitlines()
    S.append(sum(bit.count('#') for bit in bits))

regions = regions.replace('x', '*').splitlines()
p1 = 0
for region in regions:
    dim, *quantities = region.split()
    sp = eval(dim[:-1])
    p1 += sum(x*int(y) for x,y in zip(S,quantities)) <= sp
print(p1)
