arr = [2, 5, 6, 8, 13, 32, 42, 50, 53, 62, 66, 70, 80, 89, 91]
x = 70
c = 0
low = 0
high = len(arr) - 1
mid = 0

while low <= high:
    mid = (high + low) // 2
    if arr[mid] < x:
        low = mid + 1
    elif arr[mid] > x:
        high = mid - 1
    else:
        print(mid)
        break;
