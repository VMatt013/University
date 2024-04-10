def main(name):
    marks = {
        "James" : 54,
        "Bob" : 75,
        "Katy" : 87,
        "Liza" : 12
        }

    for student in marks:
        if student == name:
            print(marks[student])
            break
    else:
        print("No entry with that name found")

if __name__ == '__main__':
    print('Math test results: ')
    student_name = input("enter a student's name: ")
    main(student_name)
    