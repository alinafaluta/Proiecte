class LabUi():

    def __init__(self,controller):
        self.__controller = controller

    def start(self):
        print("1.Students")
        print("2.Search student after id")
        print("3.Add lab")
        print("4.Labs from a student")
        print("5.Students afer lab")
        print("x-Exit")

    def comand_1(self):
        all = self.__controller.all_students()
        for i in all:
            print(i)

    def comand_2(self):
        id = int(input("Give the student id: "))
        stud = self.__controller.getStudentById(id)
        if stud == False:
            print("student doesn't exist")
        else:
            print(stud)

    def comand_3(self):
        id = int(input("Give the student id: "))
        lab = int(input("Give the lab number: "))
        pb = input("Give the problem name: ")
        try:
            laboratory = self.__controller.addLab(id,lab,pb)
            print(laboratory,"was added")
        except ValueError as er:
            print(er)

    def comand_4(self):
        id = int(input("Give the student id: "))
        try:
            laboratory = self.__controller.getLabs(id)
            for i in laboratory:
                print(i)
        except ValueError as er:
            print(er)

    def comand_5(self):
        lab = int(input("Give the lab number: "))
        labs = self.__controller.getAllbyLab(lab)
        for i in labs:
            stud = self.__controller.getStudentById(i.getId())
            print(stud, "Laboratory: " + str(i.getLab()) + " Problem: " + str(i.getProblem()))

    def run(self):
        self.start()
        cmd = input("Give the command: ")
        while cmd != 'x':
            if cmd == '1':
                self.comand_1()
            elif cmd == '2':
                self.comand_2()
            elif cmd == '3':
                self.comand_3()
            elif cmd == '4':
                self.comand_4()
            elif cmd == '5':
                self.comand_5()
            cmd = input("Give the command: ")
        if cmd == 'x':
            print('Bye')
