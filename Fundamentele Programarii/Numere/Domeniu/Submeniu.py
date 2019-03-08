from Domeniu.Complex import *
from Interfata.Afisare import AfisareComplex

def Submeniu_1_1():
    """
    Adaugă număr complex la sfârșitul unei liste
    Afiseaza rezultat
    """
    real = VerificareParteReala()
    imaginar = VerificareParteImaginara()
    print('\n')
    Adauga(real, imaginar)
    AfisareComplex(getlista())


def Submeniu_1_2():
    """
    Inserare număr complex pe o poziție dată.
    """
    real=VerificareParteReala()
    imaginar=VerificareParteImaginara()
    lista = getlista()
    poz=VerificarePozitie(lista)
    print('\n')
    AdaugaPoz(real,imaginar,poz)
    AfisareComplex(getlista())


def Submeniu_2_1():
    """
    Șterge element de pe o poziție dată
    """
    lista = getlista()
    pozitie=VerificarePozitie(lista)
    print('\n')
    StergereElement(pozitie)
    AfisareComplex(getlista())


def Submeniu_2_2():
    """
    Șterge elementele de pe un interval de poziții.
    """
    inceput = VerificareInceputSecventa()
    sfarsit = VerificareSfarsitSecventa()
    print('\n')
    StergereSubsecventa(inceput,sfarsit)
    AfisareComplex(getlista())


def Submeniu_2_3():
    """
    Inlocuiește toate aparițiile unui număr complex cu un alt număr complex
    """
    print("Inserati partea reala si partea imaginara a numarului pe care doriti sa il inlocuiti:")
    real_inlocuit = VerificareParteReala()
    imaginar_inlocuit = VerificareParteImaginara()
    print("Inserati partea reala si partea imaginara a numarului cu care doriti sa il inlocuiti pe cel anterior:")
    real = VerificareParteReala()
    imaginar = VerificareParteImaginara()
    print('\n')
    Inlocuire(real_inlocuit,imaginar_inlocuit,real,imaginar)
    AfisareComplex(getlista())


def Submeniu_3_1():
    """
    Tipărește partea imaginara pentru numerele din listă. Se dă intervalul de poziții (subsecvența).

    """
    inceput = VerificareInceputSecventa()
    sfarsit = VerificareSfarsitSecventa()
    print('\n')
    print(CautaImaginar(inceput,sfarsit))


def Submeniu_3_2():
    """
     Tipărește toate numerele complexe care au modulul mai mic decât 10
    """
    TiparesteModulMaiMic(10)


def Submeniu_3_3():
    """
    Tipărește toate numerele complexe care au modulul egal cu 10
    """
    TiparesteModulEgal(10)


def Submeniu_4_1():
    """
    suma numerelor dintr-o subsecventă dată (se da poziția de început și sfârșit).
    """

    inceput = VerificareInceputSecventa()
    sfarsit = VerificareSfarsitSecventa()
    print('\n')
    lista = Suma_Subsecventa(inceput, sfarsit)
    if lista[1] > 0:
        print(lista[0], '+', lista[1], 'i')
    else:
        print(lista[0], lista[1], 'i')


def Submeniu_4_2():
    """
    Produsul numerelor dintr-o subsecventă dată (se da poziția de început și sfârșit).
    """

    inceput = VerificareInceputSecventa()
    sfarsit = VerificareSfarsitSecventa()
    print('\n')
    lista = Inmultire(inceput,sfarsit)
    if lista[1] >0:
        print(lista[0],'+',lista[1],'i')
    else :
        print(lista[0],lista[1],'i')


def Submeniu_4_3():
    """
    Tipărește lista sortată descrescător după partea imaginara
    """
    print('\n')
    Sortare_descrescatoare_imaginara()


def Submeniu_5_1():
    """
    Filtrare parte reala prim – elimină din listă numerele complexe la care partea reala este prim
    """

    print('\n')
    FiltrarePrim()
    AfisareComplex(getlista())


def Submeniu_5_2():
    """
    Filtrare modul – elimina din lista numerele complexe la care modulul este = cu un număr dat.
    """
    modul = VerificareModul()
    print('\n')
    FiltrareModulEgal(modul)
    AfisareComplex(getlista())


def Submeniu_5_3():
    """
    Filtrare modul – elimina din lista numerele complexe la care modulul este > decâtun număr dat.
    """
    modul = VerificareModul()
    print('\n')
    FiltrareModulMaiMare(modul)
    AfisareComplex(getlista())


def Submeniu_5_4():
    """
    Filtrare modul – elimina din lista numerele complexe la care modulul este < decâtun număr dat.
    """
    modul = VerificareModul()
    print('\n')
    FiltrareModulMaiMic(modul)
    AfisareComplex(getlista())


def Submeniu_6():
    """
       Reface ultima operație (lista de numere revine la numerele ce existau înainte de ultima
        operație care a modificat lista)
    """
    Undo()
    AfisareComplex(getlista())


def VerificareLista():
    return getlista()