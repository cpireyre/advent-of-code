data = open("input/12-in.txt").read().split("\n\n")
*shapes, regions = data
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
# print(S)
# configuration = [52, 68, 54, 52, 67, 61]
# acc = 0
# for tiles, factor in zip(S, configuration):
#     acc += tiles * factor
# print(acc, 50*46)
