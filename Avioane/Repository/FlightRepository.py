from Domain.Flight import Flight

class FileRepository:

    def __init__(self, fileName):
        self.__file = fileName
        self.LoadFromFile()

    def LoadFromFile(self):
        try:
            f = open(self.__file,'r')
        except IOError:
            return
        rez = []
        line = f.readline().strip()
        l = 1
        while line!='':
            list = line.split(',')
            try:
                flight = Flight(int(list[0]),list[1],list[2],list[3])
            except KeyError:
                return l
            rez.append(flight)
            line = f.readline().strip()
            l+=1

        f.close()
        return rez

    def __sizeof__(self):
        l = self.LoadFromFile()
        if type(l)== int:
            raise ValueError("The type of the flight can only be ARRIVAL or DEPARTURE. Look at line "+str(l))
        return len(self.LoadFromFile())

    def __delete__(self, flight_number):
        all = self.LoadFromFile()
        rez =[]
        for i in all:
            if i.getFlight()!= flight_number:
                rez.append(i)

        self.storeTOfile(rez)

    def storeTOfile(self,list):
        with open(self.__file,'w')as f:
            for i in list:
                string = str(i.getFlight())+","+str(i.getType())+","+str(i.getCompany())+","+str(i.getCity())+'\n'
                f.write(string)
        f.close()


    def get_by_name(self,company):
        all = self.LoadFromFile()
        rez = []
        for i in all:
            if i.getCompany() == company:
                rez.append(i)
        return rez

    def getAll(self):
        l = self.LoadFromFile()
        if type(l) == int:
            raise ValueError("The type of the flight can only be ARRIVAL or DEPARTURE. Look at line " + str(l))
        return self.LoadFromFile()

def test():
    file = 'test.txt'
    repo = FileRepository(file)
    try:
        assert repo.__sizeof__()==6
    except ValueError as er:
        print(er)

    repo.__delete__(5)
    assert repo.__sizeof__() == 6
    assert len(repo.get_by_name('Blue Air')) == 3

test()