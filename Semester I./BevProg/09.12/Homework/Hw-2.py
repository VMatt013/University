def main():
    num = float(input("Adjon meg egy számot és egy mértékegységet (cm/inch):\n"))
    unit = input()

    if unit == "inch":
        print(format(num * 0.393700787,".2f"), "centimeters")
    elif unit == "cm":
        print(format(num * 2.54,".2f"), "inches")
    else:
        print("Not correct unit!")

if __name__ == "__main__":
    main()