from Domeniu.Submeniu import *
from Domeniu.Verificare import *
from Interfata.Afisare import *


def start():
    continuare = True
    while continuare:
        ValoareMeniu = Meniu_Initial()
        ValoareMeniu = VerificareMeniuInitial(ValoareMeniu)
        if ValoareMeniu == 1 or ValoareMeniu in {'1'}:
            ComandaInserare()
            Citire_lista()
            while True:
                comanda = Comenzi()
                comanda = VerificareComenzi(comanda)
                if comanda == 7 or comanda in {'7'}:
                    break
                elif comanda == 0 or comanda in {'0'}:
                    continuare = False
                    break
                while True:
                    if VerificareLista() == []:
                        print('Lista este vida. Va rugam inserati valori')
                        break
                    if comanda == 1 or comanda in {'1'}:
                        meniu = Meniu_1()
                        meniu = VerificareSubmeniu1(meniu)
                        if meniu == 0 or meniu in {'0'}:
                            break
                        elif meniu == 1 or meniu in {'1'}:
                            Submeniu_1_1()
                        else:
                            Submeniu_1_2()
                    elif comanda == 2 or comanda in {'2'}:
                        meniu = Meniu_2()
                        meniu = VerificareSubmeniu2(meniu)
                        if meniu == 0 or meniu in {'0'}:
                            break
                        elif meniu == 1 or meniu in {'1'}:
                            Submeniu_2_1()
                        elif meniu == 2 or meniu in {'2'}:
                            Submeniu_2_2()
                        else:
                            Submeniu_2_3()
                    elif comanda == 3 or comanda in {'3'}:
                        meniu = Meniu_3()
                        meniu = VerificareSubmeniu3(meniu)
                        if meniu == 0 or meniu in {'0'}:
                            break
                        elif meniu == 1 or meniu in {'1'}:
                            Submeniu_3_1()
                        elif meniu == 2 or meniu in {'2'}:
                            Submeniu_3_2()
                        else:
                            Submeniu_3_3()
                    elif comanda == 4 or comanda in {'4'}:
                        meniu = Meniu_4()
                        meniu = VerificareSubmeniu4(meniu)
                        if meniu == 0 or meniu in {'0'}:
                            break
                        elif meniu == 1 or meniu in {'1'}:
                            Submeniu_4_1()
                        elif meniu == 2 or meniu in {'2'}:
                            Submeniu_4_2()
                        else:
                            Submeniu_4_3()
                    elif comanda == 5 or comanda in {'5'}:
                        meniu = Meniu_5()
                        meniu = VerificareSubmeniu5(meniu)
                        if meniu == 0 or meniu in {'0'}:
                            break
                        elif meniu == 1 or meniu in {'1'}:
                            Submeniu_5_1()
                        elif meniu == 2 or meniu in {'2'}:
                            Submeniu_5_2()
                        elif meniu == 3 or meniu in {'3'}:
                            Submeniu_5_3()
                        else:
                            Submeniu_5_4()
                    elif comanda == 6 or comanda in {'6'}:
                        meniu = Meniu_6()
                        meniu = VerificareSubmeniu6(meniu)
                        if meniu == 0 or meniu in {'0'}:
                            break
                        elif meniu == 1 or meniu in {'1'}:
                            Submeniu_6()
        else:
            break
    Exit()


def start2():
    print('1.creare lista')
    print('2.adaugare element pe ultima pozitie')
    print('3.adaugare element pe o pozitie data')
    print('4.afiseaza suma elementelor dintr-o subsecventa(se da pozitia de inceput si cea de sfarsit)')
    print('5.afiseaza produsul elementelor dintr-o subsecventa(se da pozitia de inceput si cea de sfarsit)')
    print('6.tipareste lista ordonata crescator dupa partea imaginara')
    print('7.sterge un element de pe o pozitie data')
    print('8.sterge o subsecventa')
    print('9.inlocuieste toate aparitiile unui numar cu altul')
    print('10.Filtreaza numerele care au partea reala numar prim')
    print('11.Filtreaza elementele cu modulul egal cu o valoare data')
    print('12.Filtreaza elementele cu modulul mai mare decat o valoare data')
    print('13.Filtreaza elementele cu modulul mai mic decat o valoare data')
    print('14.Tipărește partea imaginara pentru numerele din listă. Se dă intervalul de poziții (subsecvența).')
    print('15.Tipărește toate numerele complexe care au modulul mai mic decât 10')
    print('16.Tipărește toate numerele complexe care au modulul egal cu 10')
    print('17.Undo')
    print('0.inchide aplicatia')
    comanda = input("Dati comanda:")
    comanda = VerificareComenziUI2(comanda)
    while True:
        if comanda == 0 or comanda in {'0'}:
            break
        elif comanda == 1 or comanda in {'1'}:
            ComandaInserare()
            Citire_lista()
        elif comanda == 2 or comanda in {'2'}:
            Submeniu_1_1()
        elif comanda == 3 or comanda in {'3'}:
            Submeniu_1_2()
        elif comanda == 4 or comanda in {'4'}:
            Submeniu_4_1()
        elif comanda == 5 or comanda in {'5'}:
            Submeniu_4_2()
        elif comanda == 6 or comanda in {'6'}:
            Submeniu_4_3()
        elif comanda == 7 or comanda in {'7'}:
            Submeniu_2_1()
        elif comanda == 8 or comanda in {'8'}:
            Submeniu_2_2()
        elif comanda == 9 or comanda in {'9'}:
            Submeniu_2_3()
        elif comanda == 10 or comanda in {'10'}:
            Submeniu_5_1()
        elif comanda == 11 or comanda in {'11'}:
            Submeniu_5_2()
        elif comanda == 12 or comanda in {'12'}:
            Submeniu_5_3()
        elif comanda == 13 or comanda in {'13'}:
            Submeniu_5_4()
        elif comanda == 14 or comanda in {'14'}:
            Submeniu_3_1()
        elif comanda == 15 or comanda in {'15'}:
            Submeniu_3_2()
        elif comanda == 16 or comanda in {'16'}:
            Submeniu_3_3()
        elif comanda == 17 or comanda in {'17'}:
            Submeniu_6()
        if VerificareLista() == []:
            print("Lista este vida. Va rugam inserati valori")
            comanda = 1
        else:
            comanda = input("Dati comanda:")
            comanda = VerificareComenziUI2(comanda)
    Exit()

start()
