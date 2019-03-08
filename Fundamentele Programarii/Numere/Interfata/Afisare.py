
def Meniu_Initial():
    """
        afiseaza principalele comenzi necesare la initializarea aplicatiei
        :return: comanda aleasa de utilizator
    """
    print("\n1.Inserați o listă de numere complexe reprezentate prin partea lor reală și partea lor imaginară")
    print("0.Inchideți aplicația")
    comanda=input("     Dati comanda:")
    return comanda


def ComandaInserare():
    """
        Afiseaza submeniul corespunzator comenzii 1.Inserati o lista de numere complexe reprezentate prin partea lor reala si partea lor imaginara
    """
    print("\n   Inserați un numar n apoi n numere complexe reprezentate prin partea lor reală și partea lor imaginară")
    print("   N trebuie să fie numar natural iar 'real' va fi partea reală și 'imaginar' partea imaginară")



def Exit():
    """
        Afiseaza textul corespunzator inchiderii aplicatiei
    """
    print("")
    print("Vă mulțumim că ați ales să utilizați aplicația noastră. Vă așteptăm să reveniți.")
    print("                                La revedere!                           ")



def Comenzi():
    """
        Afiseaza comenzile principale la care poate avea acces utilizatorul
        :return: comanda aleasa de utilizator
    """
    print("\n1.Adaugă număr în listă.")
    print("2.Modifică elemente din listă.")
    print("3.Căutare numere.")
    print("4.Operații cu numerele din listă.")
    print("5.Filtrare.")
    print("6.Undo.")
    print("7.Meniu anterior.")
    print("0.Închideți aplicația.")
    comanda = input("   Dati comanda:")
    return comanda



def Meniu_1():
    """
        Afiseaza submeniul corespunzator comenzii 1.Adaugă număr în listă
        :return: comanda aleasa de utilizator
    """
    print("\n     1. Adaugă număr complex la sfârșitul listei.")
    print("     2. Inserare număr complex pe o poziție dată.")
    print("     0. Întoarceți-vă la meniul anterior.")
    comanda = input("           Dati comanda:")
    return comanda



def Meniu_2():
    """
        Afiseaza submeniul corespunzator comenzii 2.Modifică elemente din listă.
        :return: comanda aleasa de utilizator
    """
    print("\n     1. Șterge element de pe o poziție dată.")
    print("     2. Șterge elementele de pe un interval de poziții.")
    print("     3. Înlocuiește toate aparițiile unui număr complex cu un alt număr complex.")
    print("     0. Întoarceti-va la meniul anterior")
    comanda = input("           Dati comanda:")
    return comanda



def Meniu_3():
    """
        Afiseaza submeniul corespunzator comenzii 3.Căutare numere.
        :return: comanda aleasa de utilizator
    """
    print("\n     1.Tipărește partea imaginara pentru numerele din listă. Se dă intervalul de poziții (subsecvența).")
    print("     2.Tipărește toate numerele complexe care au modulul mai mic decât 10.")
    print("     3. Tipărește toate numerele complexe care au modulul egal cu 10")
    print("     0. Întoarceti-va la meniul anterior")
    comanda = input("           Dati comanda:")
    return comanda


def Meniu_4():
    """
        Afiseaza submeniul corespunzator comenzii 4.Operații cu numerele din listă.
        :return: comanda aleasa de utilizator
    """
    print("\n     1. Suma numerelor dintr-o subsecventă dată (se da poziția de început și sfârșit).")
    print("     2. Produsul numerelor dintr-o subsecventă dată (se da poziția de început și sfârșit).")
    print("     3. Tipărește lista sortată descrescător după partea imaginara.")
    print("     0. Întoarceti-va la meniul anterior")
    comanda = input("           Dati comanda:")
    return comanda



def Meniu_5():
    """
        afiseaza submeniul corespunzator comenzii 5.Filtrare.
        :return: comanda aleasa de utilizator
    """
    print("\n     1. Filtrare parte reala prim – elimină din listă numerele complexe la care partea reala este prim")
    print("     2. Filtrare modul – elimina din lista numerele complexe la care modulul este = cu un număr dat.")
    print("     3. Filtrare modul – elimina din lista numerele complexe la care modulul este > decat un număr dat.")
    print("     4. Filtrare modul – elimina din lista numerele complexe la care modulul este < decat un număr dat.")
    print("     0. Întoarceti-va la meniul anterior")
    comanda = input("           Dati comanda:")
    return comanda


def Meniu_6():
    """
        afiseaza submeniul corespunzator comenzii 6.Undo.
        :return: comanda aleasa de utilizator
    """
    print("\n     1. Reface ultima operație (lista de numere revine la numerele ce existau înainte de ultima operație care a modificat lista)")
    print("     0. Întoarceti-va la meniul anterior")
    comanda = input("           Dati comanda:")
    return comanda

def AfisareComplex(lista):
    """
    Afiseaza numerele complexe dintr-o lista de dictionare sub forma a+/- bi
    :param lista: lista de numere complexe
    """
    complex={}
    for i in range(0,len(lista)):
        complex=lista[i]
        if complex['imaginar']>=0:
            print(complex['real'],'+',complex['imaginar'],'i')
        else:
            print(complex['real'], complex['imaginar'],'i', end='')


