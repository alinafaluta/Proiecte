from Domain.Student import Validator,Student

class FileStudRepo():

    def __init__(self,nameFile):
        self.__file=nameFile
        self.loadFromFile()

    def loadFromFile(self):
        try:
            f = open(self.__file,'r')
        except IOError:
            return
        line = f.readline().strip()
        rez = []
        while line !="":
            list = line.split(",")
            student = Student(list[0],list[1])
            rez.append(student)
            line = f.readline().strip()
        f.close()
        return rez

    def add(self,student):
        validator = Validator()
        allstudents = self.loadFromFile()
        if validator.validate(student)==True:
            for i in allstudents:
                if i == student:
                    raise ValueError("Student already exists")
                elif i.getId() == student.getId():
                    raise ValueError("dublicated id")
            allstudents.append(student)
            self.storeToFile(allstudents)
        else :
            return validator.validate(student)

    def storeToFile(self,list):
        with open(self.__file, "w") as f:
            for st in list:
                strf = str(st.getId()) + "," + st.getName()
                strf = strf + "\n"
                f.write(strf)

    def findByID(self,id):
        allstud = self.loadFromFile()
        list = []
        for i in allstud:
            if int(i.getId())== id:
                list.append(i)
        if list == []:
            return False
        else:
            return list[0]

    def __sizeof__(self):
        return len(self.loadFromFile())

    def getAll(self):
        return self.loadFromFile()

def testRepo():
    repo = FileStudRepo('stud_test.txt')
    assert  repo.__sizeof__()== 4
    try :
        repo.findByID(3)
    except ValueError:
        assert False

    try :
        repo.findByID(5)
    except ValueError:
        assert True

testRepo()

