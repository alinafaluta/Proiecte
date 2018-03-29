class Varstacati():
    def __init__(self, varsta, cati):
        self.__varsta = varsta
        self.__cati = cati

    def getvarsta(self):
        return self.__varsta

    def getcati(self):
        return self.__cati
    def incrementcati(self):
        self.__cati=self.__cati + 1
class Student():

    def __init__(self, NrMatricol, Nume, Varsta):
        self.__NrMatricol = NrMatricol
        self.__Nume = Nume
        self.__Varsta = Varsta

    def getNume(self):
        return self.__Nume

    def getNrMatricol(self):
        return self.__NrMatricol

    def getVarsta(self):
        return self.__Varsta

    def __str__(self):
        return "nume: "+self.__Nume

def TestCreateStud():
    st = Student('31243','ioan',23)
    assert st.getNume() == 'ioan'
    assert st.getNrMatricol() == '31243'
    assert st.getVarsta() == 23

TestCreateStud()

