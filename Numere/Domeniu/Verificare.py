from Domeniu.Complex import *
def VerificareComenziUI2(comanda):
    '''
        verifica daca valoarea introdusa de utilizator este una dintre comenzi
        '''
    if comanda not in {'0', '1', '2', '3', '4', '5', '6','7','8','9','10','11','12','13','14','15','16','17'}:
        while True:
            try:
                comanda = int(input("Introduceti o comanda din meniu: "))
                if comanda in {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17}:
                    break
            except ValueError:
                print("Oops! Valoarea introdusa nu este un numar din meniu ")

    return comanda

def VerificareNumarElemente():
    '''
    verifica corectitudinea datelor
    :return: numarul de elemente din lista
    '''
    while True:
        try:
            elemente = int(input("   Introduceti numarul de elemente din lista n= "))
            if elemente >0 :
                  break
        except ValueError:
            print("     Valoarea introdusa nu este numar ")
    return elemente

def VerificareParteReala():
    '''
    verifica daca partea reala este int
    :return: partea reala a numarului complex pe care utilizatorul doreste sa il insereze in lista
    '''
    while True:
        try:
            real = float(input("   Introduceti real= "))
            break
        except ValueError:
            print("     Valoarea introdusa nu este numar ")
    if int(real) == real:
        return int(real)
    else:
        return real

def VerificareParteImaginara():
    '''
    verifica daca partea imaginara este int
    :return: partea imaginara a numarului complex pe care utilizatorul doreste sa il insereze in lista
    '''
    while True:
        try:
            imaginar = float(input("   Introduceti imaginar= "))
            break
        except ValueError:
            print("     Valoarea introdusa nu este numar ")
    if int(imaginar) == imaginar:
        return int(imaginar)
    else:
        return imaginar

def VerificarePozitie(lista):
    '''
    verifica daca pozitia este nr pozitiv
    :return: pozitia pe care se doreste inserarea unui element
    '''
    while True:
        try:
            poz = int(input("       Introduceti pozitia pe care doriti sa efectuati operatia: "))
            if poz-1 >= 0 and poz-1 <= len(lista):
                break
            else:
                raise ValueError
        except ValueError:
            print("       Oops! Pozitie invalida")
    return poz-1

def VerificareInceputSecventa():
    '''
    Verifica daca pozitia de inceput a secventei este numar pozitiv
    :return: pozitia de inceput a subsecventei
    '''
    while True:
        try:
            inceput = int(input("       Introduceti pozitia de unde incepe subsecventa(aceasta trebuie sa fie un numar pozitiv >0): "))
            if inceput > 0:
                break
        except ValueError:
            print("      Oops! Valoarea introdusa nu este numar ")
    return inceput-1

def VerificareSfarsitSecventa():
    '''
    Verifica daca pozitia de sfarsit a secventei este numar pozitiv
    :return: pozitia de sfarsit a subsecventei
    '''
    while True:
        try:
            sfarsit = int(input("     Introduceti pozitia unde se termina subsecventa(aceasta trebuie sa fie un numar pozitiv >0): "))
            if sfarsit > 0:
                break
        except ValueError:
            print("       Oops! Valoarea introdusa nu este numar ")
    return sfarsit-1

def VerificareModul():
    '''
    Verifica daca modulul este numar pozitiv
    :return:  modulul
    '''
    while True:
        try:
            modul = float(input( "     Introduceti valoarea modulului(numar pozitiv) cu care se vor efectua comparatiile: "))
            if modul > 0:
                break
        except ValueError:
            print("       Oops! Valoarea introdusa nu este numar ")
    if int(modul) == modul:
        return int(modul)
    else:
        return modul

def VerificareMeniuInitial(comanda):
    '''
    verifica daca valoarea introdusa de utilizator este una din meniu
    '''
    if comanda not in {'1', '0'}:
        while True:
            try:
                comanda = int(input("     Introduceti o comanda din meniu: "))
                if comanda == 1 or comanda == 0:
                    break
            except ValueError:
                print("       Oops! Valoarea introdusa nu este un numar din meniu")

    return comanda

def VerificareComenzi(comanda):
    '''
    verifica daca valoarea introdusa de utilizator este una dintre comenzi
    '''
    if comanda not in {'0', '1', '2', '3', '4', '5', '6','7'}:
        while True:
            try:
                comanda = int(input("Introduceti o comanda din meniu: "))
                if comanda in {0, 1, 2,3,4,5,6,7}:
                    break
            except ValueError:
                print("Oops! Valoarea introdusa nu este un numar din meniu ")

    return comanda

def VerificareSubmeniu1(comanda):
    '''
        V erifica daca valoarea introdusa de utilizator este una dintre comenzile din meniul 1
    '''
    if comanda not in {'0','1', '2'}:
        while True:
            try:
                comanda = int(input("   Introduceti o comanda din meniu: "))
                if comanda in {0,1,2}:
                    break
            except ValueError:
                print(" Oops! Valoarea introdusa nu este numar ")
    return comanda

def VerificareSubmeniu2(comanda):
    '''
        V erifica daca valoarea introdusa de utilizator este una dintre comenzile din meniul 2
    '''
    if comanda not in {'0','1', '2', '3'}:
        while True:
            try:
                comanda = int(input("   Introduceti o comanda din meniu: "))
                if comanda in {0,1,2,3}:
                    break
            except ValueError:
                print(" Oops! Valoarea introdusa nu este numar ")
    return comanda

def VerificareSubmeniu3(comanda):
    '''
        V erifica daca valoarea introdusa de utilizator este una dintre comenzile din meniul 5
    '''
    if comanda not in {'0','1', '2','3'}:
        while True:
            try:
                comanda = int(input("   Introduceti o comanda din meniu: "))
                if comanda in {0,1,2,3}:
                    break
            except ValueError:
                print(" Oops! Valoarea introdusa nu este numar ")
    return comanda

def VerificareSubmeniu4(comanda):
    '''
        Verifica daca valoarea introdusa de utilizator este una dintre comenzile din meniul 4
    '''
    if comanda not in {'0','1', '2','3'}:
        while True:
            try:
                comanda = int(input("   Introduceti o comanda din meniu: "))
                if comanda in {0,1,2,3}:
                    break
            except ValueError:
                print("     Oops! Valoarea introdusa nu este numar ")
    return comanda

def VerificareSubmeniu5(comanda):
    '''
        V erifica daca valoarea introdusa de utilizator este una dintre comenzile din meniul 5
    '''
    if comanda not in {'0','1', '2','3'}:
        while True:
            try:
                comanda = int(input("   Introduceti o comanda din meniu: "))
                if comanda in {0,1,2,3}:
                    break
            except ValueError:
                print(" Oops! Valoarea introdusa nu este numar ")
    return comanda

def VerificareSubmeniu6(comanda):
    '''
        V erifica daca valoarea introdusa de utilizator este una dintre comenzile din meniul 5
    '''
    if comanda not in {'0','1'}:
        while True:
            try:
                comanda = int(input("   Introduceti o comanda din meniu: "))
                if comanda in {0,1}:
                    break
            except ValueError:
                print(" Oops! Valoarea introdusa nu este numar ")
    return comanda

def VerificareNatural(numar):
    """
    verifica daca un numar este natural sau nu
    :param numar: numarul de verificat
    :return: true daca este natural, false in caz contrar
    """
    if int(numar) >= 0 :
        return True
    return False