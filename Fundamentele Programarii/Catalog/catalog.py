from Student import *
class Catalog():
    def __init__(self):
        self.__studenti = []

    def addStudent(self, NrMatricol, Nume, Varsta):
        st = Student(NrMatricol, Nume, Varsta)
        self.__studenti.append(st)
        return st

    def getAll(self):
        return self.__studenti

    def raportVarstaPerStudent(self):
        d={}
        for st in self.__studenti:
            if st.getVarsta() in d:
                d[st.getVarsta()].incrementcati()
            else:
                d[st.getVarsta()] == Varstacati(st.getVarsta(),1)
        return list(d.values())


def TestAdaugaSt():
    cat = Catalog()
    st = cat.addStudent("ers33", 'ion', 23)
    assert st.getNume() == 'ion'
    assert st.getVarsta() == 23

    studenti = cat.getAll()
    assert len(studenti) == 1

    st = cat.addStudent("ersaa33", "ioaan", 234)
    assert st.getNume() == "ioaan"
    assert st.getVarsta() == 234

    studenti = cat.getAll()
    assert len(studenti) == 2


TestAdaugaSt()