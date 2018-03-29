from math import sqrt

from Domeniu.Submeniu import *
from Domeniu.Verificare import *
from Interfata.Afisare import AfisareComplex

list = []
refaclist = [[]]

def getReal(complex):
    """
    :param complex: un dictionar cu doua chei
    :return: valoarea corespunzatoare cheii 'real' (partea reala a numarului complex)
    """
    return complex['real']


def getImaginar(complex):
    """
    :param complex: un dictionar cu doua chei
    :return: valoarea corespunzatoare cheii 'imaginar' (partea imaginara a numarului complex)
    """
    return complex['imaginar']


def getlista():
    """
    furnizeaza valoarea curenta a listei
    """
    global list
    return list


def getRefacList():
    """
    furnizeaza ultima valoarea din lista pentru undo
    """
    return refaclist[-1]


def getKey(item):
    """
         stabileste cheia dupa care se face sortarea adica partea imaginara
    """
    return item['imaginar']


def crearelista():
    """
    initializeaza lista cu care vom lucra
    """
    global list
    list = []


def Citire_lista():
    """
    citeste  elementele inserate de utilizator in lista daca acestea sunt corecte
    """
    global list
    global refaclist
    list = []
    elemente = VerificareNumarElemente()
    for i in range(0, elemente):
        complex = {}
        real = VerificareParteReala()
        imaginar = VerificareParteImaginara()
        complex['real'] = real
        complex['imaginar'] = imaginar
        list.append(complex)
    AfisareComplex(getlista())
    refaclist.append(list[:])


def CautaImaginar(inceput,sfarsit):
    """
    Tipărește partea imaginara pentru numerele din listă. Se dă intervalul de poziții (subsecvența)
    daca inceput>sfarsit atunci stergerea nu se efectueaza
    daca inceput<0 atunci secventa va incepe de la 0
    daca sfarsit >lungimea listei sfarsit va fi egal cu lungimea listei
        :param inceput: pozitia de inceput a subsecventei
        :param sfarsit: pozitia de sfarsit a subsecventei
        :return: o lista cu partea imaginara a numerelor din lista
    """
    global list
    global refaclist
    imaginar = []
    if sfarsit > len(list):
        sfarsit = len(list)
    if inceput > len(list):
        inceput = len(list) - 1
    if inceput < 0:
        inceput = 0
    for i in range (inceput,sfarsit+1):
        complex = list[i]
        imaginar.append(getImaginar(complex))
    return imaginar
    refaclist.append(list[:])


def Modul(real, imaginar):
    """
    calculeaza modulul unui numar complex
    :param real: partea reala a numarului
    :param imaginar: partea imaginara a numarului
    :return: modulul ca valoare float
    """
    module = sqrt((real*real+imaginar*imaginar))
    module = round(module,1)
    return module


def prim(element):
    """
    verifica daca un element este prim
    :param element: numarul de verificat
    :return: True daca este prim si false in caz contrar
    """
    if element == 0 or element == 1 or element<0:
        return False
    if element == 2 :
        return True
    if element%2 == 0:
        return False
    if type(element) == float:
        return False
    for i in range (3,element):
        if element%i == 0:
            return False
    return True


def Adauga(real,imaginar):
    """
        adauga un numar complex reprezentat prin partea sa reala si cea imaginara la sfarsitul unei liste
        numarul 0 nu este numar complex , asadar daca utilizatorul insereaza doua valori egale cu 0 acesta nu va fi adaugat la lista
        input:  partea reala si partea imaginara a numarului complex
        output: lista modificata sau nu in functie de ce valori insereaza utilizatorul
    """
    global list
    global refaclist
    if real!=0 and imaginar!=0:
        complex={'real':real,'imaginar':imaginar}
        list.append(complex)
    refaclist.append(list[:])


def AdaugaPoz(real,imaginar,poz):
    """
        adauga un numar complex reprezentat prin partea sa reala si cea imaginara pe o pozitie data de utilizator
        numarul 0 nu este numar complex , asadar daca utilizatorul insereaza doua valori egale cu 0 acesta nu va fi adaugat la lista
        input:  partea reala si partea imaginara a numarului complex si pozitia pe care se va insera numarul(poz>=0)
                daca poz este mai mare decat lungimea listei elementul va fi adaugat la sfarsitul acesteia
        output: lista modificata sau nu in functie de ce valori insereaza utilizatorul
    """
    lista = getlista()
    global list
    global refaclist
    lungime = len(lista)
    if real != 0 and imaginar != 0:
        if poz >= 0 and poz <= lungime:
            inceput = []
            sfarsit = []
            for i in range(poz,lungime):
                sfarsit.append(lista[i])
            for i in range(0, poz):
                inceput.append(lista[i])
            lista = []
            if inceput == []:
                complex={'real':real,'imaginar':imaginar}
                lista.append(complex)
                for i in range (0,len(sfarsit)):
                    lista.append(sfarsit[i])
            else:
                for i in range (0,len(inceput)):
                    lista.append(inceput[i])
                complex = {'real': real, 'imaginar': imaginar}
                lista.append(complex)
                for i in range (0,len(sfarsit)):
                    lista.append(sfarsit[i])

        else:
            complex = {'real': real, 'imaginar': imaginar}
            lista.append(complex)
    list = lista
    refaclist.append(lista[:])


def Undo():
    """
    Reface ultima operatie
    """
    global refaclist
    global list
    refaclist = refaclist[:-1]
    list = refaclist[-1][:]


def Suma_Subsecventa( inceput, sfarsit):

    """
        calculeaza suma numerelor complexe dintr-o subsecventa data
        input: pozitia de inceput a secventei si de cea de sfarsit , sfarsit>=inceput
                daca utilizatorul va insera valorile de inceput si sfarsit ale secventei astfel incat acestea sa depaseasca lista se vor modifica automat valorile cu
                sfarsit = n daca sfarsit >lungimea listei si inceput=0 daca inceput<0 sau inceput=n daca inceput>lungimea listei
        output: returneaza suma sub forma de lista
    """
    global list
    global refaclist
    suma=[]
    suma_reala=0
    suma_imaginara=0
    if sfarsit<inceput:
        suma=[suma_reala,suma_imaginara]
    else:
        lungime=len(list)
        if sfarsit>lungime:
            sfarsit=lungime
        if inceput > lungime:
            inceput = lungime-1
        if inceput<0:
            inceput=0
        if sfarsit!=lungime:
            for i in range(inceput,sfarsit+1):
                complex = list[i]
                suma_reala = suma_reala + getReal(complex)
                suma_imaginara = suma_imaginara + getImaginar(complex)
        else:
            for i in range(inceput,sfarsit):
                complex = list[i]
                suma_reala = suma_reala + getReal(complex)
                suma_imaginara = suma_imaginara+getImaginar(complex)
            complex = list[sfarsit-1]
            suma_reala = suma_reala + getReal(complex)
            suma_imaginara = suma_imaginara + getImaginar(complex)
        suma = [suma_reala,suma_imaginara]
    refaclist.append(suma[:])
    return suma


def Inmultire(inceput, sfarsit):
    """
        calculeaza produsul numerelor complexe dintr-o subsecventa data
        input:input:  pozitia de inceput a secventei si de cea de sfarsit , sfarsit>=inceput
                daca utilizatorul va insera valorile de inceput si sfarsit ale secventei astfel incat acestea sa depaseasca lista se vor modifica automat valorile cu
                sfarsit = n daca sfarsit >lungimea listei si inceput=0 daca inceput<0 sau inceput=n daca inceput>lungimea listei
        output: returneaza produsul sub forma de lista
    """
    global list
    global refaclist
    produs = []
    if sfarsit < inceput:
        produs_real = 1
        produs_imaginar = 1
    else:
        if sfarsit > len(list):
            sfarsit = len(list)
        if inceput > len(list):
            inceput = len(list) - 1
        if inceput < 0:
            inceput = 0
        complex = list[inceput]
        produs_real = getReal(complex)
        produs_imaginar = getImaginar(complex)
        inceput = inceput + 1
        if sfarsit != len(list):
            for i in range(inceput, sfarsit+1):
                complex = list[i]
                real = produs_real
                imaginar = produs_imaginar
                produs_real = real * getReal(complex) - imaginar * getImaginar(complex)
                produs_imaginar = real * getImaginar(complex) + imaginar * getReal(complex)
        if sfarsit == len(list):
            for i in range(inceput, sfarsit):
                complex = list[i]
                real = produs_real
                imaginar = produs_imaginar
                produs_real = real * getReal(complex) - imaginar * getImaginar(complex)
                produs_imaginar = real * getImaginar(complex) + imaginar * getReal(complex)
    produs = [produs_real,produs_imaginar]
    refaclist.append(produs[:])
    return produs


def Sortare_descrescatoare_imaginara():
    """
        sorteaza lista ordonata descrecator dupa parte imaginara
        afiseaza lista
    """
    global list
    global refaclist
    sortata = sorted(list,key= getKey,reverse=True)
    AfisareComplex(sortata)
    refaclist.append(sortata[:])


def StergereElement(pozitie):
    """
    sterge un element de pe o pozitie data
    :param pozitite: pozitia numarului pe care dorim sa il stergem
    """
    global refaclist
    global list
    if pozitie < len(list):
        del list[pozitie]
    refaclist.append(list[:])


def StergereSubsecventa(inceput,sfarsit):
    """
    sterge o subsecventa de elemente
    daca inceput>sfarsit atunci stergerea nu se efectueaza
    daca inceput<0 atunci secventa va incepe de la 0
    daca sfarsit >lungimea listei sfarsit va fi egal cu lungimea listei
    :param inceput: pozitia de inceput a listei
    :param sfarsit: ultima pozitie din subsecventa
    """
    global list
    global refaclist
    lista = getlista()
    if inceput<sfarsit:
        if inceput<0:
            inceput=0
        if sfarsit>len(lista):
            sfarsit=len(lista)
        while sfarsit != inceput-1:
            del list[sfarsit]
            sfarsit = sfarsit - 1
        list = lista
        refaclist.append(list[:])
    else:
        list = lista
        refaclist.append(list[:])


def Inlocuire(real_inlocuit, imaginar_inlocuit, real, imaginar):
    """
    inlocuieste fie care aparitie a unui numar complex cu alt numar complex
    :param imaginar_inlocuit: partea imaginara a numarului complex pe care dorim sa il inlocuim
    :param real_inlocuit: partea reala a numarului complex pe care dorim sa il inlocuim
    :param imaginar: partea imaginara a numarului complex cu care dorim sa il inlocuim pe primul
    :param real: partea imaginara a numarului complex cu care dorim sa il inlocuim pe primul
    """
    global list
    global refaclist
    de_inlocuit = {'real':real_inlocuit,'imaginar':imaginar_inlocuit}
    inlocuit = {'real':real,'imaginar': imaginar}

    for i in range(0,len(list)):
        if list[i] == de_inlocuit:
            list[i] = inlocuit
    refaclist.append(list[:])


def FiltrarePrim():
    """
    Filtrare parte reala prim – elimină din listă numerele complexe la care partea reala este numar prim.
    """
    global list
    global refaclist
    complex = {}
    i = 0
    while i <= len(list)-1:
        complex = list[i]
        if prim(getReal(complex)) and VerificareNatural(getReal(complex))== True:
            del list[i]
            i = i + 1
        else:
            i = i+1
    refaclist.append(list[:])


def FiltrareModulEgal(modul):
    """
    Filtrare modul – elimina din lista numerele complexe la care modulul este = cu un număr dat.
    :param modul: valoarea dupa care aplicam filtrarea
    """
    global list
    global refaclist
    complex = {}
    i = 0
    while i < len(list)-1:
        complex = list[i]
        if Modul(getReal(complex),getImaginar(complex)) == modul:
            del list[i]
            i = i + 1
        else:
            i=i+1
    refaclist.append(list[:])


def FiltrareModulMaiMare(modul):
    """
        Filtrare modul – elimina din lista numerele complexe la care modulul este > decat un număr dat.
        :param modul: valoarea dupa care aplicam filtrarea
    """
    global list
    global refaclist
    complex = {}
    i = 0
    while i <= len(list)-1:
        complex = list[i]
        if Modul(getReal(complex), getImaginar(complex)) > modul:
            del list[i]
            i = i + 1
        else:
            i = i + 1
    refaclist.append(list[:])


def FiltrareModulMaiMic(modul):
    """
        Filtrare modul – elimina din lista numerele complexe la care modulul este < decat un număr dat.

        :param modul: valoarea dupa care aplicam filtrarea
    """
    global list
    global refaclist
    complex = {}
    i = 0
    while i <= len(list) - 1:
        complex = list[i]
        if Modul(getReal(complex), getImaginar(complex)) < modul:
            del list[i]
            i = i + 1
        else:
            i = i + 1
    refaclist.append(list[:])


def TiparesteModulMaiMic(modul):
    """
    tipareste numerele complexe care au modului egal cu un numar dat
    """
    global list
    global refaclist
    elemente = []
    complex = {}
    i = 0
    while i <= len(list) - 1:
        complex = list[i]
        if Modul(getReal(complex), getImaginar(complex)) < modul:
            elemente.append(complex)
            i = i + 1
        else:
            i = i + 1
    if elemente == []:
        print ("Nu sunt numere care sa satisfaca aceasta conditie")
    else:
        AfisareComplex(elemente)
        refaclist.append(elemente[:])


def TiparesteModulEgal(modul):
    """
    tipareste numerele complexe care au modului egal cu un numar dat
    """
    global list
    global refaclist
    elemente = []
    complex = {}
    i = 0
    while i <= len(list) - 1:
        complex = list[i]
        if Modul(getReal(complex), getImaginar(complex)) == modul:
            elemente.append(complex)
            i = i + 1
        else:
            i = i + 1
    if elemente == []:
        print ("Nu sunt numere care sa satisfaca aceasta conditie")
    else:
        AfisareComplex(elemente)
        refaclist.append(elemente[:])

