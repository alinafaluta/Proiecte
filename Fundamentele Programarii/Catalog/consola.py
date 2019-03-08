
from catalog import Catalog
class Consola:

    def __init__(self):
        self.__cat = Catalog()

    def __addStudentUi(self):
        nume = input("dati nume:")
        varsta = input("dati varsta:")
        nrmatricol = input("nr matricol: ")
        st = self.__cat.addStudent(nrmatricol, nume, varsta)
        print(st.getNume())
    def startUi(self):
        while True:
            print(" x - iesire")
            print("add - adaugare")
            comanda = input("Dati comanda:")
            if comanda == 'x':
                break
            elif comanda == 'add':
                try:
                    self.__addStudentUi()
                except ValueError as ex:
                    print(ex)

            elif comanda == '2':
                l = self.__cat.raportVarsta



