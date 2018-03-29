from Domain.Lab import Validator,Laboratory

class FileLabRepo():

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
            lab = Laboratory(int(list[0]),int(list[1]),list[2])
            rez.append(lab)
            line = f.readline().strip()
        f.close()
        return rez

    def add(self,lab):
        validator = Validator()
        all = self.loadFromFile()
        if validator.validate(lab)==True:
            for i in all:
                if i == lab:
                    raise ValueError("Laboratory already exists")
                elif i.getLab() == lab.getLab() and i.getId() == lab.getId():
                    raise ValueError("dublicated lab number for this student")
            all.append(lab)
            self.storeToFile(all)
            return lab
        else :
            return validator.validate(lab)

    def storeToFile(self,list):
        with open(self.__file, "w") as f:
            for st in list:
                strf = str(st.getId()) + "," + str(st.getLab())+","+st.getProblem()
                strf = strf + "\n"
                f.write(strf)

    def findByID(self,id):
        all= self.loadFromFile()
        list = []
        for i in all:
            if int(i.getId())== id:
                list.append(i)
        if list == []:
            raise ValueError("Student doesn't exist")
        else:
            return list

    def __sizeof__(self):
        return len(self.loadFromFile())

    def getAll(self):
        return self.loadFromFile()

def testRepo():
    repo = FileLabRepo('lab_test.txt')
    assert  repo.__sizeof__()== 3
    try :
        repo.findByID(1)
    except ValueError:
        assert False

    try :
        repo.findByID(5)
        assert False
    except ValueError:
        assert True

testRepo()

