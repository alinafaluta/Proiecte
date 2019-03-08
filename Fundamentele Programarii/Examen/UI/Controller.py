from Domain.Jucator import Jucator
from Repository.FileRepository import JucatoriRepo
import random
from enum import Enum
class Control:

    def __init__(self, repo):
        self.repo = repo

    def afisare(self):
        """
        :return: toti jucatorii
        """
        return self.repo.getAll()

    def modificare(self,nume,prenume,inaltime):
        """
        modifica inaltimea unui jucator si valideaza datele
        :param nume: numele jucatorului
        :param prenume: prenumele jucatorului
        :param inaltime: noua inaltime
        :return: jucatorul modificat
        """
        if nume == ' ':
            raise ValueError("Numele nu poate fi vid")
        if prenume == ' ':
            raise ValueError("Prenumele nu poate fi vid")
        if inaltime ==' ':
            raise ValueError("Inaltimea nu poate fi vida")
        elif int(inaltime)<0:
            raise ValueError("Inaltimea trebuie sa fie numar pozitiv")

        return self.repo.modifica(nume,prenume,inaltime)

    def adaugare(self,nume,prenume,inaltime,post):
        return self.repo.add(nume,prenume,inaltime,post)

    def echipa(self):
        """
        :return:  o echipa formata din 5 jucatori (1-pivot,2-extreme,2-fudasi),cu media de inaltime cea mai mare
        :raises ValueError daca numarul de fundasi,pivoti respectiv extremele nu sunt suficiente
        """
        all= self.repo.getAll()
        pivot = []
        fundas = []
        extrema = []
        echipa = []
        for player in all:
            if player.getPost()=='Pivot':
                pivot.append(player)
            if player.getPost()=='Fundas':
                fundas.append(player)
            if player.getPost()=='Extrema':
                extrema.append(player)
        if len(fundas)<2:
            raise ValueError("Nu putem forma o echipa.Numarul de fundasi este insuficient")
        if len(extrema)<2:
            raise ValueError("Nu putem forma o echipa.Numarul de jucatori de extrema este insuficient")
        if len(pivot)<1:
            raise ValueError("Nu putem forma o echipa.Numarul de pivoti este insuficient")
        pivot = sorted(pivot,key=lambda x:x.getHigh(),reverse=True)
        extrema = sorted(extrema,key=lambda x:x.getHigh(),reverse=True)
        fundas = sorted(fundas,key=lambda x:x.getHigh(),reverse=True)
        echipa.append(fundas[0])
        echipa.append(fundas[1])
        echipa.append(pivot[0])
        echipa.append(extrema[0])
        echipa.append(extrema[1])
        return echipa


    def importare(self,file):
        """
        :param file:
        :return:
        """

        f= open(file,'r')
        nr = 0
        line = f.readline().strip()
        all = self.repo.getAll()
        while line != '':
            date = line.split(',')
            nume = date[0]
            prenume = date[1]
            ok=1
            for i in all:
                if i.getNume()==nume and i.getPrenume()== prenume:
                    ok = 0
            if ok ==1:
                inaltimea = random.randint(120,250)
                enumerare = random.randint(1,3)
                post = Post(enumerare).name
                self.repo.add(nume,prenume,inaltimea,post)
                nr+=1
            line = f.readline().strip()
        f.close()
        return nr

class Post(Enum):
    Fundas = 1
    Pivot = 2
    Extrema = 3

def test():
    controler = Control(JucatoriRepo('test.txt'))
    controler.importare('random.txt')

test()