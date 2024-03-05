def euk(a, b):
    x0 = 1
    x1 = 0
    y0 = 0
    y1 = 1
    s = 1

    while b != 0:
        rem = a % b
        q = a // b
        a = b
        b = rem
        x = x1
        y = y1
        x1 = q * x1 + x0
        y1 = q * y1 + y0
        x0 = x
        y0 = y
        s = -1 * s

    x = s * x0
    y = -1 * s * y0
    d, x, y = a, x, y
    print(f"{d} {x} {y}")


euk(280, 3)
