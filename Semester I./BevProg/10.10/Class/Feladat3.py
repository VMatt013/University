def fibonacci(n):
    List = [0,1]
    a = 0
    b = 1
         
    if 1 < n:
        for i in range(1, n):
            a ,b = b, a+b
            List.append(b)
    return List[0:n+1]
 
print(fibonacci(int(input)))