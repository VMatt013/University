list = [54, 76, 23, 45, 21, 5, 67, 22, 12, 64, 26, 59, 82, 99]

n = len(list)
print(list)
for k in range(0,n):
    for i in range(1,n-k):
        if list[i-1] > list[i]:
            list[i], list[i-1] = list[i-1], list[i]
print(list)
