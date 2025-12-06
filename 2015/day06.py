from sys import argv
xs = open("./input/" + argv[0] + ".txt").read().split("\n")
for instruction in xs:
    if   instruction[0:8] == "turn off": print("off")
    elif instruction[0:6] == "toggle": print("toggle")
    elif instruction[0:7] == "turn on": print("on")
    else: print(instruction)
    print(instruction)
