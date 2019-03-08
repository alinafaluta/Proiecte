from Domeniu.Complex import *

def TestAdauga():
    """
    verifica functia de adaugare a valorilor
    """
    crearelista()
    Adauga( 0, 0)
    assert getlista() == []
    Adauga( 3, 4)
    lista = getlista()
    assert lista == [{'real': 3, 'imaginar': 4}]

TestAdauga()

def TestAdaugaPoz():
    """
        verifica functia de adaugare a unui numar complex (partea reala si imaginara sunt intr-o lista) pe o pozitie data
    """
    crearelista()
    Adauga(1, 2)
    Adauga(3, 4)
    Adauga(5, 5)
    AdaugaPoz(7, 8, 2)
    lista = getlista()
    assert lista == [{'real': 1, 'imaginar': 2}, {'real': 3, 'imaginar': 4},{'real': 7, 'imaginar': 8}, {'real': 5, 'imaginar': 5}]

TestAdaugaPoz()

def TestStergereElement():
    """
    verifica functia ce sterge un element din lista
    """
    crearelista()
    Adauga( 1, 2)
    Adauga( 3, 4)
    Adauga( 5, 5)
    StergereElement(2)
    lista = getlista()
    assert lista == [{'real': 1, 'imaginar': 2}, {'real': 3, 'imaginar': 4}]

TestStergereElement()

def TestStergeSubsecventa():
    """
    verifica functia ce sterge o subsecventa din lista
    """
    crearelista()
    Adauga(1, 2)
    Adauga(4, 3)
    Adauga(3, 2)
    Adauga(4, 3)
    Adauga(3, 2)
    StergereSubsecventa(2,4)
    lista = getlista()
    assert lista == [{'real': 1, 'imaginar': 2}, {'real': 4, 'imaginar': 3}]
    Adauga(4, 5)
    Adauga(6, 7)
    StergereSubsecventa(2,1)
    lista = getlista()
    assert lista == [{'real': 1, 'imaginar': 2}, {'real': 4, 'imaginar': 3},{'real': 4, 'imaginar': 5}, {'real': 6, 'imaginar': 7}]

TestStergeSubsecventa()

def TestInlocuire():
    """
    verifica functia de inlocuire a tuturor aparitiilor unui numar complex cu un altul
    """
    crearelista()
    Adauga(1, 2)
    Adauga(4, 3)
    Adauga(3, 2)
    Adauga(4, 3)
    Adauga(3, 2)
    Inlocuire(4, 3, 0, 0)
    lista = getlista()
    assert lista == [{'real': 1, 'imaginar': 2}, {'real': 0, 'imaginar': 0},{'real': 3, 'imaginar': 2}, {'real': 0, 'imaginar': 0},{'real': 3, 'imaginar': 2}]
    Inlocuire(4,34, 2, 2)
    lista = getlista()
    assert lista == [{'real': 1, 'imaginar': 2}, {'real': 0, 'imaginar': 0},{'real': 3, 'imaginar': 2}, {'real': 0, 'imaginar': 0},{'real': 3, 'imaginar': 2}]

TestInlocuire()


def TestVerificareNatural():
    assert VerificareNatural(-2) == False
    assert VerificareNatural(0) == True
    assert VerificareNatural(3) == True

TestVerificareNatural()

def TestPrim():
    """
    verifica functia prim
    """
    assert prim(0) == False
    assert prim(23) == True
    assert prim(13) == True
    assert prim(20) == False
    assert prim(-2) == False

TestPrim()

def TestFiltrarePrim():
    """
    verifica functia de filtrare a elementelor care au partea reala numar prim
    """
    crearelista()
    lista = getlista()
    assert lista == []
    Adauga(13, 2)
    Adauga(4, 3)
    Adauga(23, 2)
    Adauga(44, 3)
    Adauga(23, 2)
    FiltrarePrim()
    assert lista == [{'real':4,'imaginar':3},{'real':44,'imaginar':3}]
    Adauga(234,5)
    Adauga(332,2)
    FiltrarePrim()
    lista = getlista()
    assert lista == [{'real':4,'imaginar':3},{'real':44,'imaginar':3},  {'real':234,'imaginar':5},{'real':332,'imaginar':2}]

TestFiltrarePrim()

def TestModul():
    assert Modul(3,4) == 5.0
    assert Modul (3.5, 5.4) == 6.4
    assert Modul (1,1) == 1.4
    assert Modul(0,0) == 0.0

TestModul()

def TestFiltrareModulEgal():
    """
    verifica functia de filtrare a elementelor care au modulul egal cu o valoare data
    """
    crearelista()
    Adauga(13, 2)
    Adauga(4, 3)
    Adauga(23, 2)
    Adauga(44, 3)
    Adauga(23, 2)
    FiltrareModulEgal(5)
    lista = getlista()
    assert lista == [{'real':13,'imaginar':2}, {'real':23,'imaginar':2}, {'real':44,'imaginar':3}, {'real':23,'imaginar':2}]
    FiltrareModulEgal(3)
    lista = getlista()
    assert lista == [{'real':13,'imaginar':2}, {'real':23,'imaginar':2}, {'real':44,'imaginar':3}, {'real':23,'imaginar':2}]

TestFiltrareModulEgal()

def TestFiltrareModulMaiMare():
    """
    verifica functia de filtrare a elementelor care au modulul mai mare decat o valoare data
    """
    crearelista()
    Adauga(13, 2)
    Adauga(4, 3)
    Adauga(23, 2)
    Adauga(44, 3)
    Adauga(23, 2)
    FiltrareModulMaiMare(2)
    lista = getlista()
    assert lista == [{'real': 4, 'imaginar': 3}, {'real': 44, 'imaginar': 3}]
    FiltrareModulMaiMare(0)
    lista = getlista()
    assert lista == [{'real': 44, 'imaginar': 3}]

TestFiltrareModulMaiMare()

def Test_Inmultire():

    """
        verifica functia ce realizeaza produsul numerelor complexe dintr-o subsecventa data
    """
    crearelista()
    Adauga(1, 2)
    Adauga(3, 4)
    Adauga(5, 6)
    assert Inmultire( 0, 1) == [-5, 10]
    crearelista()
    Adauga( 16, 24)
    Adauga(3, 4)
    Adauga(5, 7)
    Adauga( 1, 2)
    Adauga(3, 4)
    Adauga(16, 24)
    assert Inmultire( 10, 12) == [16, 24]

Test_Inmultire()

def Test_Suma_Subsecventa():
    """
    testeza functia de suma a unei subsecvente
    """
    crearelista()
    Adauga(1, 1)
    Adauga( 3, 4)
    Adauga(5, 6)
    Adauga( 7, 8)
    assert Suma_Subsecventa( 2, 3) == [12, 14]

Test_Suma_Subsecventa()

def Test_CautaImaginar():
    """
    testeaza functia ce creaza o lista cu partile imaginare ale unei liste cu numere complexe
    """
    crearelista()
    Adauga(1, 1)
    Adauga( 3, 4)
    Adauga( 5, 6)
    Adauga( 7, 8)
    assert  CautaImaginar(1, 3) == [4, 6, 8]

Test_CautaImaginar()

def testUndo():
    """
    Testeaza functia undo
    """
    crearelista()
    Adauga(1, 2)
    Adauga(4, 5)
    Adauga(3, 4)
    Undo()
    Undo()
    Adauga(5,6)
    lista = getRefacList()
    assert lista == [{'real':1,'imaginar':2},{'real':5,'imaginar':6}]

testUndo()