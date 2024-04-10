def Area(name):
    name = name.lower()
    
    if name == 'rectangle':
        L = int(input("Enter rectangle's length: "))
        W = int(input("Enter rectangle's width: "))
        
        area = L * W
        print('The area of the rectangle is: {}'.format(area))

    elif name == 'square':
        W = int(input("Enter square's width: "))
        area = W * W
        print('The area of the square is: {}'.format(area))

    elif name == 'triangle':
        H = int(input("Enter triangle's height: "))
        W = int(input("Enter triangle's width: "))
        area = W*H * 0.5 # /2 = 0.5
        print('The area of the triangle is: {}'.format(area))

    elif name == 'circle':
        R = int(input("Enter circle's radius: "))
        Pi = 3.14
        area = R*R*Pi
        print('The area of the circle is: {}'.format(area))
    else:
        print('Sorry, this shape is not avlivable')



if __name__ == '__main__':
    print('Calculate Shape Area')
    Area(input('Enter the name of shape whose area you want to find: '))